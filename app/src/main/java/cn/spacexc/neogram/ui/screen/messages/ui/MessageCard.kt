package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.proto.settings.ChatItemStyle
import cn.spacexc.neogram.proto.settings.NeogramSettings
import cn.spacexc.neogram.ui.component.DraggableBox
import cn.spacexc.neogram.ui.component.DraggableBoxDirection
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.screen.messages.ui.bubble.BubbledMessageItem
import cn.spacexc.neogram.ui.screen.messages.ui.minimalist.MinimalistMessageItem
import cn.spacexc.neogram.ui.theme.BubbleGray
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.neogram.utils.username
import org.drinkless.tdlib.TdApi

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
    isRead: Boolean?,
    senderIsMe: Boolean,
    navController: NavController,
    settings: NeogramSettings,
    onVibrate: () -> Unit,
    isActionEnabled: Boolean = true,
    onLocateToMessage: (TdApi.Message) -> Unit,
    onReplyMessage: (String, String) -> Unit
) {
    val localDensity = LocalDensity.current
    var photoThumbnail: ByteArray? by remember { mutableStateOf(null) }
    var photoFile: TdApi.File? by remember { mutableStateOf(null) }
    var name by remember { mutableStateOf("") }
    var accentColor: AccentColorRepository.AccentColor? by remember { mutableStateOf(null) }

    val isMinimalist = settings.chatItemStyle == ChatItemStyle.Minimalist

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

    Box(
        modifier = modifier
            .sharedElement(rememberSharedContentState(message.id), animatedContentScope),
        contentAlignment = CenterStart
    ) {
        var progress by remember { mutableFloatStateOf(0f) }

        Icon(
            imageVector = Icons.AutoMirrored.Rounded.Reply,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(with(localDensity) { 13.5.sp.toDp() } * 1.5f)
                .offset(y = if (isMinimalist && !isNextOneContinuous) (-4).dp else 0.dp)
                .alpha(progress)
                .scale(progress)
        )

        when (message.content) {
            is TdApi.MessageChatJoinByLink -> {
                MessageNotification("$name 通过链接加入了群聊")
            }

            is TdApi.MessageChatJoinByRequest -> {
                MessageNotification("$name 通过申请加入了群聊")
            }

            is TdApi.MessageChatDeleteMember -> {
                MessageNotification("$name 移除了成员 ${users[(message.content as TdApi.MessageChatDeleteMember).userId]?.username}")
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

            is TdApi.MessageChatChangePhoto -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                BubbleGray.copy(alpha = 0.5f),
                                RoundedCornerShape(6.dp)
                            )
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "$name 更改聊天照片为",
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
                            fontFamily = miSans,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    TgImage(
                        file = (message.content as TdApi.MessageChatChangePhoto).photo.sizes.first().photo,
                        thumbnail = (message.content as TdApi.MessageChatChangePhoto).photo.minithumbnail?.data,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            is TdApi.MessageChatAddMembers -> {
                MessageNotification(
                    "${
                        (message.content as TdApi.MessageChatAddMembers).memberUserIds.joinToString(
                            ", "
                        ) { "${users[it]?.username}" }
                    }加入了群聊"
                )
            }

            else -> {
                DraggableBox(
                    enabled = isActionEnabled,
                    modifier = Modifier.fillMaxWidth(),
                    threshold = 50f,
                    direction = DraggableBoxDirection.SwipeToLeft,
                    onProgressChange = {
                        if (progress > 1.2) {
                            onVibrate()
                        }
                        progress = it
                    },
                    triggerThreshold = 50f * 1.1f,
                    onTriggered = {
                        onReplyMessage(
                            name,
                            message.content.textDescription(users, 12.sp).second.text
                        )
                    }
                ) {
                    when (message.content) {
                        else -> {
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
                                    settings = settings,
                                    onLocateToRepliedMessage = onLocateToMessage
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
                                    settings = settings,
                                    onLocateToRepliedMessage = onLocateToMessage
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun TdApi.Message.isDisplayedAsSmallCard(): Boolean {
    return when (this.content) {
        is TdApi.MessageChatJoinByLink -> {
            true
        }

        is TdApi.MessageChatJoinByRequest -> {
            true
        }

        is TdApi.MessageChatDeleteMember -> {
            true
        }

        is TdApi.MessageExpiredPhoto -> {
            true
        }

        is TdApi.MessageExpiredVideo -> {
            true
        }

        is TdApi.MessageExpiredVideoNote -> {
            true
        }

        is TdApi.MessageExpiredVoiceNote -> {
            true
        }

        is TdApi.MessageChatChangeTitle -> {
            true
        }

        is TdApi.MessageChatAddMembers -> {
            true
        }

        else -> false
    }
}