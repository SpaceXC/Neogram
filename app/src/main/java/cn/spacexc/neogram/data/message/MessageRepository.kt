package cn.spacexc.neogram.data.message

import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.channels.Channel
import org.drinkless.tdlib.TdApi

object MessageRepository {
    val updates = Channel<TdApi.Update>()
    private val subscribers = mutableSetOf<Long>()

    suspend fun TdApi.Object.messageHandler() {
        when(this) {
            is TdApi.UpdateNewMessage -> {
                if(subscribers.contains(message.chatId)) {
                    updates.send(this)
                }
            }
            is TdApi.UpdateMessageContent -> {
                if(subscribers.contains(chatId)) {
                    updates.send(this)
                }
            }
            is TdApi.UpdateMessageInteractionInfo -> {
                if(subscribers.contains(chatId)) {
                    updates.send(this)
                }
            }
            is TdApi.UpdateDeleteMessages -> {
                if(subscribers.contains(chatId)) {
                    updates.send(this)
                }
            }
            is TdApi.UpdateMessageSendSucceeded -> {
                if(subscribers.contains(message.chatId)) {
                    updates.send(this)
                }
            }
            is TdApi.UpdateMessageSendFailed -> {
                if(subscribers.contains(message.chatId)) {
                    updates.send(this)
                }
            }
        }
    }

    fun subscribeToMessage(chatId: Long) {
        subscribers.add(chatId)
    }

    fun unsubscribeToMessage(chatId: Long) {
        try {
            subscribers.remove(chatId)
        } catch (_: Exception) {}
    }
}