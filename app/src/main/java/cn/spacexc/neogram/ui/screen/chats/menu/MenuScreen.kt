package cn.spacexc.neogram.ui.screen.chats.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.settings.NeogramSettings.neogramSettings
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.component.TgUserAvatar
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.username
import org.drinkless.tdlib.TdApi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MenuScreen(modifier: Modifier = Modifier, navController: NavController, topPadding: Dp) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val localDensity = LocalDensity.current
    val settings by neogramSettings()
    val users by UserRepository.users.collectAsState()
    val currentUserId by UserRepository.currentUserId.collectAsState()
    var tdlibVersion by remember { mutableStateOf("") }
    var containerWidth by remember { mutableStateOf(0.dp) }
    var containerWidthPx by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()

    val scrollPassedThreshold by remember {
        derivedStateOf {
            scrollState.value > containerWidthPx - with(localDensity) { topPadding.toPx() * 2 }
        }
    } //滚动超过一定界限的时候切换头像样式
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.GetOption("version"), {
            tdlibVersion = (it as TdApi.OptionValueString).value
        }, {
            tdlibVersion = it.toString()
        })
    }
    Column(modifier = modifier.verticalScroll(scrollState)) {
        /*Box(
            modifier =
            //.padding(bottom = 20.dp)
            //.aspectRatio(1f)
            //.background(Color.White)
        ) {*/
        users[currentUserId]?.tgUser?.let { currentUser ->
            val photo = currentUser.profilePhoto
            SharedTransitionLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged {
                        containerWidth = with(localDensity) { it.width.toDp() }
                        containerWidthPx = it.width
                    }
                    .height(containerWidth + 20.dp)) {
                AnimatedContent(
                    scrollPassedThreshold,
                    modifier = Modifier.fillMaxSize()
                ) { hasPassed ->
                    Box {
                        if (photo != null && !hasPassed) {
                            Box(modifier = Modifier) {
                                TgImage(
                                    photo.big,
                                    photo.minithumbnail?.data,
                                    modifier = Modifier
                                        .sharedElement(
                                            rememberSharedContentState("avatar"),
                                            this@AnimatedContent
                                        )
                                        .size(containerWidth)
                                        .alpha(0.65f)
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
                                TgImage(
                                    photo.big,
                                    photo.minithumbnail?.data,
                                    modifier = Modifier

                                        .fillMaxWidth()
                                        //.alpha(0f)
                                        .blur(20.dp)
                                        .padding(bottom = 20.dp)
                                        .alpha(0.65f)
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
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(bottom = 20.dp)
                                    .padding(horizontal = 11.dp)
                                    .sharedBounds(
                                        rememberSharedContentState("username"),
                                        this@AnimatedContent
                                    ),

                                ) {
                                Text(
                                    currentUser.username,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = miSans,
                                    modifier = Modifier.sharedBounds(
                                        rememberSharedContentState("@username"),
                                        this@AnimatedContent
                                    )
                                )
                                Text(
                                    "@${currentUser.usernames?.activeUsernames?.firstOrNull() ?: ""}",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.alpha(0.7f),
                                    fontFamily = miSans
                                )
                            }
                        } else {
                            NeoCard(
                                shape = RoundedCornerShape(40),
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
                                    TgUserAvatar(user = currentUser, avatarSize = textHeight * 1.1f)
                                    Spacer(Modifier.width(4.dp))
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .onSizeChanged {
                                                textHeight = with(localDensity) { it.height.toDp() }
                                            }) {
                                        Text(
                                            currentUser.username,
                                            color = Color.White,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = miSans,
                                            maxLines = 1,
                                            modifier = Modifier.sharedBounds(
                                                rememberSharedContentState("username"),
                                                this@AnimatedContent
                                            )
                                        )
                                        Text(
                                            "@${currentUser.usernames?.activeUsernames?.firstOrNull() ?: ""}",
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
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //}
        }
        Text("111", color = Color.White)
        Spacer(Modifier.height(200.dp))
    }
    /*Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            if (settings.chatItemStyle == ChatItemStyle.Minimalist) "Minimalist" else "Bubble",
            modifier = Modifier
                .fillMaxWidth()
                .clickVfx(onClick = {
                    scope.launch {
                        context.updateConfiguration {
                            copy {
                                chatItemStyle =
                                    if (settings.chatItemStyle == ChatItemStyle.Minimalist) ChatItemStyle.Bubble else ChatItemStyle.Minimalist
                            }
                        }
                    }
                }),
            color = Color.White,
            fontFamily = miSans,
            textAlign = TextAlign.Center
        )
        Text(
            "Debug: ${settings.debug}",
            modifier = Modifier
                .fillMaxWidth()
                .clickVfx(onClick = {
                    scope.launch {
                        context.updateConfiguration {
                            copy {
                                debug = !settings.debug
                            }
                        }
                    }
                }),
            color = Color.White,
            fontFamily = miSans,
            textAlign = TextAlign.Center
        )
        Text(
            "SESSIONS",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickVfx {
                    navController.navigate(SessionsScreen)
                },
            color = Color.White,
            fontFamily = miSans,
            textAlign = TextAlign.Center
        )
        Text(
            "tdlib version $tdlibVersion",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = Color.White,
            fontFamily = miSans,
            textAlign = TextAlign.Center
        )
    }*/
}