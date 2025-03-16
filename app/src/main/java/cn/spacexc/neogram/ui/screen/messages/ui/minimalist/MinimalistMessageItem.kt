package cn.spacexc.neogram.ui.screen.messages.ui.minimalist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.drinkless.tdlib.TdApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.screen.messages.ui.MessageContent
import cn.spacexc.neogram.ui.screen.messages.ui.MessageForwardInfo
import cn.spacexc.neogram.ui.screen.messages.ui.MessageReactions
import cn.spacexc.neogram.ui.screen.messages.ui.ReplyContent
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.toDateStr

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MinimalistMessageItem(
    isGroupChat: Boolean,
    isPreviousOneContinuous: Boolean,
    isNextOneContinuous: Boolean,
    message: TdApi.Message,
    username: String,
    userAccentColor: AccentColorRepository.AccentColor?,
    userPhoto: TdApi.File?,
    userPhotoThumbnail: ByteArray?,
    chats: Map<Long, TdApi.Chat>,
    users: Map<Long, TdApi.User>,
    messages: Map<Long, TdApi.Message>,
    senderIsMe: Boolean,
    isRead: Boolean,
    animatedContentScope: AnimatedContentScope,
    navController: NavController
) {
    val localDensity = LocalDensity.current
    var nameRowHeight by remember { mutableStateOf(0.dp) }

    Column(modifier = Modifier.padding(horizontal = 2.dp)) {
        if (!isPreviousOneContinuous) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(nameRowHeight + 1.dp)
                ) {
                    if (userPhoto != null) {
                        TgImage(
                            animatedContentScope,
                            userPhoto,
                            userPhotoThumbnail,
                            Modifier.fillMaxSize(),
                            null
                        )
                    } else {
                        val brush =
                            if (userAccentColor == null) SolidColor(NeoBlue) else Brush.verticalGradient(
                                listOf(
                                    userAccentColor.backgroundColor,
                                    userAccentColor.background2Color
                                )
                            )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(brush)
                        ) {
                            Text(
                                username.firstOrNull()?.uppercase() ?: "",
                                color = Color.White,
                                fontFamily = miSans,
                                fontSize = 13.sp,
                                //fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }
                    }
                }
                Spacer(Modifier.width(2.dp))
                Column(modifier = Modifier.onSizeChanged {
                    nameRowHeight = with(localDensity) { it.height.toDp() }
                }) {
                    Row(
                        modifier = Modifier
                            .alpha(0.56f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            (message.date * 1000L).toDateStr("HH:mm"),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontFamily = miSans
                        )
                        if (senderIsMe) {
                            if (message.sendingState == null) {
                                Text(
                                    if (isRead) "已读" else "未读",
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontFamily = miSans
                                )
                            } else {
                                Text(
                                    when (message.sendingState) {
                                        is TdApi.MessageSendingStatePending -> "发送中"
                                        else -> "发送失败"
                                    },
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontFamily = miSans
                                )
                            }
                        }
                    }
                    Text(
                        username,
                        color = userAccentColor?.nameColor ?: Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = miSans,
                        modifier = Modifier,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        /*Text(isPreviousOneContinuous.toString(), color = Color.White)
        Text(isNextOneContinuous.toString(), color = Color.White)*/
        Column(
            modifier = Modifier
                .padding(
                    top = if (isPreviousOneContinuous) 2.dp else 6.dp,
                    bottom = if (isNextOneContinuous) 2.dp else 10.dp
                )
                .padding(horizontal = 1.dp)
        ) {
            message.forwardInfo?.let {
                MessageForwardInfo(it, chats, users)
            }
            message.replyTo?.let { reply ->
                ReplyContent(reply, senderIsMe, messages, users, chats)
            }
            MessageContent(
                animatedContentScope,
                message.content,
                message.id,
                users,
                navController,
                userAccentColor?.nameColor ?: Color.White,
                13.5.sp
            )
            message.interactionInfo?.let {
                MessageReactions(it)
            }
        }
    }
}