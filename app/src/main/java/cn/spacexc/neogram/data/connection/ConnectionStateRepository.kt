package cn.spacexc.neogram.data.connection

import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.ConnectionStateConnecting
import org.drinkless.tdlib.TdApi.ConnectionStateConnectingToProxy
import org.drinkless.tdlib.TdApi.ConnectionStateReady
import org.drinkless.tdlib.TdApi.ConnectionStateUpdating
import org.drinkless.tdlib.TdApi.ConnectionStateWaitingForNetwork

/**
 * is TdApi.ConnectionStateWaitingForNetwork -> "等待连接"
 *             is TdApi.ConnectionStateConnecting -> "连接中"
 *             is TdApi.ConnectionStateUpdating -> "更新中"
 *             is TdApi.ConnectionStateConnectingToProxy -> "正在连接到代理"
 *             is TdApi.ConnectionStateReady -> "Neo"
 *             else -> "未知网络状态"
 */

enum class ConnectionState {
    WaitingForNetwork,
    Connecting,
    Updating,
    ConnectingToProxy,
    Ready,
    Unknown
}

object ConnectionStateRepository {
    val connectionState = MutableStateFlow(ConnectionState.WaitingForNetwork)

    fun TdApi.Object.connectionStateHandler() {
        if (this is TdApi.UpdateConnectionState) {
            LogUtils.info("connectionStateUpdate", this.toString())
            connectionState.value = when (state) {
                is ConnectionStateWaitingForNetwork -> ConnectionState.WaitingForNetwork
                is ConnectionStateConnecting -> ConnectionState.Connecting
                is ConnectionStateUpdating -> ConnectionState.Updating
                is ConnectionStateConnectingToProxy -> ConnectionState.ConnectingToProxy
                is ConnectionStateReady -> ConnectionState.Ready
                else -> {
                    LogUtils.info("unknownConnectionStateHandler", "$connectionState")
                    ConnectionState.Unknown
                }
            }
        }
    }
}