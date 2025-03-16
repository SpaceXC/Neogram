package cn.spacexc.neogram.ui.screen.messages.ui.bubble

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.screen.messages.ui.MessageContent
import cn.spacexc.neogram.ui.screen.messages.ui.MessageForwardInfo
import cn.spacexc.neogram.ui.screen.messages.ui.MessageReactions
import cn.spacexc.neogram.ui.screen.messages.ui.ReplyContent
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.toDateStr
import org.drinkless.tdlib.TdApi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.BubbledMessageItem(
    isGroupChat: Boolean,
    isPreviousOneContinuous: Boolean,
    isNextOneContinuous: Boolean,
    message: TdApi.Message,
    username: String,
    usernameColor: Color,
    chats: Map<Long, TdApi.Chat>,
    users: Map<Long, TdApi.User>,
    messages: Map<Long, TdApi.Message>,
    senderIsMe: Boolean,
    isRead: Boolean,
    animatedContentScope: AnimatedContentScope,
    navController: NavController
) {
    Column {
        if (isGroupChat && !isPreviousOneContinuous/* && message.content !is TdApi.MessageText*/) {
            Text(
                username,
                color = usernameColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = miSans
            )
        }

        val shouldMessageDisplayedInABox = message.content is TdApi.MessageText ||
                message.content is TdApi.MessageVoiceNote ||
                (message.content is TdApi.MessagePhoto && (message.content as TdApi.MessagePhoto).caption.text.isNotEmpty()) ||
                (message.content is TdApi.MessageVideo && (message.content as TdApi.MessageVideo).caption.text.isNotEmpty())

        if (!shouldMessageDisplayedInABox) {
            message.forwardInfo?.let {
                MessageForwardInfo(it, chats, users)
            }
        }

        if (message.content !is TdApi.MessageText) {
            message.replyTo?.let { reply ->
                ReplyContent(reply, senderIsMe, messages, users, chats)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (senderIsMe) Arrangement.End else Arrangement.Start
        ) {
            if (shouldMessageDisplayedInABox) {
                ChatBubble(
                    Modifier,
                    senderIsMe,
                    isPreviousOneContinuous,
                    isNextOneContinuous
                ) {
                    Column(
                        horizontalAlignment = if (senderIsMe) Alignment.End else Alignment.Start,
                        modifier = Modifier.padding(8.dp)
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
                            usernameColor
                        )
                        message.interactionInfo?.let {
                            MessageReactions(it)
                        }
                        Row(
                            modifier = Modifier
                                .alpha(0.7f)
                                .padding(top = 2.dp),
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
                    }
                }
            } else {
                Box {
                    MessageContent(
                        animatedContentScope,
                        message.content,
                        message.id,
                        users,
                        navController,
                        usernameColor
                    )

                    Column(modifier = Modifier.align(Alignment.BottomStart)) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = 3.dp, start = 3.dp)
                                .background(BadgeGray, CircleShape)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.alpha(0.49f)
                            ) {
                                Text(
                                    (message.date * 1000L).toDateStr("hh:mm a"),
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontFamily = miSans,
                                    modifier = Modifier
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
                        }
                    }
                }
            }

        }
        
        Spacer(Modifier.height(if (isNextOneContinuous) 1.dp else 8.dp))
    }
}