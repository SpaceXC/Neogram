package cn.spacexc.neogram.ui.screen.messages

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.spacexc.neogram.Application
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.message.MessageRepository
import cn.spacexc.neogram.ui.screen.messages.audio.AndroidAudioRecorder
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.deepCopy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.drinkless.tdlib.TdApi
import java.io.File

class MessagesViewModel(private val chatId: Long, private val lastReadInboxMessageId: Long) : ViewModel() {
    val application = Application.getApplication()

    val mutex = Mutex()
    var messages by mutableStateOf(mapOf<Long, TdApi.Message>())
    private var lastMessageId = 0L
    var loadCompleted by mutableStateOf(false)

    val lazyColumnState = LazyListState()
    var prevFirstMessageId = 0L //用于检测是否有新消息，如果有的话看情况将列表滑到开始

    val audioRecorder = AndroidAudioRecorder(Application.getApplication())
    var currentFile: File? = null
    var startTime = 0L

    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            application.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        application.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    init {
        TdClient.send(TdApi.OpenChat(chatId))
        MessageRepository.subscribeToMessage(chatId)
        viewModelScope.launch {
            while (true) {
                val update = MessageRepository.updates.receive()
                LogUtils.info("updateMessage", "$update")
                when (update) {
                    is TdApi.UpdateNewMessage -> {
                        messages = mapOf(update.message.id to update.message) + messages
                    }

                    is TdApi.UpdateDeleteMessages -> {
                        val temp = messages.toMutableMap()
                        if (update.isPermanent) {
                            update.messageIds.forEach {
                                temp.remove(it)
                            }
                            messages = temp
                        }
                    }

                    is TdApi.UpdateMessageContent -> {
                        val temp = messages.toMutableMap()
                        val newMessage = temp[update.messageId]?.deepCopy()
                        if (newMessage != null) {
                            newMessage.content = update.newContent
                            temp[update.messageId] = newMessage
                            messages = temp
                        }
                    }

                    is TdApi.UpdateMessageInteractionInfo -> {
                        val temp = messages.toMutableMap()
                        val newMessage = temp[update.messageId]?.deepCopy()
                        if (newMessage != null) {
                            newMessage.interactionInfo = update.interactionInfo
                            temp[update.messageId] = newMessage
                            messages = temp
                        }
                    }

                    is TdApi.UpdateMessageSendFailed -> {
                        val temp = messages.toMutableMap()
                        temp[update.oldMessageId] = update.message
                        messages = temp
                    }

                    is TdApi.UpdateMessageSendSucceeded -> {
                        val temp = messages.toMutableMap()
                        temp[update.oldMessageId] = update.message
                        messages = temp
                    }
                }
            }
        }
    }

    suspend fun getMessages(scope: CoroutineScope) {
        mutex.withLock {
            TdClient.send(TdApi.GetChatHistory(chatId, lastMessageId, 0, 20, false), {
                LogUtils.info("getMessages", "$it")
                if (it is TdApi.Messages) {
                    val messageList = it.messages.toList()
                    messages += messageList.map { Pair(it.id, it) }.toMap()
                    lastMessageId = messageList.last().id
                    if (!messages.keys.contains(lastReadInboxMessageId) && lastReadInboxMessageId != 0L) {
                        viewModelScope.launch {
                            getMessages(scope)
                        }
                    } else {
                        if (!loadCompleted) {
                            loadCompleted = true
                        }
                    }
                }
            })
        }
    }

    fun viewMessage(messageId: Long) {
        TdClient.send(TdApi.ViewMessages(chatId, arrayOf(messageId).toLongArray(), null, false))
    }

    fun deleteMessage(messageId: Long) {
        TdClient.send(TdApi.DeleteMessages(chatId, arrayOf(messageId).toLongArray(), true))
    }

    override fun onCleared() {
        super.onCleared()
        MessageRepository.unsubscribeToMessage(chatId)
        TdClient.send(TdApi.CloseChat(chatId))
    }

    fun recordAudio() {
        if (currentFile != null) return
        startTime = System.currentTimeMillis()
        markSelfAsRecording()
        val file =
            File(Application.getApplication().cacheDir, "voiceNote${System.currentTimeMillis()}")
        file.createNewFile()
        currentFile = file
        audioRecorder.start(file)
    }

    fun stopRecording(sendMessage: Boolean) {
        if (currentFile == null) return
        audioRecorder.stop()
        markSelfAsNotRecording()
        LogUtils.info("AudioRecord", "RecordCompleted ${currentFile?.path}")
        if (sendMessage) {
            val content = TdApi.InputMessageVoiceNote(
                TdApi.InputFileLocal(currentFile?.path),
                (System.currentTimeMillis() - startTime).toInt() / 1000,
                emptyArray<Byte>().toByteArray(),
                null,
                null
            )
            val action = TdApi.SendMessage(
                chatId,
                0,
                null,
                null,
                null,
                content
            )
            TdClient.send(action)
        }
        currentFile = null
    }

    fun markSelfAsRecording() {
        TdClient.send(TdApi.SendChatAction(chatId, 0, "", TdApi.ChatActionRecordingVoiceNote()))
    }

    fun markSelfAsNotRecording() {
        TdClient.send(TdApi.SendChatAction(chatId, 0, "", TdApi.ChatActionCancel()))
    }

    fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100L, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100L)
        }
    }
}