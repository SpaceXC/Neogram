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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.textDescription
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Chat
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            chatList.forEach { chat ->
                item(key = chat.id) {
                    ChatListItem(modifier = Modifier.animateItem(), chat = chat)
                }
            }
        }
    }
}

@Composable
fun ChatListItem(modifier: Modifier = Modifier, chat: ChatListRepository.ChatItem) {
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(NeoBlue, CircleShape)
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
            Column(modifier = Modifier.onSizeChanged {
                textHeight = with(localDensity) { it.height.toDp() }
            }) {
                Text(
                    chat.title,
                    color = Color.White,
                    fontFamily = miSans,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                /*val shouldDisplaySenderName = chat.type is TdApi.ChatTypeBasicGroup   //是群组
                        ||  //或者
                        (chat.type is ChatTypeSupergroup && !(chat.type as ChatTypeSupergroup).isChannel)   //是Supergroup且不是频道
                if (shouldDisplaySenderName) {
                    if (chat.lastMessage?.senderId is MessageSenderUser) {
                        val userId = (chat.lastMessage?.senderId as MessageSenderUser).userId
                        Text(
                            "@" + users[userId]?.usernames?.editableUsername.toString(),
                            color = NeoBlue,
                            fontFamily = miSans,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.alpha(.8f)
                        )
                    } else {
                        val chatId = (chat.lastMessage?.senderId as MessageSenderChat).chatId
                        Text(
                            chats[chatId]?.title.toString(),
                            color = Color.White,
                            fontFamily = miSans,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.alpha(.8f)
                        )
                    }
                }*/

                Text(
                    chat.lastMessage?.content.textDescription,
                    color = Color.White,
                    fontFamily = miSans,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.alpha(.8f)
                )
            }
        }
    }
}