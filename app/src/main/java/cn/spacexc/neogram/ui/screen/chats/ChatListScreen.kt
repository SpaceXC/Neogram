package cn.spacexc.neogram.ui.screen.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.screen.messages.MessagesScreen
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.NeoRed
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Chat
import org.drinkless.tdlib.TdApi.ChatActionCancel
import org.drinkless.tdlib.TdApi.ChatTypePrivate
import org.drinkless.tdlib.TdApi.User
import org.drinkless.tdlib.TdApi.UserStatusOnline

@Serializable
object ChatListScreen

fun <T> updateStateListWithDiff(
    stateList: SnapshotStateList<T>,
    newList: List<T>,
    areItemsTheSame: (T, T) -> Boolean,
    areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) {
    Snapshot.apply {
        val currentSize = stateList.size - 1
        val newSize = newList.size - 1

        // 如果当前列表比新列表长，移除多余的元素
        if (currentSize > newSize) {
            stateList.removeRange(newSize, currentSize)
        }
        // 遍历新的列表，更新现有的元素或添加新元素
        for (i in 0..newSize) {
            if (i < currentSize) {
                // 如果元素是相同的，但内容不同，更新内容
                if (!areItemsTheSame(stateList[i], newList[i]) || !areContentsTheSame(
                        stateList[i],
                        newList[i]
                    )
                ) {
                    stateList[i] = newList[i]
                }
            } else {
                // 如果新列表比当前列表长，添加多出来的元素
                stateList.add(newList[i])
            }
        }
    }
}

@Composable
fun ChatListScreen(navController: NavController, viewModel: ChatListViewModel = viewModel()) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val chatList by viewModel.chatList.collectAsState(emptyList())
    val users by UserRepository.users.collectAsState()
    LaunchedEffect(Unit) {
        ChatListRepository.getMainChatList()
    }
    TitleFrame("Neo", onActionClicked = navController::navigateUp, onTitleClicked = {
        scope.launch { scrollState.animateScrollToItem(0) }
    }) { topPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = topPadding,
                bottom = 8.dp,
                start = 8.dp,
                end = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = scrollState
        ) {
            chatList.forEach { chat ->
                item(key = chat.id) {
                    ChatListItem(
                        modifier = Modifier
                            .animateItem()
                            .clickVfx {
                                navController.navigate(MessagesScreen(chat.id, chat.title))
                            },
                        chat = chat,
                        users = users.map { Pair(it.key, it.value.tgUser) }.toMap()
                    )
                }
            }
        }
    }
}

@Composable
fun ChatListItem(
    modifier: Modifier = Modifier,
    chat: ChatListRepository.ChatItem,
    users: Map<Long, User>
) {
    val localDensity = LocalDensity.current
    Box(
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
                        chat.photo.small, //都有缩略图了岂不是包有图的
                        thumbnailBytes,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
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
                        Text(
                            text = if (chat.chatAction == null || chat.chatAction?.action is ChatActionCancel) annotatedString else buildAnnotatedString {
                                append(
                                    when (chat.chatAction!!.action) {
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
                                )
                            },
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
    }
}