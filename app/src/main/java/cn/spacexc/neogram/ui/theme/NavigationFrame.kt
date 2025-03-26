package cn.spacexc.neogram.ui.theme

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.R
import cn.spacexc.neogram.data.connection.ConnectionState
import cn.spacexc.neogram.data.connection.ConnectionStateRepository
import cn.spacexc.telegram.ui.component.clickAlpha

val StatusBarsTopPadding: PaddingValues
    @Composable
    get() = WindowInsets.statusBars.union(
        WindowInsets.captionBar
    ).only(WindowInsetsSides.Top).asPaddingValues()

@Composable
fun TitleFrame(
    title: String,
    timeText: String? = null,
    actionImage: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.3f)
        )
    },
    actionImageModifier: Modifier = Modifier,
    isLoading: Boolean = false,
    fullScreenLoading: Boolean = true,
    onActionClicked: () -> Unit,
    onTitleClicked: () -> Unit,
    content: @Composable (topPadding: Dp) -> Unit
) {
    val localDensity = LocalDensity.current
    var titleHeight by remember {
        mutableStateOf(0.dp)
    }
    val timeSource = DefaultTimeSource("HH:mm")
    //val timeText = timeSource.currentTime
    val connectionState by ConnectionStateRepository.connectionState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(StatusBarsTopPadding)
    ) {
        Crossfade(
            targetState = isLoading,
            modifier = Modifier.fillMaxSize(),
            label = ""
        ) { loading ->
            if (loading) {
                val infiniteTransition = rememberInfiniteTransition(label = "")
                val rotation by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            1000,
                            easing = LinearEasing
                        )
                    ), label = ""
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.img_loading_indicator),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .align(Alignment.Center)
                            .rotate(rotation)
                    )
                }
            } else {
                Box(modifier = Modifier
                    .graphicsLayer {
                        compositingStrategy =
                            CompositingStrategy.Offscreen
                    }
                    .drawWithContent {
                        val titleHeightPx = titleHeight.toPx()
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                0f to Color(0, 0, 0, 40),
                                titleHeightPx / size.height * 1.2f to Color.Black
                            ),
                            blendMode = BlendMode.DstIn,
                        )
                        //drawContent()
                    }) {
                    content(titleHeight)
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                titleHeight = with(localDensity) { it.height.toDp() }
            }
            .graphicsLayer {
                compositingStrategy =
                    CompositingStrategy.Offscreen
            }
            .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {

            Box(
                modifier = Modifier
                    .offset(y = 2.dp)
                    .size(with(localDensity) { 26.sp.toDp() })
                    .background(parseColor("#121212"), CircleShape)
                    .padding(4.dp)
                    .clickAlpha { onActionClicked() }
                    .then(actionImageModifier),
                contentAlignment = Alignment.Center
            ) {
                actionImage()
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.clickAlpha { onTitleClicked() }) {
                Text(
                    text = if (timeText == null) timeSource.currentTime else timeText,
                    color = Color.White,
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = when (connectionState) {
                        ConnectionState.WaitingForNetwork -> "无网络"
                        ConnectionState.Connecting -> "连接中"
                        ConnectionState.Updating -> "更新中"
                        ConnectionState.ConnectingToProxy -> "代理连接中"
                        ConnectionState.Ready -> title
                        ConnectionState.Unknown -> "网络异常"
                    },
                    color = NeoBlue,
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End,
                    modifier = Modifier.offset(y = (-3).dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SQUARE)
@Composable
private fun FramePrev() {
    TitleFrame(title = "Test", onTitleClicked = {}, onActionClicked = {}, isLoading = true) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(it))
            Box(
                modifier = Modifier
                    .height(2000.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
            )
        }
    }
}