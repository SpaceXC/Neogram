package cn.spacexc.neogram.ui.screen.messages.actions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.TdClient
import org.drinkless.tdlib.TdApi

class MessageActionsViewModel(chatId: Long, messageId: Long) : ViewModel() {
    var currentMessage by mutableStateOf<TdApi.Message?>(null)

    init {
        TdClient.send(TdApi.GetMessage(chatId, messageId), {
            currentMessage = it as? TdApi.Message
            println(it)
        }, {
            it?.printStackTrace()
        })
    }
}