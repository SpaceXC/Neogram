package cn.spacexc.neogram.ui.screen.messages

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.spacexc.neogram.Application
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.message.MessageRepository
import cn.spacexc.neogram.ui.screen.messages.audio.AndroidAudioRecorder
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.ToastUtils
import cn.spacexc.neogram.utils.deepCopy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.drinkless.tdlib.TdApi
import java.io.File

class MessagesViewModel(private val chatId: Long, private val lastReadInboxMessageId: Long) :
    ViewModel() {
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

    var messagesNeeded = mutableStateMapOf<Long, TdApi.Message>()   //为了一些被回复的但是还没有加载到的消息

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
                    messages += messageList.associate { Pair(it.id, it) }
                    lastMessageId = messageList.last().id

                    //检查有没有加载到最新未读
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

    suspend fun initMessages(haveUnreadMessages: Boolean) {
        mutex.withLock {
            val messagesFromUnread = TdClient.sendAsync<TdApi.Messages>(
                TdApi.GetChatHistory(
                    chatId,
                    if (haveUnreadMessages) lastReadInboxMessageId else 0,
                    -1,
                    20,
                    false
                )
            )
            if (messagesFromUnread == null) return
            val messageListFromUnreadMessage =
                messagesFromUnread.messages.toList().associate { Pair(it.id, it) }
            messages = messageListFromUnreadMessage
            LogUtils.info("initMessages", "Loaded old messages")

            delay(100)

            if (haveUnreadMessages) {
                LogUtils.info("initMessages", "Loading new messages")
                val newMessagesMap = emptyMap<Long, TdApi.Message>().toMutableMap()
                var finishedLoading = false
                var lastLoadedMessageId = 0L
                while (!finishedLoading) {
                    val newMessages = TdClient.sendAsync<TdApi.Messages>(
                        TdApi.GetChatHistory(
                            chatId,
                            lastLoadedMessageId,
                            0,
                            20,
                            false
                        )
                    )
                    LogUtils.info("initMessages", "Loaded part size ${newMessages?.messages?.size}")
                    if (newMessages == null) continue
                    var newMessagesMapPart =
                        newMessages.messages.toList().associate { Pair(it.id, it) }
                    LogUtils.info(
                        "initMessages",
                        "Have reached $lastReadInboxMessageId? ${
                            newMessagesMapPart.containsKey(lastReadInboxMessageId)
                        }"
                    )
                    finishedLoading =
                        newMessagesMapPart.containsKey(lastReadInboxMessageId)    //加载到了！
                    lastLoadedMessageId = newMessagesMapPart.keys.last()
                    newMessagesMap += newMessagesMapPart
                }
                messages = newMessagesMap + messages
                loadCompleted = true
            }
        }
    }

    fun locateToMessage(messageId: Long, scope: CoroutineScope) {
        if (messages.keys.contains(messageId)) {
            val index = messages.keys.indexOf(messageId)
            scope.launch {
                delay(100)
                //lazyColumnState.animateScrollAndCentralizeItem(index)
            }
        }
    }

    suspend fun LazyListState.animateScrollAndCentralizeItem(index: Int) {
        /*val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
        LogUtils.info("animateScrollAndCentralizeItem", "itemInfo $itemInfo")
        if (itemInfo != null) {
            val center = this@animateScrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            this@animateScrollAndCentralizeItem.scrollBy((childCenter - center).toFloat())
        } else {
            this@animateScrollAndCentralizeItem.animateScrollToItem(index)
        }*/
        var reached = false //this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
        while (!reached) {
            scrollBy(
                10f
            )
            reached = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } != null
            LogUtils.info("animateScrollAndCentralizeItem", "$reached")
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

    /**
     * @param sendMessage 是否发出信息（i.e. 是因为真的要发了还是因为取消了才停止录制的
     */
    fun stopRecording(sendMessage: Boolean) {
        if (currentFile == null) return
        audioRecorder.stop()
        markSelfAsNotRecording()
        LogUtils.info("AudioRecord", "RecordCompleted ${currentFile?.path}")
        if (sendMessage) {
            if ((System.currentTimeMillis() - startTime) < 1000) {
                ToastUtils.toast("录制时间太短了吧...")
            } else {
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