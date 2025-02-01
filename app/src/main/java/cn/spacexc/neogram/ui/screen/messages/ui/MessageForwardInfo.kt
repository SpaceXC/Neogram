package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.username
import org.drinkless.tdlib.TdApi

@Composable
fun MessageForwardInfo(
    forwardInfo: TdApi.MessageForwardInfo,
    chats: Map<Long, TdApi.Chat>,
    users: Map<Long, TdApi.User>,
    modifier: Modifier = Modifier,
) {
    val originName = when (forwardInfo.origin) {
        is TdApi.MessageOriginChat -> {
            val chatId =
                (forwardInfo.origin as TdApi.MessageOriginChat).senderChatId
            chats[chatId]?.title ?: ""
        }

        is TdApi.MessageOriginChannel -> {
            val chatId = (forwardInfo.origin as TdApi.MessageOriginChannel).chatId
            chats[chatId]?.title ?: ""
        }

        is TdApi.MessageOriginUser -> {
            val userId =
                (forwardInfo.origin as TdApi.MessageOriginUser).senderUserId
            users[userId]?.username ?: ""
        }

        is TdApi.MessageOriginHiddenUser -> {
            (forwardInfo.origin as TdApi.MessageOriginHiddenUser).senderName
        }

        else -> ""
    }
    Text(
        "转发自$originName",
        fontFamily = miSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = NeoBlue,
        modifier = modifier
    )

}