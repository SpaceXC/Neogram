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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.overscroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Reply
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.ui.component.DraggableBox
import cn.spacexc.neogram.ui.screen.messages.MessageSwipeToReplyState
import cn.spacexc.neogram.ui.screen.messages.ui.bubble.BubbledMessageItem
import cn.spacexc.neogram.ui.screen.messages.ui.minimalist.MinimalistMessageItem
import cn.spacexc.neogram.utils.textDescription
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
    var accentColor: AccentColorRepository.AccentColor? by remember { mutableStateOf(null) }

    val isMinimalist = true

    if (message.senderId is TdApi.MessageSenderChat) {
        val chatId = (message.senderId as TdApi.MessageSenderChat).chatId
        chats[chatId]?.let { chat ->
            name = chat.title
            photoThumbnail = chat.photo?.minithumbnail?.data
            photoFile = chat.photo?.small
            accentColor =
                AccentColorRepository.getAccentColor(chat.accentColorId)
        }
    }
    if (message.senderId is TdApi.MessageSenderUser) {
        val userId = (message.senderId as TdApi.MessageSenderUser).userId
        users[userId]?.let { user ->
            name = user.username
            photoThumbnail = user.profilePhoto?.minithumbnail?.data
            photoFile = user.profilePhoto?.small
            accentColor =
                AccentColorRepository.getAccentColor(user.accentColorId)
        }
    }

    Box(modifier = modifier, contentAlignment = CenterStart) {
        var progress by remember { mutableFloatStateOf(0f) }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.Reply,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(with(localDensity) { 13.5.sp.toDp() } * 1.5f)
                .offset(y = if (isMinimalist && !isNextOneContinuous) (-4).dp else 0.dp)
                .alpha(progress)
                .scale(progress)
        )

        when (message.content) {
            is TdApi.MessageChatJoinByLink -> {
                MessageNotification("${name}通过链接加入了群聊")
            }

            is TdApi.MessageChatJoinByRequest -> {
                MessageNotification("${name}通过申请加入了群聊")
            }

            is TdApi.MessageChatDeleteMember -> {
                MessageNotification("${name}退出了群聊")
            }

            is TdApi.MessageExpiredPhoto -> {
                MessageNotification("图片已失效")
            }

            is TdApi.MessageExpiredVideo -> {
                MessageNotification("视频已失效")
            }

            is TdApi.MessageExpiredVideoNote -> {
                MessageNotification("视频已失效")
            }

            is TdApi.MessageExpiredVoiceNote -> {
                MessageNotification("语音已失效")
            }

            is TdApi.MessageChatChangeTitle -> {
                MessageNotification("$name 更改群名为 ${(message.content as TdApi.MessageChatChangeTitle).title}")
            }

            is TdApi.MessageChatAddMembers -> {
                MessageNotification(
                    "${
                        (message.content as TdApi.MessageChatAddMembers).memberUserIds.map { "${users[it]?.username}" }
                            .joinToString { ", " }
                    }加入了群聊"
                )
            }

            else -> {
                DraggableBox(
                    modifier = Modifier.fillMaxWidth(),
                    50f,
                    onProgressChange = {
                        progress = it
                    },
                    onTriggered = {
                        onReplyMessage(
                            name,
                            message.content.textDescription(users, 12.sp).second.text
                        )
                    }
                ) {
                    if (isMinimalist) {
                        MinimalistMessageItem(
                            isGroupChat = isGroupChat,
                            isPreviousOneContinuous = isPreviousOneContinuous,
                            isNextOneContinuous = isNextOneContinuous,
                            message = message,
                            username = name,
                            userAccentColor = accentColor,
                            userPhoto = photoFile,
                            userPhotoThumbnail = photoThumbnail,
                            chats = chats,
                            users = users,
                            messages = messages,
                            senderIsMe = senderIsMe,
                            isRead = isRead,
                            animatedContentScope = animatedContentScope,
                            navController = navController,
                        )
                    } else {
                        BubbledMessageItem(
                            isGroupChat = isGroupChat,
                            isPreviousOneContinuous = isPreviousOneContinuous,
                            isNextOneContinuous = isNextOneContinuous,
                            message = message,
                            username = name,
                            usernameColor = accentColor?.nameColor ?: Color.White,
                            chats = chats,
                            users = users,
                            messages = messages,
                            senderIsMe = senderIsMe,
                            isRead = isRead,
                            animatedContentScope = animatedContentScope,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}