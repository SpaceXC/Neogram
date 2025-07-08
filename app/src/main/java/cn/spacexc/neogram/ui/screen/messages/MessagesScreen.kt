package cn.spacexc.neogram.ui.screen.messages

import android.Manifest
import android.content.ClipDescription
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
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
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.settings.NeogramSettings.neogramSettings
import cn.spacexc.neogram.ui.component.NeoIconButton
import cn.spacexc.neogram.ui.icons.Back
import cn.spacexc.neogram.ui.icons.ChatBubble
import cn.spacexc.neogram.ui.icons.Delete
import cn.spacexc.neogram.ui.icons.Edit
import cn.spacexc.neogram.ui.icons.Microphone
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.screen.forward.ForwardMessageScreen
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.screen.messages.ui.MessageCard
import cn.spacexc.neogram.ui.screen.messages.ui.isDisplayedAsSmallCard
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.ToastUtils
import cn.spacexc.neogram.utils.getChatActionDescription
import cn.spacexc.telegram.ui.component.clickAlpha
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.thunderdog.challegram.voip.VoIP
import kotlin.math.absoluteValue

@Serializable
data class MessagesScreen(val chatId: Long, val title: String, val haveUnreadMessages: Boolean)

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MessagesScreen(
    animatedContentScope: AnimatedContentScope,
    navController: NavController,
    chatId: Long,
    haveUnreadMessages: Boolean,
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
    var lastReadInboxMessage = remember { currentChat?.lastReadInboxMessageId ?: 0L }
    var scrolledToLastReadInboxMessage by remember { mutableStateOf(false) }
    val viewModel = viewModel { MessagesViewModel(chatId, lastReadInboxMessage) }
    val microphonePermissionRequester =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                viewModel.recordAudio()
            }
        }
    val settings by neogramSettings()
    var isRecordingAudio by remember { mutableStateOf(false) }
    var shouldDisplayUnreadHint by remember { mutableStateOf(haveUnreadMessages) }
    var voiceButtonPosition by remember { mutableStateOf(Offset.Zero) }
    var cancelAreaPosition by remember { mutableStateOf(Offset.Zero) }
    var cancelAreaSize by remember { mutableStateOf(IntSize.Zero) }
    var currentDragOffset by remember { mutableStateOf(Offset.Zero) }

    LaunchedEffect(isRecordingAudio) {
        if (isRecordingAudio) {
            microphonePermissionRequester.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    LaunchedEffect(currentChat?.lastReadInboxMessageId) {
        if (lastReadInboxMessage == 0L && currentChat?.lastReadInboxMessageId != null)
            lastReadInboxMessage = currentChat.lastReadInboxMessageId
    }

    LaunchedEffect(Unit) {
        if (!viewModel.loadCompleted) {
            viewModel.initMessages(haveUnreadMessages)//getMessages(scope)
        }
    }

    LaunchedEffect(viewModel.messages) {
        if (viewModel.loadCompleted) {
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
    }

    val chatState = action.getChatActionDescription(
        users.map { Pair(it.key, it.value.tgUser) }.toMap(),
        chats,
        currentChat?.type
    )

    val inputValue = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(
            "inputValue",
            if (currentChat?.draftMessage?.inputMessageText is TdApi.InputMessageText) (currentChat.draftMessage?.inputMessageText as TdApi.InputMessageText).text.text else ""
        )?.collectAsState()

    TitleFrame(
        title,
        timeText = chatState,
        onTitleClicked = {
            if (currentChat?.type is TdApi.ChatTypePrivate) {
                val userId = (currentChat.type as TdApi.ChatTypePrivate).userId
                TdClient.send(TdApi.CreateCall(userId, VoIP.getProtocol(), false))
            }
        },
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
                        var isInActionMode by remember { mutableStateOf(false) }

                        //region val isNextOneContinuous
                        val isNextOneContinuous =
                            if (index == 0) false   //上一条消息是连续的吗？i.e. 是同一个人发的且相隔时间不超过5分钟且在同一天吗吗？
                            else {
                                val nextItem = viewModel.messages.entries.toList()[index - 1]
                                if ((message.date - nextItem.value.date).absoluteValue > 5 * 60) false  //时间超过5分钟
                                else if (nextItem.value.isDisplayedAsSmallCard()) false //是小卡片（指的是xxx加入了群聊那种
                                else if (nextItem.value.senderId.constructor != message.senderId.constructor) {
                                    false   //不是同一类(chat/user)人发的
                                } else {
                                    if (message.senderId is TdApi.MessageSenderUser) {
                                        (message.senderId as TdApi.MessageSenderUser).userId == (nextItem.value.senderId as TdApi.MessageSenderUser).userId //是不是同一个人发的
                                    } else {
                                        (message.senderId as TdApi.MessageSenderChat).chatId == (nextItem.value.senderId as TdApi.MessageSenderChat).chatId //是不是同一个人发的
                                    }
                                }
                            }
                        //endregion

                        //region val isPreviousOneContinuous
                        val isPreviousOneContinuous =   //同上
                            if (index == viewModel.messages.entries.size - 1) false
                            else {
                                val previousItem = viewModel.messages.entries.toList()[index + 1]
                                if ((message.date - previousItem.value.date).absoluteValue > 5 * 60) false
                                else if (previousItem.value.isDisplayedAsSmallCard()) false
                                else if (previousItem.value.senderId.constructor != message.senderId.constructor) {
                                    false
                                } else {
                                    if (message.senderId is TdApi.MessageSenderUser) {
                                        (message.senderId as TdApi.MessageSenderUser).userId == (previousItem.value.senderId as TdApi.MessageSenderUser).userId
                                    } else {
                                        (message.senderId as TdApi.MessageSenderChat).chatId == (previousItem.value.senderId as TdApi.MessageSenderChat).chatId
                                    }
                                }
                            }
                        //endregion

                        LaunchedEffect(Unit) {
                            viewModel.viewMessage(messageId)
                        }
                        val senderIsMe =
                            message.senderId is TdApi.MessageSenderUser && (message.senderId as TdApi.MessageSenderUser).userId == currentUserId

                        /*Text(
                            settings.toString(),
                            color = Color.White,
                            fontFamily = miSans,
                            fontSize = 10.sp,
                        )*/

                        /*if (message.id == lastReadInboxMessage && shouldDisplayUnreadHint) {
                            Text(
                                "以下是未读消息",
                                color = Color.White,
                                fontFamily = miSans,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(vertical = 2.dp).fillMaxWidth().background(CardGray.copy(alpha = 0.9f)).padding(vertical = 2.dp),
                                textAlign = TextAlign.Center
                            )
                        }*/

                        AnimatedVisibility(
                            isInActionMode,
                            enter = expandVertically() + fadeIn(),
                            exit = shrinkVertically() + fadeOut()
                        ) {
                            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                                if (senderIsMe) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        NeoIconButton(
                                            icon = NeogramIcons.Edit,
                                            modifier = Modifier
                                                .weight(1f)
                                                .clickVfx {
                                                    navController.navigate(
                                                        SendMessageScreen(
                                                            chatId,
                                                            inputValue?.value ?: "",
                                                            0,
                                                            "",
                                                            "",
                                                            message.id,
                                                            (message.content as? TdApi.MessageText)?.text?.text
                                                                ?: ""
                                                        )
                                                    )
                                                }
                                        )
                                        NeoIconButton(
                                            icon = NeogramIcons.Delete,
                                            modifier = Modifier
                                                .weight(1f)
                                                .clickVfx {
                                                    viewModel.deleteMessage(messageId)
                                                }
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    NeoIconButton(
                                        icon = Icons.Outlined.Bookmarks,
                                        iconModifier = Modifier.scale(0.7f),
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickVfx {
                                                //Save Message
                                                scope.launch {
                                                    TdClient.send(
                                                        TdApi.ForwardMessages(
                                                            ChatListRepository.getSavedMessageChatId(),
                                                            message.messageThreadId,
                                                            message.chatId,
                                                            longArrayOf(message.id),
                                                            null,
                                                            false,
                                                            false
                                                        ),
                                                        {
                                                            if (it is TdApi.Messages) {
                                                                ToastUtils.toast("保存成功")
                                                            }
                                                        }
                                                    )
                                                }
                                            }
                                    )
                                    NeoIconButton(
                                        icon = NeogramIcons.Back,
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickVfx {
                                                navController.navigate(
                                                    ForwardMessageScreen(
                                                        message.messageThreadId,
                                                        message.chatId,
                                                        message.id
                                                    )
                                                )
                                            }
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    NeoIconButton(
                                        icon = NeogramIcons.Back,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickVfx {
                                                isInActionMode = false
                                            }
                                    )
                                }
                            }
                        }
                        MessageCard(
                            animatedContentScope = animatedContentScope,
                            isGroupChat = currentChat?.type != null && (currentChat.type is TdApi.ChatTypeBasicGroup || currentChat.type is TdApi.ChatTypeSupergroup),
                            users = users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                            chats = chats,
                            modifier = Modifier
                                .animateItem()
                                .clickAlpha(enabled = !isInActionMode, onLongClick = {
                                    isInActionMode = true
                                }),
                            message = message,
                            isPreviousOneContinuous = isPreviousOneContinuous,
                            isNextOneContinuous = isNextOneContinuous,
                            messages = viewModel.messages,
                            isRead = messageId <= (lastReadOutboxMessage ?: (messageId + 1)),
                            senderIsMe = senderIsMe,
                            settings = settings,
                            onVibrate = {
                                viewModel.vibrate()
                            },
                            navController = navController,
                            onLocateToMessage = { messageToLocate ->
                                if (messageToLocate.chatId == chatId) {
                                    viewModel.locateToMessage(messageToLocate.id, scope)
                                }
                            }
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
                            viewModel.getMessages(scope)
                        }
                    }
                }
            }
            //endregion
            //region voice recording indicator
            AnimatedVisibility(
                isRecordingAudio,
                modifier = Modifier.animateContentSize(),
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
                                        viewModel.stopRecording(true)
                                        LogUtils.info("AudioRecord", "Ended")
                                        return true
                                    }
                                }
                            }
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Red)
                            .align(Alignment.Center)
                            .onGloballyPositioned {
                                cancelAreaSize = it.size
                                cancelAreaPosition = it.localToScreen(Offset.Zero)
                            })
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
                        .onGloballyPositioned {
                            voiceButtonPosition = it.localToScreen(Offset.Zero)
                        }
                        .pointerInput(Unit) {
                            detectDragGesturesAfterLongPress(
                                onDragStart = { offset ->
                                    isRecordingAudio = true
                                    viewModel.recordAudio()
                                    currentDragOffset = offset
                                },
                                onDrag = { change, dragAmount ->
                                    currentDragOffset += dragAmount

                                },
                                onDragEnd = {
                                    isRecordingAudio = false
                                    LogUtils.info("AudioRecording", "$currentDragOffset")


                                    val isInCancelArea =
                                        (currentDragOffset + voiceButtonPosition) within (cancelAreaPosition plus cancelAreaSize)

                                    LogUtils.info("AudioRecording", "$isInCancelArea")

                                    viewModel.stopRecording(!isInCancelArea)

                                },
                                onDragCancel = {

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
                        imageVector = NeogramIcons.Microphone,
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
                            .align(CenterStart),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = NeogramIcons.ChatBubble,
                            contentDescription = null,
                            modifier = Modifier.size(with(localDensity) { 18.sp.toDp() }),
                            tint = Color.White
                        )
                        Text(
                            if (inputValue?.value.isNullOrEmpty()) "发送消息" else inputValue.value,
                            fontFamily = miSans,
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }
            }
            //endregion
            /*if (viewModel.loadCompleted && !scrolledToLastReadInboxMessage && lastReadInboxMessage != viewModel.messages.keys.toList()
                    .first()
            ) {
                Button(
                    {
                        scrolledToLastReadInboxMessage = true
                        val index = viewModel.messages.keys.toList().indexOf(lastReadInboxMessage)
                        scope.launch {
                            viewModel.lazyColumnState.animateScrollToItem(index)
                            //viewModel.lazyColumnState.animateScrollBy(with(localDensity) { it.toPx() })
                        }
                    }, modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(inputBarHeight)
                ) {
                    Text("从上次开始")
                }
            }*/
        }
    }
}

infix fun Offset.within(range: Pair<Offset, Offset>): Boolean {
    if (range.first.x > range.second.x || range.first.y > range.second.y) throw RuntimeException("Range $range illegal")
    return x in range.first.x..range.second.x && y in range.first.y..range.second.y
}

infix fun Offset.plus(size: IntSize): Pair<Offset, Offset> {
    return Pair(this, Offset(this.x + size.width, this.y + size.height))
}