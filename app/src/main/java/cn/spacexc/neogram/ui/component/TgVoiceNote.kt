package cn.spacexc.neogram.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import cn.spacexc.neogram.data.file.FileRepository
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Pause
import cn.spacexc.neogram.ui.icons.Play
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.BubbleGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.currentProgressFlow
import cn.spacexc.neogram.utils.decodeToPCM
import cn.spacexc.neogram.utils.extractWaveformUsingJTransforms
import cn.spacexc.neogram.utils.formatFileSize
import cn.spacexc.neogram.utils.toMinSec
import cn.spacexc.telegram.ui.component.clickVfx
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import org.drinkless.tdlib.TdApi
import java.io.File
import kotlin.math.hypot

@Composable
fun TgVoiceNote(file: TdApi.File, modifier: Modifier, isOutgoing: Boolean) {
    val downloadState = FileRepository.downloadList[file.id]
    var exoPlayer: ExoPlayer? by remember { mutableStateOf(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var isEnded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        FileRepository.downloadFile(file)
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer?.release()
        }
    }
    if (downloadState?.localPath == null) {
        Box {
            Box(modifier = modifier.shimmerPlaceHolder(true))
            Text(
                "${downloadState?.downloadedSize?.formatFileSize()}/${downloadState?.expectedSize?.formatFileSize()}",
                color = Color.White,
                fontFamily = miSans,
                fontSize = 9.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .background(BadgeGray.copy(alpha = 0.7f), CircleShape)
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            )
        }
    } else {
        Column(
            modifier = modifier
                .background(
                    if (isOutgoing) NeoMain else BubbleGray,
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 5.dp,
                        bottomEnd = 12.dp
                    )
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.End
        ) {
            var duration by remember { mutableLongStateOf(0L) }
            var isReady by remember { mutableStateOf(false) }

            var lowFreq by remember { mutableStateOf(emptyList<Float>()) }
            var highFreq by remember { mutableStateOf(emptyList<Float>()) }

            val drawProgress by animateFloatAsState(if (lowFreq.isEmpty()) 0f else 1f, tween(800))

            LaunchedEffect(Unit) {
                val player = ExoPlayer.Builder(context).build()
                exoPlayer = player
                player.setMediaItem(MediaItem.fromUri(File(downloadState.localPath).toUri()))
                player.playWhenReady = false
                player.repeatMode = Player.REPEAT_MODE_OFF
                player.prepare()
                exoPlayer?.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(playing: Boolean) {
                        super.onIsPlayingChanged(playing)
                        isPlaying = playing
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        when (playbackState) {
                            ExoPlayer.STATE_ENDED -> {
                                isEnded = true
                            }

                            Player.STATE_BUFFERING -> {

                            }

                            Player.STATE_IDLE -> {

                            }

                            Player.STATE_READY -> {
                                duration = exoPlayer!!.duration
                                isReady = true
                            }
                        }
                    }
                })
            }
            LaunchedEffect(Unit) {
                val pcm = decodeToPCM(File(downloadState.localPath))
                val (lowFreqPoints, highFreqPoints) = extractWaveformUsingJTransforms(pcm)
                lowFreq = lowFreqPoints
                highFreq = highFreqPoints
                LogUtils.info("VoiceNote", "$lowFreq $highFreq")
            }

            if (exoPlayer != null) {
                val currentPosition by exoPlayer!!.currentProgressFlow().collectAsState(0L)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isReady) {
                        Icon(
                            imageVector = if (isPlaying) NeogramIcons.Pause else NeogramIcons.Play,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .clickVfx {
                                    if (isPlaying) exoPlayer?.pause() else exoPlayer?.play()
                                }
                        )
                    } else {
                        CircularProgressIndicator(color = NeoMain)
                    }
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth()
                                .height(24.dp)
                                .graphicsLayer {
                                    compositingStrategy = CompositingStrategy.Offscreen
                                }
                                .drawWithCache {
                                    onDrawBehind {
                                        clipRect(right = drawProgress * size.width) {
                                            val path = drawFreqCurve(lowFreq)
                                            drawFreqCurve(highFreq)

                                            val filledPath = Path()
                                            filledPath.addPath(path)
                                            filledPath.lineTo(size.width, size.height)
                                            filledPath.lineTo(0f, size.height)
                                            filledPath.close()

                                            drawPath(
                                                path = filledPath,
                                                brush = Brush.verticalGradient(
                                                    listOf(
                                                        Color.White.copy(alpha = 0.8f),
                                                        Color.Transparent
                                                    )
                                                ),
                                                style = Fill
                                            )
                                        }
                                        drawRect(
                                            brush = Brush.verticalGradient(
                                                0f to Color.Black,
                                                0.65f to Color.Black,
                                                1f to Color.Transparent
                                            ),
                                            blendMode = BlendMode.DstIn,
                                        )
                                    }
                                }
                        )
                        Box(
                            modifier = Modifier
                                .height(30.dp)
                                .width(2.dp)
                                .background(Color.White, CircleShape)
                        )
                    }
                }

                Text(
                    "${currentPosition.toMinSec()}/${duration.toMinSec()}",
                    color = Color.White,
                    fontFamily = miSans,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

fun downsample(values: List<Float>, groupSize: Int = 4): List<Float> {
    return values.chunked(groupSize).map { it.max() }
}

fun smooth(values: List<Float>, windowSize: Int = 7): List<Float> {
    if (windowSize < 2 || values.size < windowSize) return values

    return values.mapIndexed { i, _ ->
        val start = (i - windowSize / 2).coerceAtLeast(0)
        val end = (i + windowSize / 2).coerceAtMost(values.lastIndex)
        val window = values.subList(start, end + 1)
        window.average().toFloat()
    }
}

fun scale(values: List<Float>): List<Float> {
    val max = values.maxOrNull() ?: 1f
    val fraction = 1f / max
    return values.map { it * fraction }
}


fun smoothPoints(
    points: List<Offset>,
    alpha: Float = 0.2f  // 越小越平滑
): List<Offset> {
    if (points.isEmpty()) return emptyList()
    val smoothed = mutableListOf<Offset>()
    var prev = points.first()
    smoothed.add(prev)
    for (i in 1 until points.size) {
        val current = points[i]
        val filtered = Offset(
            x = alpha * current.x + (1 - alpha) * prev.x,
            y = alpha * current.y + (1 - alpha) * prev.y
        )
        smoothed.add(filtered)
        prev = filtered
    }
    return smoothed
}

fun createCornerSmoothPath(points: List<Offset>, smoothness: Float = 0.4f): Path {
    val path = Path()
    if (points.size < 2) return path

    path.moveTo(points[0].x, points[0].y)

    for (i in 1 until points.size - 1) {
        val prev = points[i - 1]
        val current = points[i]
        val next = points[i + 1]

        // 方向向量
        val dx1 = current.x - prev.x
        val dy1 = current.y - prev.y
        val dx2 = next.x - current.x
        val dy2 = next.y - current.y

        // 控制点距离 = 原始边长 × smoothness
        val len1 = hypot(dx1, dy1)
        val len2 = hypot(dx2, dy2)

        val cp1 = Offset(
            current.x - dx1 / len1 * smoothness * len1,
            current.y - dy1 / len1 * smoothness * len1
        )
        val cp2 = Offset(
            current.x + dx2 / len2 * smoothness * len2,
            current.y + dy2 / len2 * smoothness * len2
        )

        path.lineTo(cp1.x, cp1.y)
        path.quadraticBezierTo(current.x, current.y, cp2.x, cp2.y)
    }

    // 最后一个点直接连回
    path.lineTo(points.last().x, points.last().y)
    return path
}


fun DrawScope.drawFreqCurve(freq: List<Float>): Path {
    val downsampled = downsample(freq, groupSize = 4)

    val basePoints = downsampled.mapIndexed { index, value ->
        val x = index.toFloat() / downsampled.size.toFloat() * size.width
        val y = (1f - value.coerceIn(0f, 1f)) * size.height
        Offset(x, y)
    }
    val points = buildList {
        add(Offset(0f, this@drawFreqCurve.size.height))
        //add(Offset(0f, basePoints.firstOrNull()?.y ?: 0f))
        addAll(basePoints)
        //add(Offset(this@drawFreqCurve.size.width, basePoints.lastOrNull()?.y ?: 0f))
        add(Offset(this@drawFreqCurve.size.width, this@drawFreqCurve.size.height))
    }

    val path = createCornerSmoothPath(points)

    drawPath(
        path = path,
        color = Color.White,
        style = Stroke(width = 1.5.dp.toPx())
    )

    return path
}