package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.proto.settings.ChatItemStyle
import cn.spacexc.neogram.proto.settings.NeogramSettings
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.neogram.utils.username
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import org.drinkless.tdlib.TdApi

@Composable
fun ReplyContent(
    reply: TdApi.MessageReplyTo,
    senderIsMe: Boolean,
    messages: Map<Long, TdApi.Message>,
    users: Map<Long, TdApi.User>,
    chats: Map<Long, TdApi.Chat>,
    settings: NeogramSettings,
    shouldMessageBeDisplayedInBubble: Boolean,
    onClick: (TdApi.Message) -> Unit
) {
    val localDensity = LocalDensity.current
    var textHeight by remember { mutableStateOf(0.dp) }
    var senderName by remember { mutableStateOf("") }
    var senderColor by remember { mutableStateOf(Color.White) }

    var shouldBeColorful = !shouldMessageBeDisplayedInBubble || settings.chatItemStyle == ChatItemStyle.Minimalist

    Row(
        modifier = Modifier
            .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(3.dp)
                .height(textHeight)
                .background(
                    (if (shouldBeColorful) senderColor else if (senderIsMe) Color.White else NeoMain).copy(
                        alpha = 0.5f
                    ), CircleShape
                )
        )
        if (reply is TdApi.MessageReplyToMessage) {
            Column(modifier = Modifier
                .onSizeChanged {
                    textHeight = with(localDensity) { it.height.toDp() }
                }
                .clickVfx(onClick = { messages[reply.messageId]?.let { onClick(it) } })) {
                if (reply.content == null) {
                    messages[reply.messageId]?.let { message ->
                        senderName = when (message.senderId) {
                            is TdApi.MessageSenderUser -> {
                                val user = users[(message.senderId as TdApi.MessageSenderUser).userId]
                                senderColor = user?.accentColorId?.let { AccentColorRepository.getAccentColor(it) }?.nameColor ?: Color.White
                                user?.username ?: ""
                            }

                            is TdApi.MessageSenderChat -> {
                                val chat = chats[(message.senderId as TdApi.MessageSenderChat).chatId]
                                senderColor = chat?.accentColorId?.let { AccentColorRepository.getAccentColor(it) }?.nameColor ?: Color.White
                                chat?.title ?: ""
                            }

                            else -> ""
                        }
                        Text(
                            senderName,
                            color = if (shouldBeColorful) senderColor else if (senderIsMe) Color.White else NeoMain,
                            fontSize = 12.sp,
                            fontFamily = miSans,
                            maxLines = 1,
                            fontWeight = FontWeight.Medium,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            message.content.textDescription(users, 12.sp).second,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = miSans,
                            maxLines = 1,
                            modifier = Modifier.alpha(0.5f),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                } else {
                    senderName = when (reply.origin) {
                        is TdApi.MessageOriginChat -> {
                            val chatId =
                                (reply.origin as TdApi.MessageOriginChat).senderChatId
                            senderColor = chats[chatId]?.accentColorId?.let { AccentColorRepository.getAccentColor(it)?.nameColor } ?: Color.White
                            chats[chatId]?.title ?: ""
                        }

                        is TdApi.MessageOriginChannel -> {
                            val chatId = (reply.origin as TdApi.MessageOriginChannel).chatId
                            senderColor = chats[chatId]?.accentColorId?.let { AccentColorRepository.getAccentColor(it)?.nameColor } ?: Color.White
                            chats[chatId]?.title ?: ""
                        }

                        is TdApi.MessageOriginUser -> {
                            val userId =
                                (reply.origin as TdApi.MessageOriginUser).senderUserId
                            senderColor = users[userId]?.accentColorId?.let { AccentColorRepository.getAccentColor(it)?.nameColor } ?: Color.White
                            users[userId]?.username ?: ""
                        }

                        is TdApi.MessageOriginHiddenUser -> {
                            (reply.origin as TdApi.MessageOriginHiddenUser).senderName
                        }

                        else -> ""
                    }
                    Text(
                        senderName,
                        color = if (shouldBeColorful) senderColor else if (senderIsMe) Color.White else NeoMain,
                        fontSize = 12.sp,
                        fontFamily = miSans,
                        maxLines = 1,
                        fontWeight = FontWeight.Medium,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        if (senderIsMe) buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.White)) {
                                append(
                                    reply.content.textDescription(
                                        users,
                                        12.sp
                                    ).second.text
                                )
                            }
                        } else reply.content.textDescription(users, 12.sp).second,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = miSans,
                        maxLines = 1,
                        modifier = Modifier.alpha(0.5f),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}