package cn.spacexc.neogram.ui.screen.messages

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Reply
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.screen.messages.ui.MessageCard
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.formatTimestamp
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.telegram.ui.component.clickAlpha
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Serializable
data class MessagesScreen(val chatId: Long, val title: String)

enum class MessageSwipeToReplyState { Resting, Replying }

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MessagesScreen(
    animatedContentScope: AnimatedContentScope,
    navController: NavController,
    chatId: Long,
    title: String
) {
    //region variables
    var inputBarHeight by remember { mutableStateOf(0.dp) }
    val scope = rememberCoroutineScope()
    val users by UserRepository.users.collectAsState(emptyMap())
    val chats by ChatListRepository.chats.collectAsState()
    val chatActions by ChatListRepository.chatActions.collectAsState()
    val currentUserId by UserRepository.currentUserId.collectAsState()
    val localDensity = LocalDensity.current
    val currentChat = chats[chatId]
    val action = chatActions[chatId]
    val lastReadOutboxMessage = currentChat?.lastReadOutboxMessageId
    val viewModel = viewModel { MessagesViewModel(chatId) }


    LaunchedEffect(viewModel.messages) {
        if (viewModel.messages.isNotEmpty()) {
            if (viewModel.messages.entries.first().key != viewModel.prevFirstMessageId) {
                val message = viewModel.messages.entries.first().value
                viewModel.prevFirstMessageId = message.id
                val messageSenderIsMe =
                    message.senderId is TdApi.MessageSenderUser && (message.senderId as TdApi.MessageSenderUser).userId == currentUserId
                if (viewModel.lazyColumnState.firstVisibleItemIndex <= 1 || messageSenderIsMe) {
                    scope.launch {
                        viewModel.lazyColumnState.animateScrollToItem(0)
                    }
                }
            }
            viewModel.prevFirstMessageId = viewModel.messages.entries.first().key
        }
    }

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
            //region message list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = it,
                    bottom = inputBarHeight + 6.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
                reverseLayout = true,
                state = viewModel.lazyColumnState
            ) {
                viewModel.messages.entries.toList().forEachIndexed { index, (messageId, message) ->
                    item(key = messageId) {
                        //region val isNextOneContinuous
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
                        //endregion
                        //region val isPreviousOneContinuous
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
                        //endregion
                        LaunchedEffect(Unit) {
                            viewModel.viewMessage(messageId)
                        }

                        val senderIsMe =
                            message.senderId is TdApi.MessageSenderUser && (message.senderId as TdApi.MessageSenderUser).userId == currentUserId

                        MessageCard(
                            animatedContentScope = animatedContentScope,
                            isGroupChat = currentChat?.type != null && (currentChat.type is TdApi.ChatTypeBasicGroup || currentChat.type is TdApi.ChatTypeSupergroup),
                            users = users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                            chats = chats,
                            modifier = Modifier.animateItem(),
                            message = message,
                            isPreviousOneContinuous = isPreviousOneContinuous,
                            isNextOneContinuous = isNextOneContinuous,
                            messages = viewModel.messages,
                            isRead = messageId <= (lastReadOutboxMessage ?: (messageId + 1)),
                            senderIsMe = senderIsMe,
                            navController = navController
                        ) { senderName, replyContent ->
                            navController.navigate(
                                SendMessageScreen(
                                    chatId,
                                    inputValue?.value ?: "",
                                    messageId,
                                    senderName,
                                    replyContent
                                )
                            )
                        }
                    }

                    item {
                        LaunchedEffect(Unit) {
                            viewModel.getMessages()
                        }
                    }
                }
            }
            //endregion
            //region voice recording indicator
            var isRecordingAudio by remember { mutableStateOf(false) }
            AnimatedVisibility(
                isRecordingAudio,
                modifier = Modifier.fillMaxSize(),
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .dragAndDropTarget(
                            shouldStartDragAndDrop = { event ->
                                event
                                    .mimeTypes()
                                    .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                            },
                            target = remember {
                                object : DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        isRecordingAudio = false
                                        LogUtils.info("AudioRecord", "Ended")
                                        return true
                                    }
                                }
                            }
                        )
                ) {
                    Box(modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red)
                        .align(Alignment.Center)
                        .dragAndDropTarget(
                            shouldStartDragAndDrop = { event ->
                                event
                                    .mimeTypes()
                                    .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                            },
                            target = remember {
                                object : DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        isRecordingAudio = false
                                        LogUtils.info("AudioRecord", "Canceled")
                                        return true
                                    }
                                }
                            }
                        ))
                }
            }
            //endregion
            //region input bar
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
                        .dragAndDropTarget(
                            shouldStartDragAndDrop = { event ->
                                event
                                    .mimeTypes()
                                    .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                            },
                            target = remember {
                                object : DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        return true
                                    }

                                    override fun onEnded(event: DragAndDropEvent) {
                                        super.onEnded(event)
                                        isRecordingAudio = false
                                        LogUtils.info("AudioRecord", "Ended")
                                    }
                                }
                            }
                        )
                        .dragAndDropSource(drawDragDecoration = {

                        }) {
                            detectTapGestures(
                                onPress = { offset ->
                                    scope.launch {
                                        startTransfer(
                                            transferData = DragAndDropTransferData(
                                                clipData = ClipData.newPlainText(
                                                    "text",
                                                    "Drag me!"
                                                )
                                            )
                                        )
                                    }
                                    isRecordingAudio = true
                                    try {
                                        awaitRelease()
                                        //isRecordingAudio = false
                                    } finally {
                                        //isRecordingAudio = false
                                    }
                                }
                            )
                        }
                        .onSizeChanged { barHeight = with(localDensity) { it.height.toDp() } }
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(InputBarGray, CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.MicNone,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(
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
                        .weight(3.5f)
                        .height(barHeight)
                        .background(InputBarGray, CircleShape)
                        .padding(horizontal = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
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
            //endregion
        }
    }
}