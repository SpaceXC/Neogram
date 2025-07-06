package cn.spacexc.neogram.ui.screen.call

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.data.connection.ConnectionStateRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.username
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.SendCallSignalingData
import org.thunderdog.challegram.voip.ConnectionStateListener
import org.thunderdog.challegram.voip.VoIP
import org.thunderdog.challegram.voip.VoIPInstance
import org.thunderdog.challegram.voip.annotation.CallState

@Serializable
data class VoiceCallScreen(val callId: Int)

const val VOIP_CONNECTION_MIN_LAYER = 65


@Composable
fun VoiceCallScreen(navController: NavController, callId: Int) {
    val currentCall by CallHandler.currentCall.collectAsState()
    val users by UserRepository.users.collectAsState()
    val networkType by ConnectionStateRepository.connectionState.collectAsState()
    TitleFrame("Call", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(modifier = Modifier.padding(top = it)) {
            currentCall?.let { call ->
                users[call.userId]?.let { user ->
                    Text(user.tgUser.username, fontFamily = miSans, color = Color.White)
                }
                Button({
                    val stateListener: ConnectionStateListener = object : ConnectionStateListener {
                        override fun onConnectionStateChanged(
                            context: VoIPInstance,
                            @CallState newState: Int
                        ) {
                            /*if (newState == CallState.ESTABLISHED) {
                                tdlib.dispatchCallStateChanged(call.id, newState)
                            } else if (newState == CallState.FAILED) {
                                val connectionId = context.getConnectionId()
                                tdlib.context().calls().hangUp(tdlib, call.id, true, connectionId)
                            }*/
                        }

                        override fun onSignalBarCountChanged(newCount: Int) {
                            //tdlib.dispatchCallBarsCount(call.id, newCount)
                        }

                        override fun onSignallingDataEmitted(data: ByteArray?) {
                            /*tdlib.client()
                                .send(SendCallSignalingData(call.id, data), tdlib.silentHandler())*/
                        }
                    }

                    val temp = VoIP.instantiateAndConnect(
                        call,
                        currentCall?.state as TdApi.CallStateReady?,
                        stateListener,
                        false,
                        null,
                        networkType.ordinal,
                        true,
                        1,
                        false
                    )
                }) {
                    Text("JIE")
                }
            }
        }
    }
}