package cn.spacexc.neogram.ui.screen.call

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.data.call.durationFlow
import cn.spacexc.neogram.data.connection.ConnectionStateRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.component.TgUserAvatar
import cn.spacexc.neogram.ui.icons.Microphone
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Speaker
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.NeoRed
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.toMinSec
import cn.spacexc.neogram.utils.username
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.SendCallSignalingData
import org.thunderdog.challegram.voip.ConnectionStateListener
import org.thunderdog.challegram.voip.VoIP
import org.thunderdog.challegram.voip.VoIPInstance
import org.thunderdog.challegram.voip.annotation.CallState
import kotlin.time.Duration.Companion.milliseconds

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
                        is TdApi.CallStateReady, is TdApi.CallStateHangingUp -> NeoBlue
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
                    val text = when (call.state) {
                        is TdApi.CallStatePending -> {
                            if (!(call.state as TdApi.CallStatePending).isCreated) "创建通话中"
                            else {
                                if ((call.state as TdApi.CallStatePending).isReceived) "响铃" else "通话已发出"
                            }
                        }

                        is TdApi.CallStateReady -> {
                            if (duration < 0) "连接中..." else duration.toMinSec()
                        }

                        is TdApi.CallStateHangingUp -> "正在挂断"
                        is TdApi.CallStateError -> "发生错误"
                        is TdApi.CallStateDiscarded -> "通话结束"
                        is TdApi.CallStateExchangingKeys -> "交换密钥"
                        else -> "未知状态"
                    }
                    Text(
                        text,
                        fontWeight = FontWeight.Medium,
                        fontFamily = miSans,
                        fontSize = 13.sp,
                        color = if (call.state is TdApi.CallStateReady && duration > 0) Color.White.copy(
                            alpha = 0.7f
                        ) else NeoBlue,
                        maxLines = 1
                    )

                    Spacer(Modifier.weight(1f))
                    val buttonsHeight = remember { 36.dp }
                    AnimatedContent(call.state, transitionSpec = {
                        /*fadeIn(animationSpec = tween(220, delayMillis = 90))
                            .togetherWith(fadeOut(animationSpec = tween(90)))*/
                        fadeIn().togetherWith(fadeOut())
                    }, modifier = Modifier.fillMaxWidth()) { callState ->
                        SharedTransitionLayout(modifier = Modifier.fillMaxWidth()) {
                            when (callState) {
                                is TdApi.CallStatePending -> {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
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
                                                            //null,
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
                                                Icons.Rounded.Close,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 8.dp)
                                            )
                                        }
                                        if (!call.isOutgoing) {
                                            NeoCard(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxWidth()
                                                    .clickVfx {
                                                        TdClient.send(
                                                            TdApi.AcceptCall(
                                                                call.id,
                                                                VoIP.getProtocol()
                                                            )
                                                        )
                                                    },
                                                background = NeoBlue,
                                                shape = RoundedCornerShape(45)
                                            ) {
                                                Icon(
                                                    Icons.Rounded.Check,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 8.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                is TdApi.CallStateReady -> {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
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
                                                            //null,
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
                                                Icons.Rounded.Close,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .height(buttonsHeight)
                                                    .fillMaxWidth()
                                                    .padding(vertical = 8.dp)
                                            )
                                        }

                                        val microphoneButtonColor by animateColorAsState(if (isMicrophoneDisabled) InputBarGray else NeoBlue)
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
                                                    .padding(vertical = 8.dp)
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
                                                    .padding(vertical = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}