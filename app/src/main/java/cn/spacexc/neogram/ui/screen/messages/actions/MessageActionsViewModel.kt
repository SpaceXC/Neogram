package cn.spacexc.neogram.ui.screen.messages.actions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.utils.ToastUtils
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi
import kotlin.collections.getValue
import kotlin.collections.setValue

class MessageActionsViewModel(private val chatId: Long, messageId: Long) : ViewModel() {
    var currentMessage by mutableStateOf<TdApi.Message?>(null)
    var messagesNeeded = mutableStateMapOf<Long, TdApi.Message>()

    init {
        TdClient.send(TdApi.GetMessage(chatId, messageId), {
            currentMessage = it as? TdApi.Message
            currentMessage?.let { message ->
                if (message.replyTo is TdApi.MessageReplyToMessage) {
                    val replyTo = message.replyTo as TdApi.MessageReplyToMessage
                    TdClient.send(
                        TdApi.GetMessage(replyTo.chatId, replyTo.messageId),
                        { replyMessage ->
                            if (replyMessage is TdApi.Message) messagesNeeded[replyTo.messageId] =
                                replyMessage
                        })
                }
            }
        }, {
            it?.printStackTrace()
        })
    }

    fun deleteMessage(messageId: Long) {
        TdClient.send(TdApi.DeleteMessages(chatId, arrayOf(messageId).toLongArray(), true))
    }

    fun saveMessage(message: TdApi.Message) {
        viewModelScope.launch {
            TdClient.send(
                TdApi.ForwardMessages(
                    ChatListRepository.getSavedMessageChatId(),
                    message.messageThreadId,
                    message.chatId,
                    longArrayOf(message.id),
                    null,
                    false,
                    false
                ),
                {
                    if (it is TdApi.Messages) {
                        ToastUtils.toast("保存成功")
                    }
                }
            )
        }
    }
}