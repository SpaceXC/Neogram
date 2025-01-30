package cn.spacexc.neogram.ui.screen.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
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
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.formatTimestamp
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.neogram.utils.username
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data class MessagesScreen(val chatId: Long, val title: String)

@Composable
fun MessagesScreen(navController: NavController, chatId: Long, title: String) {
    val viewModel = viewModel { MessagesViewModel(chatId) }
    val lazyState = rememberLazyListState()
    var textHeight by remember { mutableStateOf(0.dp) }
    val scope = rememberCoroutineScope()
    val users by UserRepository.users.collectAsState(emptyMap())
    val chats by ChatListRepository.chats.collectAsState()
    val chatActions by ChatListRepository.chatActions.collectAsState()
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
        when(action.action) {
            is TdApi.ChatActionTyping -> "Typing..."
            is TdApi.ChatActionRecordingVideo -> "Recording a video..."
            is TdApi.ChatActionUploadingVideo -> "Uploading a video..."
            is TdApi.ChatActionRecordingVoiceNote -> "Recording a voice note..."
            is TdApi.ChatActionUploadingVoiceNote -> "Uploading a voice note..."
            is TdApi.ChatActionUploadingPhoto -> "Uploading a photo..."
            is TdApi.ChatActionUploadingDocument -> "Uploading a document..."
            is TdApi.ChatActionChoosingSticker -> "Choosing a sticker..."
            is TdApi.ChatActionChoosingLocation -> "Choosing a location..."
            is TdApi.ChatActionRecordingVideoNote -> "Recording a video..."
            is TdApi.ChatActionUploadingVideoNote -> "Uploading a video"
            else -> ""
        }
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
            when(status) {
                is TdApi.UserStatusOnline -> "Online"
                is TdApi.UserStatusOffline -> "Last seen ${formatTimestamp(status.wasOnline.toLong())}"
                is TdApi.UserStatusRecently -> "Last seen recently"
                is TdApi.UserStatusLastWeek -> "Last seen last week"
                is TdApi.UserStatusLastMonth -> "Last seen last month"
                else -> ""
            }
        }
        else ""
    }

    Text(
        "", //Placeholder for calculating text height
        color = Color.Transparent,
        fontFamily = miSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.onSizeChanged {
            textHeight = with(localDensity) { it.height.toDp() }
        }
    )
    TitleFrame(title, timeText = chatState, onTitleClicked = {}, onActionClicked = navController::navigateUp
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            reverseLayout = true,
            state = lazyState
        ) {
            viewModel.messages.entries.toList().forEachIndexed { index, (messageId, message) ->
                item(key = messageId) {
                    val shouldDisplayFull = if (index == viewModel.messages.entries.size - 1) {
                        true
                    } else {
                        val prevItem = viewModel.messages.entries.toList()[index + 1]
                        if (prevItem.value.senderId.constructor != message.senderId.constructor) {
                            true
                        } else {
                            if (prevItem.value.senderId is TdApi.MessageSenderChat) true
                            else {
                                (prevItem.value.senderId as TdApi.MessageSenderUser).userId != (message.senderId as TdApi.MessageSenderUser).userId
                            }
                        }
                    }
                    LaunchedEffect(Unit) {
                        viewModel.viewMessage(messageId)
                    }
                    MessageCard(
                        users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                        chats,
                        modifier = Modifier.animateItem(),
                        message,
                        textHeight,
                        shouldDisplayFull,
                        viewModel.messages,
                        messageId <= (lastReadMessage ?: (messageId + 1))
                    )
                }
                item {
                    LaunchedEffect(Unit) {
                        viewModel.getMessages()
                    }
                }
            }
        }
    }
}

