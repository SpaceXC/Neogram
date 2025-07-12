package cn.spacexc.neogram.ui.screen.messages.ui

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.icons.CallError
import cn.spacexc.neogram.ui.icons.CallIncome
import cn.spacexc.neogram.ui.icons.CallOutgoing
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.formatTimestamp
import cn.spacexc.neogram.utils.rgba
import cn.spacexc.neogram.utils.toMinSec
import org.drinkless.tdlib.TdApi

data class CallMessageCardColors(
    val background: Color,
    val iconBackground: Color,
    val textColor: Color = iconBackground
)

val outgoing = CallMessageCardColors(
    rgba(49, 24, 15, 1f),
    rgba(255, 87, 34, 1f)
)

val incoming = CallMessageCardColors(
    rgba(49, 49, 49, 0.65f),
    rgba(49, 49, 49, 0.65f),
    rgba(142, 142, 142, 1f)
)

val failed = CallMessageCardColors(
    rgba(255, 58, 62, 0.2f),
    rgba(255, 58, 62, 1f)
)

val aborted = CallMessageCardColors(
    rgba(49, 38, 15, 1f),
    rgba(255, 163, 34, 1f)
)

@Composable
fun CallMessageCard(
    modifier: Modifier = Modifier,
    messageCall: TdApi.MessageCall,
    isOutgoing: Boolean,
    timestamp: Long,
) {
    val color = when (messageCall.discardReason) {
        is TdApi.CallDiscardReasonDeclined -> failed
        is TdApi.CallDiscardReasonDisconnected -> aborted
        else -> {
            if (isOutgoing) outgoing else incoming
        }
    }
    val icon = when (messageCall.discardReason) {
        is TdApi.CallDiscardReasonDeclined -> NeogramIcons.CallError
        is TdApi.CallDiscardReasonDisconnected -> NeogramIcons.CallError
        else -> {
            if (isOutgoing) NeogramIcons.CallOutgoing else NeogramIcons.CallIncome
        }
    }
    val mainText = when (messageCall.discardReason) {
        is TdApi.CallDiscardReasonDeclined -> "拒绝接听"
        is TdApi.CallDiscardReasonDisconnected -> "通话中断"
        else -> {
            if (isOutgoing) "去电" else "来电"
        }
    }
    val secondaryText = when (messageCall.discardReason) {
        is TdApi.CallDiscardReasonMissed -> "未接"
        is TdApi.CallDiscardReasonDisconnected, is TdApi.CallDiscardReasonHungUp -> {
            "通话结束 ${(messageCall.duration * 1000L).toMinSec()}"
        }
        else -> ""
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = color.background, shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 5.dp,
                    bottomEnd = 15.dp
                )
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint = Color.White,
            modifier = Modifier.size(36.dp).background(color.iconBackground, CircleShape).padding(8.dp),
            contentDescription = null
        )
        Column {
            Text(
                text = mainText,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            )
            Text(
                text = "${formatTimestamp(timestamp)} $secondaryText",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    color = color.textColor,
                )
            )
        }
    }
}