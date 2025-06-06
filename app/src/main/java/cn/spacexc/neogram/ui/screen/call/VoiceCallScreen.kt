package cn.spacexc.neogram.ui.screen.call

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.ui.theme.TitleFrame
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data class VoiceCallScreen(val callId: Int)

const val VOIP_CONNECTION_MIN_LAYER = 65


@Composable
fun VoiceCallScreen(navController: NavController, callId: Int) {
    TitleFrame("Call", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(modifier = Modifier.padding(top = it)) {
            Button({
                TdClient.send(TdApi.AcceptCall(callId, TdApi.CallProtocol()))
            }) {
                Text("JIE")
            }
        }
    }
}