@Composable
fun MessageCard(
    users: Map<Long, TdApi.User>,
    chats: Map<Long, TdApi.Chat>,
    modifier: Modifier = Modifier,
    message: TdApi.Message,
    textHeight: Dp,
    shouldDisplayFull: Boolean,
    messages: Map<Long, TdApi.Message>,
    isRead: Boolean,
) {
    val colors by AccentColorRepository.colors.collectAsState()
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
            nameColor = colors[chat.accentColorId]?.darkThemeColors?.first()
                ?.let { Color(it).copy(alpha = 1f) }
                ?: Color.White
        }
    }
    if (message.senderId is TdApi.MessageSenderUser) {
        val userId = (message.senderId as TdApi.MessageSenderUser).userId
        users[userId]?.let { user ->
            name = user.username
            photoThumbnail = user.profilePhoto?.minithumbnail?.data
            photoFile = user.profilePhoto?.small
            nameColor = colors[user.accentColorId]?.darkThemeColors?.first()
                ?.let { Color(it).copy(alpha = 1f) }
                ?: Color.White
        }
    }

    if (shouldDisplayFull) {
        Spacer(Modifier.height(4.dp))
    }
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(textHeight * 2)
        ) {
            if (shouldDisplayFull) {
                if (photoThumbnail != null) {
                    TgImage(
                        photoFile!!, //都有缩略图了岂不是包有图的
                        photoThumbnail!!,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(NeoBlue, CircleShape)
                    ) {
                        Text(
                            name.firstOrNull()?.uppercase() ?: "",
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
            } else {
                Spacer(Modifier.width(textHeight * 2))
            }
        }
        Spacer(Modifier.width(4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                if (shouldDisplayFull) {
                    Text(
                        name,
                        color = nameColor,
                        fontFamily = miSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                MessageContent(message.content, message.replyTo, users, messages)
            }
            Text(isRead.toString(), color = Color.White)
        }
    }

    if (shouldDisplayFull) {
        Spacer(Modifier.height(4.dp))
    }
}

@Composable
fun MessageContent(
    content: TdApi.MessageContent,
    replyTo: TdApi.MessageReplyTo?,
    users: Map<Long, TdApi.User>,
    messages: Map<Long, TdApi.Message>
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

    replyTo?.let { reply ->
        if (reply is TdApi.MessageReplyToMessage) {
            if (reply.content == null) {
                messages[reply.messageId]?.let { message ->
                    Text(
                        message.content.textDescription(users, 12.sp).second,
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.alpha(0.7f),
                        fontFamily = miSans
                    )
                }
            } else {
                Text(
                    reply.content.textDescription(users, 12.sp).second,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.alpha(0.7f),
                    fontFamily = miSans
                )
            }
        }
    }

    when (content) {
        is TdApi.MessageText -> {
            TgRichText(content.text.entities.toList(), content.text.text)
        }

        is TdApi.MessagePhoto -> {
            val thumbnail = content.photo.minithumbnail?.data
            val file = content.photo.sizes.last().photo
            //TODO caption & secret photo
            val aspectRatio =
                content.photo.sizes.last().width.toFloat() / content.photo.sizes.last().height.toFloat()
            TgImage(
                file,
                thumbnail,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(aspectRatio)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
            )
            TgRichText(content.caption.entities.toList(), content.caption.text)

        }

        is TdApi.MessageSticker -> {
            val aspectRatio = content.sticker.width.toFloat() / content.sticker.height.toFloat()
            TgSticker(
                sticker = content.sticker,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(aspectRatio)
            )
        }

        is TdApi.MessageAnimation -> {
            val aspectRatio = content.animation.width.toFloat() / content.animation.height.toFloat()
            TgAnimation(
                content.animation,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(aspectRatio)
            )
        }

        is TdApi.MessageAnimatedEmoji -> {
            val aspectRatio =
                content.animatedEmoji.stickerWidth.toFloat() / content.animatedEmoji.stickerHeight.toFloat()
            content.animatedEmoji.sticker?.let {
                TgSticker(
                    it,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(aspectRatio)
                )
            }
        }

        is TdApi.MessageVideo -> {
            val aspectRatio =
                content.video.width.toFloat() / content.video.height.toFloat()
            TgVideo(
                content.video.video, modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(aspectRatio)
            )
            TgRichText(content.caption.entities.toList(), content.caption.text)
        }

        else -> {
            Text(
                "Unsupported message type ${content.javaClass.name} but here is an description: ${
                    content.textDescription(
                        users,
                        14.sp
                    )
                }", color = NeoBlue
            )
        }
    }
}