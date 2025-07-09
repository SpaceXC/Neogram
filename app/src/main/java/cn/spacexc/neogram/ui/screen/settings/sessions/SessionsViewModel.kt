package cn.spacexc.neogram.ui.screen.settings.sessions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.TdClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.drinkless.tdlib.TdApi

class SessionsViewModel : ViewModel() {
    var sessions by mutableStateOf<TdApi.Sessions?>(null)
        private set


    init {
        TdClient.send(TdApi.GetActiveSessions(), {
            if (it is TdApi.Sessions) sessions = it
        })
    }
}