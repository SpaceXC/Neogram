package cn.spacexc.neogram.ui.screen.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.TgAnimation
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.component.TgRichText
import cn.spacexc.neogram.ui.component.TgSticker
import cn.spacexc.neogram.ui.component.TgVideo
import cn.spacexc.neogram.ui.component.TgVoiceNote
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.BubbleGray
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.formatTimestamp
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.neogram.utils.toDateStr
import cn.spacexc.neogram.utils.username
import cn.spacexc.telegram.ui.component.clickAlpha
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import kotlin.math.absoluteValue

@Serializable
data class MessagesScreen(val chatId: Long, val title: String)

@Composable
fun MessagesScreen(navController: NavController, chatId: Long, title: String) {
    val viewModel = viewModel { MessagesViewModel(chatId) }
    val lazyState = rememberLazyListState()
    var textHeight by remember { mutableStateOf(0.dp) }
    var inputBarHeight by remember { mutableStateOf(0.dp) }
    val scope = rememberCoroutineScope()
    val users by UserRepository.users.collectAsState(emptyMap())
    val chats by ChatListRepository.chats.collectAsState()
    val chatActions by ChatListRepository.chatActions.collectAsState()
    val currentUserId by UserRepository.currentUserId.collectAsState()
    var prevFirstMessageId = remember { 0L }
    LaunchedEffect(viewModel.messages) {
        if (viewModel.messages.isNotEmpty()) {
            if (viewModel.messages.entries.first().key != prevFirstMessageId) {
                prevFirstMessageId = viewModel.messages.entries.first().key
                if (lazyState.firstVisibleItemIndex <= 1) {
                    scope.launch {
                        lazyState.animateScrollToItem(0)
                    }
                }
            }
        }
    }
    val localDensity = LocalDensity.current
    val currentChat = chats[chatId]
    val action = chatActions[chatId]
    val lastReadMessage = currentChat?.lastReadOutboxMessageId

    val chatState = if (action != null && action.action !is TdApi.ChatActionCancel) {
        /**
         * ChatActionTyping.CONSTRUCTOR,
         * ChatActionRecordingVideo.CONSTRUCTOR,
         * ChatActionUploadingVideo.CONSTRUCTOR,
         * ChatActionRecordingVoiceNote.CONSTRUCTOR,
         * ChatActionUploadingVoiceNote.CONSTRUCTOR,
         * ChatActionUploadingPhoto.CONSTRUCTOR,
         * ChatActionUploadingDocument.CONSTRUCTOR,
         * ChatActionChoosingSticker.CONSTRUCTOR,
         * ChatActionChoosingLocation.CONSTRUCTOR,
         * ChatActionChoosingContact.CONSTRUCTOR,
         * ChatActionStartPlayingGame.CONSTRUCTOR,
         * ChatActionRecordingVideoNote.CONSTRUCTOR,
         * ChatActionUploadingVideoNote.CONSTRUCTOR,
         * ChatActionWatchingAnimations.CONSTRUCTOR,
         */
        val actionSenderName = if (action.senderId is TdApi.MessageSenderUser) {
            val actionUser = users[action.senderId.userId]
            actionUser?.tgUser?.firstName ?: ""
        } else {
            val actionChat = chats[(action.senderId as TdApi.MessageSenderChat).chatId]
            actionChat?.title
        }
        val actionName = when (action.action) {
            is TdApi.ChatActionTyping -> "正输入"
            is TdApi.ChatActionRecordingVideo -> "正录制视频"
            is TdApi.ChatActionUploadingVideo -> "正上传视频"
            is TdApi.ChatActionRecordingVoiceNote -> "正录制语音"
            is TdApi.ChatActionUploadingVoiceNote -> "正上传语音"
            is TdApi.ChatActionUploadingPhoto -> "正上传照片"
            is TdApi.ChatActionUploadingDocument -> "正上传文件"
            is TdApi.ChatActionChoosingSticker -> "正挑选贴纸"
            is TdApi.ChatActionChoosingLocation -> "正选择定位"
            is TdApi.ChatActionRecordingVideoNote -> "正录制视频"
            is TdApi.ChatActionUploadingVideoNote -> "正上传视频"
            else -> ""
        }
        if (actionSenderName.isNullOrEmpty() || currentChat?.type is TdApi.ChatTypePrivate) actionName else "$actionSenderName${actionName.lowercase()}"
    } else {
        if (currentChat?.type is TdApi.ChatTypePrivate) {
            val userId = (currentChat.type as TdApi.ChatTypePrivate).userId
            val currentUser = users[userId]
            val status = currentUser?.status
            /**
             * UserStatusEmpty.CONSTRUCTOR,
             * UserStatusOnline.CONSTRUCTOR,
             * UserStatusOffline.CONSTRUCTOR,
             * UserStatusRecently.CONSTRUCTOR,
             * UserStatusLastWeek.CONSTRUCTOR,
             * UserStatusLastMonth.CONSTRUCTOR
             */
            when (status) {
                is TdApi.UserStatusOnline -> "在线"
                is TdApi.UserStatusOffline -> "${formatTimestamp(status.wasOnline.toLong())}在线"
                is TdApi.UserStatusRecently -> "刚才在线"
                is TdApi.UserStatusLastWeek -> "上周曾在线"
                is TdApi.UserStatusLastMonth -> "上个月曾在线"
                else -> ""
            }
        } else ""
    }

    /*Text("", //Placeholder for calculating text height
        color = Color.Transparent,
        fontFamily = miSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.onSizeChanged {
            textHeight = with(localDensity) { it.height.toDp() }
        }
    )*/

    val inputValue = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(
            "inputValue",
            if (currentChat?.draftMessage?.inputMessageText is TdApi.InputMessageText) (currentChat.draftMessage?.inputMessageText as TdApi.InputMessageText).text.text else ""
        )?.collectAsState()

    TitleFrame(
        title,
        timeText = chatState,
        onTitleClicked = {},
        onActionClicked = navController::navigateUp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = it,
                    bottom = inputBarHeight + 6.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
                reverseLayout = true,
                state = lazyState
            ) {
                viewModel.messages.entries.toList().forEachIndexed { index, (messageId, message) ->
                    item(key = messageId) {
                        val isNextOneContinuous =
                            if (index == 0) false   //上一条消息是连续的吗？i.e. 是同一个人发的且相隔时间不超过5分钟吗？
                            else {
                                val nextItem = viewModel.messages.entries.toList()[index - 1]
                                if ((message.date - nextItem.value.date).absoluteValue > 5 * 60) false
                                else if (nextItem.value.senderId.constructor != message.senderId.constructor) {
                                    false
                                } else {
                                    if (message.senderId is TdApi.MessageSenderUser) {
                                        (message.senderId as TdApi.MessageSenderUser).userId == (nextItem.value.senderId as TdApi.MessageSenderUser).userId
                                    } else {
                                        (message.senderId as TdApi.MessageSenderChat).chatId == (nextItem.value.senderId as TdApi.MessageSenderChat).chatId
                                    }
                                }
                            }
                        val isPreviousOneContinuous =   //同上
                            if (index == viewModel.messages.entries.size - 1) false
                            else {
                                val nextItem = viewModel.messages.entries.toList()[index + 1]
                                if ((message.date - nextItem.value.date).absoluteValue > 5 * 60) false
                                else if (nextItem.value.senderId.constructor != message.senderId.constructor) {
                                    false
                                } else {
                                    if (message.senderId is TdApi.MessageSenderUser) {
                                        (message.senderId as TdApi.MessageSenderUser).userId == (nextItem.value.senderId as TdApi.MessageSenderUser).userId
                                    } else {
                                        (message.senderId as TdApi.MessageSenderChat).chatId == (nextItem.value.senderId as TdApi.MessageSenderChat).chatId
                                    }
                                }
                            }
                        LaunchedEffect(Unit) {
                            viewModel.viewMessage(messageId)
                        }
                        MessageCard(
                            currentChat?.type != null && (currentChat.type is TdApi.ChatTypeBasicGroup || currentChat.type is TdApi.ChatTypeSupergroup),
                            users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                            chats,
                            modifier = Modifier.animateItem(),
                            message,
                            isPreviousOneContinuous,
                            isNextOneContinuous,
                            viewModel.messages,
                            messageId <= (lastReadMessage ?: (messageId + 1)),
                            message.senderId is TdApi.MessageSenderUser && (message.senderId as TdApi.MessageSenderUser).userId == currentUserId
                        )
                    }
                    item {
                        LaunchedEffect(Unit) {
                            viewModel.getMessages()
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    //.background(Color.Green)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .onSizeChanged { inputBarHeight = with(localDensity) { it.height.toDp() } },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                var barHeight by remember { mutableStateOf(0.dp) }
                Box(
                    modifier = Modifier
                        .onSizeChanged { barHeight = with(localDensity) { it.height.toDp() } }
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(InputBarGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(3.5f)
                        .height(barHeight)
                        .background(InputBarGray, CircleShape)
                        .padding(horizontal = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clickAlpha {
                                navController.navigate(
                                    SendMessageScreen(
                                        chatId,
                                        inputValue?.value ?: "",
                                        0
                                    )
                                )
                            }
                            .alpha(if (inputValue?.value.isNullOrEmpty()) 0.6f else 1f)
                            .align(Alignment.CenterStart),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(cn.spacexc.neogram.R.drawable.icon_new_message),
                            contentDescription = null,
                            modifier = Modifier.size(with(localDensity) { 18.sp.toDp() }),
                            tint = Color.White
                        )
                        Text(
                            if (inputValue?.value.isNullOrEmpty()) "发送消息" else inputValue.value,
                            fontFamily = miSans,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessageCard(
    isGroupChat: Boolean,
    users: Map<Long, TdApi.User>,
    chats: Map<Long, TdApi.Chat>,
    modifier: Modifier = Modifier,
    message: TdApi.Message,
    isPreviousOneContinuous: Boolean,
    isNextOneContinuous: Boolean,
    messages: Map<Long, TdApi.Message>,
    isRead: Boolean,
    senderIsMe: Boolean
) {
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

    Spacer(Modifier.height(if (isNextOneContinuous) 1.dp else 8.dp))

    Column(horizontalAlignment = if (senderIsMe) Alignment.End else Alignment.Start) {
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
                ReplyContent(reply, messages, users)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (senderIsMe) Arrangement.End else Arrangement.Start
        ) {
            if (shouldMessageDisplayedInABox) {
                ChatBubble(modifier, senderIsMe, isPreviousOneContinuous, isNextOneContinuous) {
                    Column(
                        horizontalAlignment = if (senderIsMe) Alignment.End else Alignment.Start,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        message.forwardInfo?.let {
                            MessageForwardInfo(it, chats, users)
                        }
                        message.replyTo?.let { reply ->
                            ReplyContent(reply, messages, users)
                        }
                        MessageContent(message.content, users)
                        Row(
                            modifier = Modifier
                                .alpha(0.7f)
                                .padding(top = 2.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                (message.date * 1000L).toDateStr("hh:mm a"),
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
                    MessageContent(message.content, users)

                    Box(
                        modifier = Modifier
                            .padding(bottom = 3.dp, start = 3.dp)
                            .align(Alignment.BottomStart)
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
        //Text("Previous $isPreviousOneContinuous", color = Color.White)
    }
}

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    senderIsMe: Boolean,
    isPreviousOneContinuous: Boolean,
    isNextOneContinuous: Boolean,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.then(
            if (senderIsMe) {
                Modifier
                    .padding(start = 4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = if (isPreviousOneContinuous) 3.dp else 15.dp,
                            bottomStart = 15.dp,
                            bottomEnd = if (isNextOneContinuous) 3.dp else 15.dp
                        )
                    )
                    .background(NeoBlue)
            } else {
                Modifier
                    .padding(end = 4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (isPreviousOneContinuous) 3.dp else 15.dp,
                            topEnd = 15.dp,
                            bottomStart = if (isNextOneContinuous) 3.dp else 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .background(BubbleGray)
            }
        )
    ) { content() }
}

@Composable
fun ReplyContent(
    reply: TdApi.MessageReplyTo, messages: Map<Long, TdApi.Message>, users: Map<Long, TdApi.User>
) {
    val localDensity = LocalDensity.current
    var textHeight by remember { mutableStateOf(0.dp) }
    Row(
        modifier = Modifier
            .alpha(0.7f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(end = 2.dp)
                .width(1.5.dp)
                .height(textHeight - 1.dp)
                .background(Color.White)
        )
        if (reply is TdApi.MessageReplyToMessage) {
            if (reply.content == null) {
                messages[reply.messageId]?.let { message ->
                    Text(
                        message.content.textDescription(users, 12.sp).second,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.onSizeChanged {
                            textHeight = with(localDensity) { it.height.toDp() }
                        },
                        fontFamily = miSans
                    )
                }
            } else {
                Text(
                    reply.content.textDescription(users, 12.sp).second,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.onSizeChanged {
                        textHeight = with(localDensity) { it.height.toDp() }
                    },
                    fontFamily = miSans
                )
            }
        }
    }
}

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

@Composable
fun MessageContent(
    content: TdApi.MessageContent,
    users: Map<Long, TdApi.User>,
) {
    /**
     * MessageText.CONSTRUCTOR, //WIP
     * MessageAnimation.CONSTRUCTOR,
     * MessageAudio.CONSTRUCTOR,
     * MessageDocument.CONSTRUCTOR,
     * MessagePaidMedia.CONSTRUCTOR,
     * MessagePhoto.CONSTRUCTOR,
     * MessageSticker.CONSTRUCTOR,
     * MessageVideo.CONSTRUCTOR,
     * MessageVideoNote.CONSTRUCTOR,
     * MessageVoiceNote.CONSTRUCTOR,
     * MessageExpiredPhoto.CONSTRUCTOR,
     * MessageExpiredVideo.CONSTRUCTOR,
     * MessageExpiredVideoNote.CONSTRUCTOR,
     * MessageExpiredVoiceNote.CONSTRUCTOR,
     * MessageLocation.CONSTRUCTOR,
     * MessageVenue.CONSTRUCTOR,
     * MessageContact.CONSTRUCTOR,
     * MessageAnimatedEmoji.CONSTRUCTOR,
     * MessageDice.CONSTRUCTOR,
     * MessageGame.CONSTRUCTOR,
     * MessagePoll.CONSTRUCTOR,
     * MessageStory.CONSTRUCTOR,
     * MessageInvoice.CONSTRUCTOR,
     * MessageCall.CONSTRUCTOR,
     * MessageVideoChatScheduled.CONSTRUCTOR,
     * MessageVideoChatStarted.CONSTRUCTOR,
     * MessageVideoChatEnded.CONSTRUCTOR,
     * MessageInviteVideoChatParticipants.CONSTRUCTOR,
     * MessageBasicGroupChatCreate.CONSTRUCTOR,
     * MessageSupergroupChatCreate.CONSTRUCTOR,
     * MessageChatChangeTitle.CONSTRUCTOR,
     * MessageChatChangePhoto.CONSTRUCTOR,
     * MessageChatDeletePhoto.CONSTRUCTOR,
     * MessageChatAddMembers.CONSTRUCTOR,
     * MessageChatJoinByLink.CONSTRUCTOR,
     * MessageChatJoinByRequest.CONSTRUCTOR,
     * MessageChatDeleteMember.CONSTRUCTOR,
     * MessageChatUpgradeTo.CONSTRUCTOR,
     * MessageChatUpgradeFrom.CONSTRUCTOR,
     * MessagePinMessage.CONSTRUCTOR,
     * MessageScreenshotTaken.CONSTRUCTOR,
     * MessageChatSetBackground.CONSTRUCTOR,
     * MessageChatSetTheme.CONSTRUCTOR,
     * MessageChatSetMessageAutoDeleteTime.CONSTRUCTOR,
     * MessageChatBoost.CONSTRUCTOR,
     * MessageForumTopicCreated.CONSTRUCTOR,
     * MessageForumTopicEdited.CONSTRUCTOR,
     * MessageForumTopicIsClosedToggled.CONSTRUCTOR,
     * MessageForumTopicIsHiddenToggled.CONSTRUCTOR,
     * MessageSuggestProfilePhoto.CONSTRUCTOR,
     * MessageCustomServiceAction.CONSTRUCTOR,
     * MessageGameScore.CONSTRUCTOR,
     * MessagePaymentSuccessful.CONSTRUCTOR,
     * MessagePaymentSuccessfulBot.CONSTRUCTOR,
     * MessagePaymentRefunded.CONSTRUCTOR,
     * MessageGiftedPremium.CONSTRUCTOR,
     * MessagePremiumGiftCode.CONSTRUCTOR,
     * MessageGiveawayCreated.CONSTRUCTOR,
     * MessageGiveaway.CONSTRUCTOR,
     * MessageGiveawayCompleted.CONSTRUCTOR,
     * MessageGiveawayWinners.CONSTRUCTOR,
     * MessageGiftedStars.CONSTRUCTOR,
     * MessageGiveawayPrizeStars.CONSTRUCTOR,
     * MessageGift.CONSTRUCTOR,
     * MessageUpgradedGift.CONSTRUCTOR,
     * MessageRefundedUpgradedGift.CONSTRUCTOR,
     * MessageContactRegistered.CONSTRUCTOR,
     * MessageUsersShared.CONSTRUCTOR,
     * MessageChatShared.CONSTRUCTOR,
     * MessageBotWriteAccessAllowed.CONSTRUCTOR,
     * MessageWebAppDataSent.CONSTRUCTOR,
     * MessageWebAppDataReceived.CONSTRUCTOR,
     * MessagePassportDataSent.CONSTRUCTOR,
     * MessagePassportDataReceived.CONSTRUCTOR,
     * MessageProximityAlertTriggered.CONSTRUCTOR,
     * MessageUnsupported.CONSTRUCTOR
     */
    when (content) {
        is TdApi.MessageText -> {
            TgRichText(
                content.text.entities.toList(),
                content.text.text
            )
        }

        is TdApi.MessagePhoto -> {
            val thumbnail = content.photo.minithumbnail?.data
            val file = content.photo.sizes.last().photo
            //TODO caption & secret photo
            val aspectRatio =
                content.photo.sizes.last().width.toFloat() / content.photo.sizes.last().height.toFloat()
            TgImage(
                file, thumbnail, modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio)
            )
            if (content.caption.text.isNotEmpty()) {
                TgRichText(
                    content.caption.entities.toList(),
                    content.caption.text,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                )
            }
        }

        is TdApi.MessageSticker -> {
            val aspectRatio = content.sticker.width.toFloat() / content.sticker.height.toFloat()
            TgSticker(
                sticker = content.sticker,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio)
            )
        }

        is TdApi.MessageAnimation -> {
            val aspectRatio = content.animation.width.toFloat() / content.animation.height.toFloat()
            TgAnimation(
                content.animation, modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio)
            )
        }

        is TdApi.MessageAnimatedEmoji -> {
            val aspectRatio =
                content.animatedEmoji.stickerWidth.toFloat() / content.animatedEmoji.stickerHeight.toFloat()
            content.animatedEmoji.sticker?.let {
                TgSticker(
                    it, modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(aspectRatio)
                )
            }
        }

        is TdApi.MessageVideo -> {
            val aspectRatio = content.video.width.toFloat() / content.video.height.toFloat()
            TgVideo(
                content.video.video, modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio)
            )
            if (content.caption.text.isNotEmpty()) {
                TgRichText(
                    content.caption.entities.toList(),
                    content.caption.text,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                )
            }
        }

        is TdApi.MessageVoiceNote -> {
            TgVoiceNote(content.voiceNote.voice, modifier = Modifier)
            if (content.caption.text.isNotEmpty()) {
                TgRichText(
                    content.caption.entities.toList(),
                    content.caption.text,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                )
            }
        }

        else -> {
            Text(
                "Unsupported message type ${content.javaClass.name} but here is an description: ${
                    content.textDescription(
                        users, 14.sp
                    )
                }", color = NeoBlue, modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}