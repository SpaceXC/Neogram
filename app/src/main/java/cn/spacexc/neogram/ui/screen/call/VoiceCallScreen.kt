package cn.spacexc.neogram.ui.screen.call

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.data.call.durationFlow
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.component.TgUserAvatar
import cn.spacexc.neogram.ui.icons.Call
import cn.spacexc.neogram.ui.icons.Chat
import cn.spacexc.neogram.ui.icons.Close
import cn.spacexc.neogram.ui.icons.Microphone
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Speaker
import cn.spacexc.neogram.ui.screen.messages.MessagesScreen
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.NeoRed
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.toMinSec
import cn.spacexc.neogram.utils.username
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.thunderdog.challegram.voip.VoIP

@Serializable
data object VoiceCallScreen

const val VOIP_CONNECTION_MIN_LAYER = 65


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun VoiceCallScreen(navController: NavController) {
    val currentCall by CallHandler.currentCall.collectAsState()
    val callInstance by CallHandler.currentInstance.collectAsState()
    val duration by (callInstance?.durationFlow()?.collectAsState(0L)
        ?: remember { mutableLongStateOf(0L) })
    val users by UserRepository.users.collectAsState()
    var isMicrophoneDisabled by remember { mutableStateOf(false) }
    TitleFrame(
        "通话",
        onTitleClicked = {},
        titleShadow = false,
        onActionClicked = navController::navigateUp
    ) {
        val localDensity = LocalDensity.current
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            currentCall?.let { call ->
                val ambientAlpha by animateFloatAsState(
                    if (call.state !is TdApi.CallStatePending && duration > 0) 0.6f else 0f,
                    tween(400)
                )
                val ambientColor by animateColorAsState(
                    when (call.state) {
                        is TdApi.CallStateReady, is TdApi.CallStateHangingUp -> NeoMain
                        is TdApi.CallStateError, is TdApi.CallStateDiscarded -> NeoRed
                        else -> Color.Transparent
                    }
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    var circleHeight by remember {
                        mutableStateOf(0.dp)
                    }
                    Box(
                        modifier = Modifier
                            .offset(y = circleHeight * -0.5f)
                            .fillMaxWidth()
                            .alpha(ambientAlpha)
                            .aspectRatio(1f)
                            .background(
                                shape = CircleShape, brush = Brush.radialGradient(
                                    listOf(ambientColor, Color.Transparent)
                                )
                            )
                            .onSizeChanged {
                                circleHeight = with(localDensity) { it.height.toDp() }
                            }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        //.verticalScroll(rememberScrollState())
                        .padding(top = it * 0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    users[call.userId]?.tgUser?.let { user ->
                        TgUserAvatar(modifier = Modifier.fillMaxWidth(0.4f), user = user)
                        Text(
                            user.username,
                            fontWeight = FontWeight.Bold,
                            fontFamily = miSans,
                            fontSize = 15.sp,
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                    AnimatedContent(
                        call.state,
                        transitionSpec = {
                            // Compare the incoming number with the previous number.
                            slideInVertically { height -> height } + fadeIn() togetherWith
                                    slideOutVertically { height -> -height } + fadeOut()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) { state ->
                        val text = when (state) {
                            is TdApi.CallStatePending -> {
                                if (!state.isCreated) "〇发起通话中"
                                else {
                                    if (state.isReceived) {
                                        if (call.isOutgoing) "〇对方已响铃" else "Telegram Audio"
                                    } else "〇通话已发出"
                                }
                            }

                            is TdApi.CallStateReady -> {
                                if (duration < 0) "〇连接中..." else duration.toMinSec()
                            }

                            is TdApi.CallStateHangingUp -> "正在挂断"
                            is TdApi.CallStateError -> "发生错误"
                            is TdApi.CallStateDiscarded -> "通话结束"
                            is TdApi.CallStateExchangingKeys -> "〇交换密钥"
                            else -> "未知状态"
                        }

                        val textColor = when (state) {
                            is TdApi.CallStatePending -> {
                                if (call.isOutgoing) NeoMain else Color.White.copy(0.7f)
                            }

                            is TdApi.CallStateReady -> {
                                if (duration < 0) NeoMain else Color.White.copy(0.7f)
                            }

                            is TdApi.CallStateDiscarded, is TdApi.CallStateError -> NeoRed
                            else -> NeoMain
                        }

                        Text(
                            text,
                            fontWeight = FontWeight.Medium,
                            fontFamily = miSans,
                            fontSize = 13.sp,
                            color = textColor,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }


                    Spacer(Modifier.weight(1f))
                    val buttonsHeight = remember { 32.dp }
                    SharedTransitionLayout(modifier = Modifier.fillMaxWidth()) {
                        AnimatedContent(call.state, transitionSpec = {
                            fadeIn(tween(400)).togetherWith(fadeOut(tween(400)))
                        }, modifier = Modifier.fillMaxWidth()) { callState ->
                            when (callState) {
                                is TdApi.CallStatePending -> {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 12.dp),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        NeoCard(
                                            modifier = Modifier
                                                .sharedElement(
                                                    rememberSharedContentState("discard"),
                                                    this@AnimatedContent
                                                )
                                                .weight(1f)
                                                .fillMaxWidth()
                                                .clickVfx(onClick = {
                                                    TdClient.send(
                                                        TdApi.DiscardCall(
                                                            call.id,
                                                            false,
                                                            null,
                                                            (duration / 1000L).toInt(),
                                                            false,
                                                            callInstance?.connectionId ?: 0
                                                        )
                                                    )
                                                    callInstance?.performDestroy()
                                                }),
                                            background = if (call.isOutgoing) InputBarGray else NeoRed,
                                            shape = RoundedCornerShape(45),
                                            borderAlpha = if (call.isOutgoing) 0.03f else 0.2f
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Icon(
                                                    NeogramIcons.Close,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .size(buttonsHeight)
                                                        .fillMaxWidth()
                                                        .padding(vertical = 6.dp)
                                                )
                                                if (call.isOutgoing) {
                                                    Text(
                                                        "取消呼叫",
                                                        fontFamily = miSans,
                                                        fontSize = 12.5.sp,
                                                        color = Color.White,
                                                        modifier = Modifier
                                                            .padding(vertical = 6.dp)
                                                            .padding(end = 4.dp)
                                                    )
                                                }
                                            }
                                        }
                                        if (!call.isOutgoing) {
                                            NeoCard(
                                                modifier = Modifier
                                                    .weight(2f)
                                                    .fillMaxWidth()
                                                    .clickVfx {
                                                        TdClient.send(
                                                            TdApi.AcceptCall(
                                                                call.id,
                                                                VoIP.getProtocol()
                                                            )
                                                        )
                                                    },
                                                background = NeoMain,
                                                shape = RoundedCornerShape(45)
                                            ) {
                                                Icon(
                                                    NeogramIcons.Call,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .height(buttonsHeight)
                                                        .fillMaxWidth()
                                                        .padding(vertical = 6.dp)
                                                )
                                            }
                                            NeoCard(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxWidth()
                                                    .clickVfx(onClick = {
                                                        isMicrophoneDisabled = !isMicrophoneDisabled
                                                    }),
                                                background = InputBarGray,
                                                shape = RoundedCornerShape(45),
                                                borderAlpha = 0.03f
                                            ) {
                                                Icon(
                                                    NeogramIcons.Speaker,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .height(buttonsHeight)
                                                        .fillMaxWidth()
                                                        .padding(vertical = 6.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                is TdApi.CallStateDiscarded, is TdApi.CallStateHangingUp -> {
                                    val infiniteTransition = rememberInfiniteTransition()
                                    val alpha by infiniteTransition.animateFloat(
                                        initialValue = 1f,
                                        targetValue = 0f,
                                        animationSpec = infiniteRepeatable(
                                            animation = tween(
                                                durationMillis = 500
                                            ),
                                            repeatMode = RepeatMode.Reverse
                                        )
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 12.dp),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        NeoCard(
                                            modifier = Modifier
                                                .sharedElement(
                                                    rememberSharedContentState("discard"),
                                                    this@AnimatedContent
                                                )
                                                .weight(1f)
                                                .fillMaxWidth()
                                                .alpha(alpha),
                                            background = if (call.isOutgoing) InputBarGray else NeoRed,
                                            shape = RoundedCornerShape(45),
                                            borderAlpha = if (call.isOutgoing) 0.03f else 0.2f
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Icon(
                                                    NeogramIcons.Close,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .size(buttonsHeight)
                                                        .fillMaxWidth()
                                                        .padding(vertical = 6.dp)
                                                )
                                            }
                                        }

                                        Box(modifier = Modifier.weight(2f))

                                        NeoCard(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxWidth()
                                                .clickVfx(onClick = {
                                                    users[call.userId]?.tgUser?.let { user ->
                                                        TdClient.send(TdApi.CreatePrivateChat(user.id, false), { chat ->
                                                            if (chat is TdApi.Chat) {
                                                                navController.navigate(
                                                                    MessagesScreen(
                                                                        chat.id,
                                                                        chat.title,
                                                                        false
                                                                    )
                                                                )
                                                            }
                                                        })
                                                    }
                                                }),
                                            background = InputBarGray,
                                            shape = RoundedCornerShape(45),
                                            borderAlpha = 0.03f
                                        ) {
                                            Icon(
                                                NeogramIcons.Chat,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .height(buttonsHeight)
                                                    .fillMaxWidth()
                                                    .padding(vertical = 6.dp)
                                            )
                                        }
                                    }
                                }

                                is TdApi.CallStateReady, is TdApi.CallStateExchangingKeys -> {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 12.dp),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        NeoCard(
                                            modifier = Modifier
                                                .sharedElement(
                                                    rememberSharedContentState("discard"),
                                                    this@AnimatedContent
                                                )
                                                .weight(1f)
                                                .fillMaxWidth()
                                                .clickVfx(onClick = {
                                                    TdClient.send(
                                                        TdApi.DiscardCall(
                                                            call.id,
                                                            false,
                                                            null,
                                                            (duration / 1000L).toInt(),
                                                            false,
                                                            callInstance?.connectionId ?: 0
                                                        )
                                                    )
                                                    callInstance?.performDestroy()
                                                }),
                                            background = InputBarGray,
                                            shape = RoundedCornerShape(45),
                                            borderAlpha = 0.03f
                                        ) {
                                            Icon(
                                                NeogramIcons.Close,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .height(buttonsHeight)
                                                    .fillMaxWidth()
                                                    .padding(vertical = 6.dp)
                                            )
                                        }

                                        val microphoneButtonColor by animateColorAsState(if (isMicrophoneDisabled) InputBarGray else NeoMain)
                                        val microphoneButtonBorderAlpha by animateFloatAsState(if (isMicrophoneDisabled) 0.03f else 0.2f)
                                        NeoCard(
                                            modifier = Modifier
                                                .weight(2f)
                                                .fillMaxWidth()
                                                .clickVfx {
                                                    isMicrophoneDisabled = !isMicrophoneDisabled
                                                    callInstance?.setMicDisabled(
                                                        isMicrophoneDisabled
                                                    )
                                                },
                                            background = microphoneButtonColor,
                                            shape = RoundedCornerShape(45),
                                            borderAlpha = microphoneButtonBorderAlpha
                                        ) {
                                            Icon(
                                                NeogramIcons.Microphone,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .height(buttonsHeight)
                                                    .fillMaxWidth()
                                                    .padding(vertical = 6.dp)
                                            )
                                        }
                                        NeoCard(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxWidth()
                                                .clickVfx(onClick = {

                                                }),
                                            background = InputBarGray,
                                            shape = RoundedCornerShape(45),
                                            borderAlpha = 0.03f
                                        ) {
                                            Icon(
                                                NeogramIcons.Speaker,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .height(buttonsHeight)
                                                    .fillMaxWidth()
                                                    .padding(vertical = 6.dp)
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    Box(modifier = Modifier.fillMaxWidth())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}