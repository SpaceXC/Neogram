package cn.spacexc.neogram.ui.screen.messages

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.drinkless.tdlib.TdApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.message.MessageRepository
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.deepCopy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MessagesViewModel(private val chatId: Long) : ViewModel() {
    var messages by mutableStateOf(mapOf<Long, TdApi.Message>())
    private var lastMessageId = 0L

    init {
        getMessages()
        TdClient.send(TdApi.OpenChat(chatId))
        MessageRepository.subscribeToMessage(chatId)
        viewModelScope.launch {
            while (true) {
                val update = MessageRepository.updates.receive()
                LogUtils.info("updateMessage", "$update")
                when(update) {
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
                        if(newMessage != null) {
                            newMessage.content = update.newContent
                            temp[update.messageId] = newMessage
                            messages = temp
                        }
                    }
                    is TdApi.UpdateMessageInteractionInfo -> {
                        val temp = messages.toMutableMap()
                        val newMessage = temp[update.messageId]?.deepCopy()
                        if(newMessage != null) {
                            newMessage.interactionInfo = update.interactionInfo
                            temp[update.messageId] = newMessage
                            messages = temp
                        }
                    }
                }
            }
        }
    }

    fun getMessages() {
        TdClient.send(TdApi.GetChatHistory(chatId, lastMessageId, 0, 20, false), {
            LogUtils.info("getMessages", "$it")
            if (it is TdApi.Messages) {
                val messageList = it.messages.toList()
                messages += messageList.map { Pair(it.id, it) }.toMap()
                lastMessageId = messageList.last().id
            }
        })
    }

    fun viewMessage(messageId: Long) {
        TdClient.send(TdApi.ViewMessages(chatId, arrayOf(messageId).toLongArray(), null, false))
    }

    override fun onCleared() {
        super.onCleared()
        MessageRepository.unsubscribeToMessage(chatId)
        TdClient.send(TdApi.CloseChat(chatId))
    }
}