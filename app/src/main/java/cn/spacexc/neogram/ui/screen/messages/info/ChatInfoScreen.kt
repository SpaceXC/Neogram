package cn.spacexc.neogram.ui.screen.messages.info

import android.os.Build
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.proto.settings.ChatItemStyle
import cn.spacexc.neogram.proto.settings.copy
import cn.spacexc.neogram.settings.NeogramSettings.neogramSettings
import cn.spacexc.neogram.settings.updateConfiguration
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.component.TgChatAvatar
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.component.TgUserAvatar
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import cn.spacexc.neogram.ui.icons.Account
import cn.spacexc.neogram.ui.icons.AccountBox
import cn.spacexc.neogram.ui.icons.Call
import cn.spacexc.neogram.ui.icons.Edit
import cn.spacexc.neogram.ui.icons.More
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Saved
import cn.spacexc.neogram.ui.icons.Settings
import cn.spacexc.neogram.ui.screen.call.VoiceCallScreen
import cn.spacexc.neogram.ui.screen.settings.main.SettingsItem
import cn.spacexc.neogram.ui.screen.settings.main.SettingsScreen
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.username
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.thunderdog.challegram.voip.VoIP

@Serializable
data class ChatInfoScreen(val chatId: Long)

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun ChatInfoScreen(
    navController: NavController,
    chatId: Long
) {
    val localDensity = LocalDensity.current
    val chats by ChatListRepository.chats.collectAsState()
    val currentChat = remember { chats[chatId] }
    var containerWidth by remember { mutableStateOf(0.dp) }
    var containerWidthPx by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    var hasShadow by remember { mutableStateOf(false) }

    TitleFrame(
        "聊天详情",
        onActionClicked = navController::navigateUp,
        onTitleClicked = {},
        titleShadow = hasShadow
    ) { topPadding ->
        val scrollPassedThreshold by remember {
            derivedStateOf {
                scrollState.value * 2 > containerWidthPx - with(localDensity) { topPadding.toPx() * 2 } //i know this supposes to be exactly the same as that in the MenuScreen
                //but i just cant figure out why the scroll state here is two times bigger......
            }
        } //滚动超过一定界限的时候切换头像样式

        LaunchedEffect(scrollPassedThreshold) {
            hasShadow = scrollPassedThreshold   //i wonder if there's a better way to do this
        }
        currentChat?.let { chat ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                val photo = chat.photo
                SharedTransitionLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged {
                            containerWidth = with(localDensity) { it.width.toDp() }
                            containerWidthPx = it.width
                        }
                        .then(
                            if (photo != null) Modifier.height(containerWidth + 20.dp) else Modifier.padding(
                                top = topPadding
                            )
                        )
                ) {
                    AnimatedContent(
                        scrollPassedThreshold,
                        modifier = Modifier.fillMaxSize()
                    ) { hasPassed ->
                        Box {
                            if (photo != null && !hasPassed) {
                                Box(
                                    modifier = Modifier.sharedElement(
                                        rememberSharedContentState("avatar"),
                                        this@AnimatedContent
                                    )
                                ) {
                                    TgImage(
                                        photo.big,
                                        photo.minithumbnail?.data,
                                        modifier = Modifier
                                            .size(containerWidth)
                                            .alpha(1f)
                                            .graphicsLayer {
                                                compositingStrategy =
                                                    CompositingStrategy.Offscreen
                                            }
                                            .drawWithContent {
                                                drawContent()
                                                drawRect(
                                                    brush = Brush.verticalGradient(
                                                        0f to Color.Black,
                                                        0.6f to Color.Black,
                                                        1f to Color.Transparent   //???
                                                    ),
                                                    blendMode = BlendMode.DstIn,
                                                )
                                            }
                                    )
                                    if (Build.VERSION.SDK_INT >= 31) {
                                        TgImage(
                                            photo.big,
                                            photo.minithumbnail?.data,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .blur(20.dp)
                                                .padding(bottom = 20.dp)
                                                .alpha(1f)
                                                .size(containerWidth)
                                                .graphicsLayer {
                                                    compositingStrategy =
                                                        CompositingStrategy.Offscreen
                                                }
                                                .drawWithContent {
                                                    drawContent()
                                                    drawRect(
                                                        brush = Brush.verticalGradient(
                                                            0f to Color.Transparent,
                                                            0.9f to Color.Black
                                                        ),
                                                        blendMode = BlendMode.DstIn,
                                                    )
                                                }
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(bottom = 20.dp)
                                        .padding(horizontal = 11.dp),
                                ) {
                                    Text(
                                        chat.title,
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = miSans,
                                        modifier = Modifier
                                            .sharedBounds(
                                                rememberSharedContentState("username"),
                                                this@AnimatedContent
                                            ),
                                        style = TextStyle(
                                            shadow = Shadow(
                                                Color.Black.copy(alpha = 0.7f),
                                                blurRadius = 15f
                                            )
                                        )
                                    )
                                    /*Text(
                                        "${chat.ac}",
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .alpha(0.7f)
                                            .sharedBounds(
                                                rememberSharedContentState("@username"),
                                                this@AnimatedContent
                                            ),
                                        fontFamily = miSans,
                                        style = TextStyle(
                                            shadow = Shadow(
                                                Color.Black.copy(alpha = 0.7f),
                                                blurRadius = 15f
                                            )
                                        )
                                    )*/
                                }
                            } else {
                                NeoCard(
                                    shape = RoundedCornerShape(20.dp),
                                    background = InputBarGray,
                                    borderAlpha = 0.1f,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .align(Alignment.BottomCenter)//.padding(top = containerWidth * 0.6f + topPadding)
                                ) {
                                    var textHeight by remember { mutableStateOf(0.dp) }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .sharedElement(
                                                rememberSharedContentState("avatar"),
                                                this@AnimatedContent
                                            )
                                    ) {
                                        TgChatAvatar(
                                            chat = chat,
                                            avatarSize = textHeight * 1.1f
                                        )
                                        Spacer(Modifier.width(4.dp))
                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .onSizeChanged {
                                                    textHeight =
                                                        with(localDensity) { it.height.toDp() }
                                                }) {
                                            Text(
                                                chat.title,
                                                color = Color.White,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Medium,
                                                fontFamily = miSans,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.sharedBounds(
                                                    rememberSharedContentState("username"),
                                                    this@AnimatedContent
                                                )
                                            )
                                            /*Text(
                                                "@${chat.usernames?.activeUsernames?.firstOrNull() ?: ""}",
                                                color = Color.White,
                                                fontSize = 12.sp,
                                                fontFamily = miSans,
                                                maxLines = 1,
                                                modifier = Modifier
                                                    .sharedBounds(
                                                        rememberSharedContentState("@username"),
                                                        this@AnimatedContent
                                                    )
                                                    .alpha(0.7f)
                                            )*/
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (chat.type is TdApi.ChatTypePrivate) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            NeoCard(
                                shape = RoundedCornerShape(18.dp),
                                background = CardGray,
                                modifier = Modifier.weight(1f).clickVfx(onClick = {
                                    val userId = (currentChat.type as TdApi.ChatTypePrivate).userId
                                    TdClient.send(TdApi.CreateCall(userId, VoIP.getProtocol(), false))
                                }),
                                borderAlpha = 0.03f
                            ) {
                                Icon(
                                    NeogramIcons.Call,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center)
                                        .padding(vertical = 12.dp)
                                        .size(24.dp)
                                )
                            }
                            NeoCard(
                                shape = RoundedCornerShape(18.dp),
                                background = CardGray,
                                modifier = Modifier.fillMaxWidth().weight(1f).fillMaxWidth(),
                                borderAlpha = 0.03f
                            ) {
                                Icon(
                                    NeogramIcons.More,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center)
                                        .padding(vertical = 12.dp)
                                        .size(24.dp)
                                )
                            }
                        }
                    }
                    SettingsItem(
                        leadingIcon = NeogramIcons.AccountBox,
                        itemName = "联系人",
                        shape = RoundedCornerShape(18.dp)
                    )
                    SettingsItem(
                        leadingIcon = NeogramIcons.Call,
                        itemName = "通话",
                        shape = RoundedCornerShape(18.dp)
                    )
                    SettingsItem(
                        leadingIcon = NeogramIcons.Saved,
                        itemName = "保存的消息",
                        shape = RoundedCornerShape(18.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(bottom = 6.dp)
                    ) {
                        NeoCard(
                            shape = RoundedCornerShape(40),
                            background = InputBarGray,
                            borderAlpha = 0.03f,
                            modifier = Modifier
                                .weight(1f)
                                .clickVfx {
                                    navController.navigate(SettingsScreen)
                                }
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    NeogramIcons.Settings,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(24.dp)
                                        .align(Alignment.Center)
                                )

                            }
                        }
                        NeoCard(
                            shape = RoundedCornerShape(40),
                            background = InputBarGray,
                            borderAlpha = 0.03f,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    NeogramIcons.Account,
                                    null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(24.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    /*Column(modifier = modifier.verticalScroll(rememberScrollState())) {

    }*/
}