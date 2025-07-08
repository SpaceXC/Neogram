package cn.spacexc.neogram.ui.screen.call

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.data.call.durationFlow
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
    val callInstance by CallHandler.currentInstance.collectAsState()
    val duration by (callInstance?.durationFlow()?.collectAsState(0L)
        ?: remember { mutableLongStateOf(0L) })
    val users by UserRepository.users.collectAsState()
    TitleFrame("Call", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = it)
        ) {
            currentCall?.let { call ->
                users[call.userId]?.let { user ->
                    Text(user.tgUser.username, fontFamily = miSans, color = Color.White)
                }
                Button({
                    TdClient.send(TdApi.AcceptCall(call.id, VoIP.getProtocol()))
                }) {
                    Text("JIE")
                }
                Button({
                    TdClient.send(
                        TdApi.DiscardCall(
                            call.id,
                            false,
                            null,
                            (duration / 1000L).toInt(),
                            false,
                            callInstance?.connectionId ?: 0
                        )
                    )
                    callInstance?.performDestroy()
                }) {
                    Text("BUJIE")
                }

                Text("$duration", fontFamily = miSans, color = Color.White)
                Text("${call.state}", fontFamily = miSans, color = Color.White)

            }
        }
    }
}