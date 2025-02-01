package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.ui.screen.messages.MessageSwipeToReplyState
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.neogram.utils.toDateStr
import cn.spacexc.neogram.utils.username
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.drinkless.tdlib.TdApi
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MessageCard(
    animatedContentScope: AnimatedContentScope,
    isGroupChat: Boolean,
    users: Map<Long, TdApi.User>,
    chats: Map<Long, TdApi.Chat>,
    modifier: Modifier = Modifier,
    message: TdApi.Message,
    isPreviousOneContinuous: Boolean,
    isNextOneContinuous: Boolean,
    messages: Map<Long, TdApi.Message>,
    isRead: Boolean,
    senderIsMe: Boolean,
    navController: NavController,
    onReplyMessage: (String, String) -> Unit
) {
    val localDensity = LocalDensity.current
    var photoThumbnail: ByteArray? by remember { mutableStateOf(null) }
    var photoFile: TdApi.File? by remember { mutableStateOf(null) }
    var name by remember { mutableStateOf("") }
    var nameColor by remember { mutableStateOf(Color.White) }
    if (message.senderId is TdApi.MessageSenderChat) {
        val chatId = (message.senderId as TdApi.MessageSenderChat).chatId
        chats[chatId]?.let { chat ->
            name = chat.title
            photoThumbnail = chat.photo?.minithumbnail?.data
            photoFile = chat.photo?.small
            nameColor =
                AccentColorRepository.getAccentColor(chat.accentColorId)?.nameColor ?: Color.White
        }
    }
    if (message.senderId is TdApi.MessageSenderUser) {
        val userId = (message.senderId as TdApi.MessageSenderUser).userId
        users[userId]?.let { user ->
            name = user.username
            photoThumbnail = user.profilePhoto?.minithumbnail?.data
            photoFile = user.profilePhoto?.small
            nameColor =
                AccentColorRepository.getAccentColor(user.accentColorId)?.nameColor ?: Color.White
        }
    }

    val anchors = remember(localDensity) {
        val destination = with(localDensity) { 24.dp.toPx() }
        DraggableAnchors {
            MessageSwipeToReplyState.Resting at 0f
            MessageSwipeToReplyState.Replying at if (senderIsMe) -destination else destination
        }
    }
    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = MessageSwipeToReplyState.Resting,
            anchors = anchors,
            velocityThreshold = {
                with(localDensity) { 80.dp.toPx() }
            },
            positionalThreshold = { distance ->
                distance * 0.5f
            },
            snapAnimationSpec = tween(),
            decayAnimationSpec = splineBasedDecay(localDensity)
        )
    }
    val overscrollEffect = ScrollableDefaults.overscrollEffect()

    SideEffect {
        anchoredDraggableState.updateAnchors(anchors)
    }

    LaunchedEffect(anchoredDraggableState) {
        snapshotFlow { anchoredDraggableState.settledValue }.collectLatest {
            if (it == MessageSwipeToReplyState.Replying) {
                delay(100)
                onReplyMessage(name, message.content.textDescription(users, 12.sp).second.text)
                anchoredDraggableState.animateTo(MessageSwipeToReplyState.Resting)
            }
        }
    }

    Box(modifier = modifier) {
        val progress = anchoredDraggableState.progress(
            MessageSwipeToReplyState.Resting,
            MessageSwipeToReplyState.Replying
        )

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.Reply,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(if (senderIsMe) CenterEnd else CenterStart)
                .alpha(progress)
                .scale(progress)
        )

        Column(horizontalAlignment = if (senderIsMe) Alignment.End else Alignment.Start,
            modifier = Modifier
                .anchoredDraggable(
                    state = anchoredDraggableState,
                    orientation = Orientation.Horizontal,
                    overscrollEffect = overscrollEffect
                )
                .overscroll(overscrollEffect)
                .offset {
                    IntOffset(
                        x = anchoredDraggableState.requireOffset().roundToInt(),
                        y = 0
                    )
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {

                        }
                    )
                }
        ) {
            if (isGroupChat && !isPreviousOneContinuous/* && message.content !is TdApi.MessageText*/) {
                Text(
                    name,
                    color = nameColor,
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
                    ChatBubble(Modifier, senderIsMe, isPreviousOneContinuous, isNextOneContinuous) {
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
                            MessageContent(animatedContentScope, message.content, users, navController)
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
                                    Text(
                                        if (isRead) "已读" else "未读",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontFamily = miSans
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Box {
                        MessageContent(animatedContentScope, message.content, users, navController)

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
                                        Text(
                                            if (isRead) "已读" else "未读",
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
            Spacer(Modifier.height(if (isNextOneContinuous) 1.dp else 8.dp))
        }
    }
}