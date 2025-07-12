package cn.spacexc.neogram.ui.screen.messages.ui

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.drinkless.tdlib.TdApi

data class CallMessageCardColors(
    val background: Color,
    val iconBackground: Color,
    val icon: ImageVector
)


@Composable
fun CallMessageCard(modifier: Modifier = Modifier, messageCall: TdApi.MessageCall) {

}