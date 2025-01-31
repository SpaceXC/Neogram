package cn.spacexc.neogram.ui.screen.messages.send

import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.utils.LogUtils
import org.drinkless.tdlib.TdApi

class SendMessageViewModel(
    private val chatId: Long,
    private val replyMessageId: Long = 0
) : ViewModel() {

    init {
        markSelfAsTyping()
    }

    fun sendTextMessage(textContent: String) {
        val content = TdApi.InputMessageText(
            TdApi.FormattedText(textContent, arrayOf()),
            null,
            true
        )
        val replyInfo = if (replyMessageId == 0L) null else TdApi.InputMessageReplyToMessage(
            replyMessageId,
            null
        )
        val action = TdApi.SendMessage(
            chatId,
            0,
            replyInfo,
            null,
            null,
            content
        )
        TdClient.send(action)
    }

    fun updateDraftMessage(textContent: String) {
        val content = TdApi.InputMessageText(
            TdApi.FormattedText(textContent, arrayOf()),
            null,
            true
        )
        val replyInfo = if (replyMessageId == 0L) null else TdApi.InputMessageReplyToMessage(
            replyMessageId,
            null
        )
        TdClient.send(TdApi.SetChatDraftMessage(
            chatId,
            0,
            TdApi.DraftMessage(replyInfo, (System.currentTimeMillis() / 1000).toInt(), content, 0)
        ))
    }

    fun markSelfAsTyping() {
        TdClient.send(TdApi.SendChatAction(chatId, 0, "", TdApi.ChatActionTyping()))
    }

    fun markSelfAsNotTyping() {
        TdClient.send(TdApi.SendChatAction(chatId, 0, "", TdApi.ChatActionCancel()))
    }

    override fun onCleared() {
        super.onCleared()
        markSelfAsNotTyping()
    }
}