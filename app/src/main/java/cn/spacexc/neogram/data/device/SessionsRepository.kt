package cn.spacexc.neogram.data.device

import cn.spacexc.neogram.data.TdClient
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi

object SessionsRepository {
    val sessions = MutableStateFlow<TdApi.Sessions?>(null)

    fun getSessions() {
        TdClient.send(TdApi.GetActiveSessions(), {
            if (it is TdApi.Sessions) sessions.value = it
        })
    }
}