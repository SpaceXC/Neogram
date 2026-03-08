package cn.spacexc.neogram.ui.screen.messages.send

import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.TdClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
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

    fun sendStickerMessage(stickerFileId: Int, width: Int, height: Int, emoji: String) {
        val stickerFile = TdApi.InputFileId(stickerFileId)
        val content = TdApi.InputMessageSticker(
            stickerFile,
            null,
            width,
            height,
            emoji
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

    fun updateTextMessage(
        messageId: Long,
        textContent: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val content = TdApi.InputMessageText(
            TdApi.FormattedText(textContent, arrayOf()),
            null,
            true
        )
        val action = TdApi.EditMessageText(
            chatId,
            messageId,
            null,
            content
        )
        TdClient.send(action, {
            MainScope().launch {
                onSuccess()
            }
        }, {
            MainScope().launch {
                onFailure()
                it?.printStackTrace()
            }
        })
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