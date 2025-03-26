package cn.spacexc.neogram.ui.screen.chats

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.folders.FoldersRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.component.TgRichText
import cn.spacexc.neogram.ui.icons.Lock
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Options
import cn.spacexc.neogram.ui.icons.People
import cn.spacexc.neogram.ui.screen.chats.menu.MenuScreen
import cn.spacexc.neogram.ui.screen.messages.MessagesScreen
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.NeoRed
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.getChatActionDescription
import cn.spacexc.neogram.utils.textDescription
import cn.spacexc.telegram.ui.component.clickVfx
import cn.spacexc.telegram.ui.component.lazyRotateInput
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Chat
import org.drinkless.tdlib.TdApi.ChatActionCancel
import org.drinkless.tdlib.TdApi.User
import org.drinkless.tdlib.TdApi.UserStatusOnline

@Serializable
object ChatListScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ChatListScreen(
    animatedContentScope: AnimatedContentScope,
    navController: NavController,
    viewModel: ChatListViewModel = viewModel()
) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val chatList by viewModel.chatList.collectAsState(emptyList())
    val folders by FoldersRepository.chatFolderLists.collectAsState()
    val chats by ChatListRepository.chats.collectAsState()
    val users by UserRepository.users.collectAsState()

    var isShowingMenu by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    var currentSelectedFolderId by remember { mutableIntStateOf(0) }

    LaunchedEffect(folders) {
        LogUtils.info("FOLDER", folders.toString())
    }

    val displayedChatList by remember {
        derivedStateOf {
            if (currentSelectedFolderId == 0) chatList
            else {
                val folder = folders[currentSelectedFolderId]
                if (folder == null) emptyList()
                else {
                    LogUtils.info("FOLDER", folder.includedChatIds.toString())
                    folder.includedChatIds.map { includedChatId -> chatList.firstOrNull { it.id == includedChatId } }
                        .mapNotNull { it }
                }
            }
        }
    }

    TitleFrame(
        "Neo",
        actionImage = {
            if (isShowingMenu) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(1.3f)
                )
            } else {
                Icon(
                    imageVector = NeogramIcons.Options,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                    contentDescription = "Menu"
                )
            }
        },
        onActionClicked = {
            isShowingMenu = !isShowingMenu
        },
        onTitleClicked = {
            scope.launch { scrollState.animateScrollToItem(0) }
        }
    ) { topPadding ->
        AnimatedContent(isShowingMenu, transitionSpec = {
            val tweenFloat = tween<Float>(
                durationMillis = 400,
            )
            val tweenIntInOffset = tween<IntOffset>(
                durationMillis = 400,
                easing = CubicBezierEasing(0f, 1f, 0.25f, 1f)
            )
            val tweenIntOutOffset = tween<IntOffset>(
                durationMillis = 250,
                easing = EaseInCubic
            )
            if (!isShowingMenu) {
                (fadeIn(tweenFloat)).togetherWith(slideOutVertically(animationSpec = tweenIntOutOffset) { height -> -height } + fadeOut(
                    tweenFloat
                ))
            } else {
                (slideInVertically(animationSpec = tweenIntInOffset) { height -> -height } + fadeIn(
                    tweenFloat
                )).togetherWith(fadeOut(tweenFloat))
            }
        }) { menu ->
            if (menu) {
                MenuScreen(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .lazyRotateInput(focusRequester, scrollState),
                    contentPadding = PaddingValues(
                        top = topPadding,
                        bottom = 8.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    state = scrollState
                ) {
                    item {
                        ChatFolders(currentSelectedFolderId) {
                            currentSelectedFolderId = it
                        }
                    }

                    displayedChatList.forEach { chat ->
                        item(key = chat.id) {
                            ChatListItem(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .animateItem()
                                    .clickVfx {
                                        navController.navigate(MessagesScreen(chat.id, chat.title))
                                    },
                                chat = chat,
                                chats = chats,
                                users = users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                                animatedContentScope = animatedContentScope,
                            )
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        ChatListRepository.getMainChatList()
    }
}

@Composable
fun ChatFolders(currentFolder: Int, onSelected: (Int) -> Unit) {
    val folders by FoldersRepository.chatFoldersInfo.collectAsState()
    val localDensity = LocalDensity.current
    Row(
        modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val color by animateColorAsState(
            if (currentFolder == 0) NeoBlue else Color.Transparent,
            tween(400)
        )
        Row(
            modifier = Modifier
                .clickVfx(onClick = {
                    onSelected(0)
                })
                .background(CardGray, CircleShape)
                .border(1.dp, color, CircleShape)
                .padding(start = 10.dp, top = 6.dp, bottom = 6.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = NeogramIcons.People,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier.size(with(localDensity) { 23.sp.toDp() })
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "全部",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = miSans,
                color = Color.White
            )
        }
        folders.forEach { folder ->
            FolderItem(
                Modifier.clickVfx(onClick = {
                    onSelected(folder.id)
                }),
                folder,
                localDensity,
                currentFolder == folder.id
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ChatListItem(
    modifier: Modifier = Modifier,
    chat: ChatListRepository.ChatItem,
    chats: Map<Long, Chat>,
    users: Map<Long, User>,
    animatedContentScope: AnimatedContentScope
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
                        animatedContentScope,
                        chat.photo.small, //都有缩略图了岂不是包有图的
                        thumbnailBytes,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        navController = null
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
                                    NeogramIcons.Lock,
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
                        val chatState =
                            chat.chatAction.getChatActionDescription(users, chats, chat.type)
                        Text(
                            text = if (chat.chatAction == null || chat.chatAction?.action is ChatActionCancel) annotatedString else buildAnnotatedString {
                                withStyle(
                                    SpanStyle(color = NeoBlue, fontWeight = FontWeight.Medium)
                                ) {
                                    append(chatState)
                                }
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

@Composable
fun FolderItem(
    modifier: Modifier = Modifier,
    chatFolderInfo: TdApi.ChatFolderInfo,
    localDensity: Density,
    selected: Boolean
) {
    val color by animateColorAsState(if (selected) NeoBlue else Color.Transparent, tween(400))
    Row(
        modifier = modifier
            .background(CardGray, CircleShape)
            .border(1.dp, color, CircleShape)
            .padding(start = 10.dp, top = 6.dp, bottom = 6.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = NeogramIcons.People,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.size(with(localDensity) { 23.sp.toDp() })
        )
        Spacer(Modifier.width(6.dp))
        TgRichText(
            chatFolderInfo.name.text,
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = miSans,
            )
        )
    }
}