package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.NeoRed
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.getChatActionDescription
import cn.spacexc.neogram.utils.textDescription
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.ChatActionCancel
import org.drinkless.tdlib.TdApi.UserStatusOnline

@Composable
fun NotificationItemCard() {
    val localDensity = LocalDensity.current
    /*Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CardGray, RoundedCornerShape(25))
            .padding(vertical = 10.dp, horizontal = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            var textHeight by remember {
                mutableStateOf(0.dp)
            }
            val thumbnailBytes = chat.photo?.minithumbnail?.data
            Box(
                modifier = Modifier
                    .size(textHeight + 6.dp)
            ) {
                if (thumbnailBytes != null) {
                    TgImage(
                        animatedContentScope,
                        chat.photo.small, //都有缩略图了岂不是包有图的
                        thumbnailBytes,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        navController = null
                    )
                } else {
                    val accentColor = chat.accentColor
                    val brush =
                        if (accentColor == null) SolidColor(NeoBlue) else Brush.verticalGradient(
                            listOf(accentColor.first, accentColor.second)
                        )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush, CircleShape)
                    ) {
                        Text(
                            chat.title.first().uppercase(),
                            color = Color.White,
                            fontFamily = miSans,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                }
                if (chat.userStatus is UserStatusOnline) {
                    Box(
                        Modifier
                            .offset(x = (-1).dp, y = (-1).dp)
                            .size(textHeight * 0.35f)
                            .background(CardGray, CircleShape)
                            .padding(1.5.dp)
                            .align(Alignment.BottomEnd)
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(NeoBlue, CircleShape)
                                .padding(0.5.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.width(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier
                    .weight(1f)
                    .onSizeChanged {
                        textHeight = with(localDensity) { it.height.toDp() }
                    }
                ) {
                    Text(
                        buildAnnotatedString {
                            if (chat.type is TdApi.ChatTypeSecret) {
                                appendInlineContent("lock")
                            }
                            append(chat.title)
                        },
                        color = Color.White,
                        fontFamily = miSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        inlineContent = mapOf(
                            "lock" to InlineTextContent(
                                placeholder = Placeholder(
                                    15.sp, 15.sp,
                                    PlaceholderVerticalAlign.Center
                                )
                            ) {
                                Icon(
                                    painterResource(cn.spacexc.neogram.R.drawable.icon_lock),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        )
                    )
                    val (inlineTextContent, annotatedString) = chat.lastMessage?.content.textDescription(
                        users,
                        13.sp
                    )
                    if (chat.draftMessage != null) {
                        val draftContent = when (chat.draftMessage.inputMessageText) {
                            is TdApi.InputMessageText -> (chat.draftMessage.inputMessageText as TdApi.InputMessageText).text.text
                            is TdApi.InputMessageVoiceNote -> "语音 ${(chat.draftMessage.inputMessageText as TdApi.InputMessageVoiceNote).duration}\""
                            is TdApi.InputMessageVideoNote -> "视频 ${(chat.draftMessage.inputMessageText as TdApi.InputMessageVideoNote).duration}\""
                            else -> ""
                        }
                        Text(
                            text = "草稿: $draftContent",
                            color = NeoRed,
                            fontFamily = miSans,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.alpha(.8f)
                        )
                    } else {
                        val chatState = chat.chatAction.getChatActionDescription(users, chats, chat.type)
                        Text(
                            text = if (chat.chatAction == null || chat.chatAction?.action is ChatActionCancel) annotatedString else buildAnnotatedString { chatState },
                            color = Color.White,
                            fontFamily = miSans,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.alpha(.8f),
                            inlineContent = inlineTextContent
                        )
                    }
                }
                if (chat.unreadCount > 0) {
                    Box(
                        modifier = Modifier
                            .height(textHeight * 0.5f)
                            .requiredSizeIn(minWidth = textHeight * 0.5f)
                            .background(if (chat.isMuted) Color.Gray else NeoBlue, CircleShape)
                    ) {

                        Text(
                            chat.unreadCount.toString(),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 2.dp, end = 2.5.dp, bottom = 2.dp, start = 3.dp),
                            color = if (chat.isMuted) CardGray else Color.White,
                            fontFamily = miSans,
                            fontSize = 9.sp,
                        )
                    }
                }
            }
        }
    }*/
}