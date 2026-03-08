package cn.spacexc.neogram.ui.screen.forward

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.icons.Lock
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.screen.chats.ChatListViewModel
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.ToastUtils
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.UserStatusOnline

@Serializable
data class ForwardMessageScreen(
    val messageThreadId: Long,
    val messageChatId: Long,
    val messageId: Long
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ForwardMessageScreen(
    navController: NavController,
    animatedContentScope: AnimatedContentScope,
    viewModel: ChatListViewModel = viewModel(),
    messageThreadId: Long,
    messageChatId: Long,
    messageId: Long
) {
    val chatList by viewModel.chatList.collectAsState(emptyList())

    TitleFrame(
        "转发消息",
        onTitleClicked = {},
        onActionClicked = navController::navigateUp
    ) { topPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = topPadding,
                bottom = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            chatList.forEach { chat ->
                item(key = chat.id) {
                    ChatBriefCard(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .animateItem()
                            .clickVfx {
                                LogUtils.info("Forward!!!", "Forwarding")
                                TdClient.send(
                                    TdApi.ForwardMessages(
                                        chat.id,
                                        messageThreadId,
                                        messageChatId,
                                        longArrayOf(messageId),
                                        null,
                                        false,
                                        false
                                    ),
                                    {
                                        MainScope().launch {
                                            ToastUtils.toast("转发成功")
                                            navController.navigateUp()
                                        }
                                    },
                                    {
                                        MainScope().launch {
                                            ToastUtils.toast("转发失败")
                                        }
                                    }
                                )
                            },
                        chat = chat,
                        animatedContentScope = animatedContentScope,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ChatBriefCard(
    modifier: Modifier = Modifier,
    chat: ChatListRepository.ChatItem,
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
                        if (accentColor == null) SolidColor(NeoMain) else Brush.verticalGradient(
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
                                .background(NeoMain, CircleShape)
                                .padding(0.5.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.width(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .onGloballyPositioned {
                            textHeight = with(localDensity) { it.size.height.toDp() }
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
                        fontSize = 15.sp,
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
                }
            }
        }
    }
}