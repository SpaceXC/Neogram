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
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.username
import kotlinx.serialization.Serializable

@Serializable
data class VoiceCallScreen(val callId: Int)

const val VOIP_CONNECTION_MIN_LAYER = 65


@Composable
fun VoiceCallScreen(navController: NavController, callId: Int) {
    val currentCall by CallHandler.currentCall.collectAsState()
    val users by UserRepository.users.collectAsState()
    TitleFrame("Call", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(modifier = Modifier.padding(top = it)) {
            currentCall?.let { call ->
                users[call.userId]?.let { user ->
                    Text(user.tgUser.username, fontFamily = miSans, color = Color.White)
                }
                Button({
                    /*TdClient.send(TdApi.AcceptCall(callId, TdApi.CallProtocol(
                        true,
                        true,
                        VOIP_CONNECTION_MIN_LAYER,

                    )))*/
                }) {
                    Text("JIE")
                }
            }
        }
    }
}