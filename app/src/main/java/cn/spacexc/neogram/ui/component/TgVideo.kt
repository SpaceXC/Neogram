package cn.spacexc.neogram.ui.component

import android.view.TextureView
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import cn.spacexc.neogram.data.file.FileRepository
import cn.spacexc.neogram.ui.screen.video.VideoPlayerScreen
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.formatFileSize
import cn.spacexc.neogram.utils.getVideoFirstFrame
import cn.spacexc.neogram.ui.component.modifier.shimmerPlaceHolder
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi
import java.io.File

@OptIn(UnstableApi::class)
@Composable
fun TgVideo(
    file: TdApi.File,
    modifier: Modifier,
    navController: NavController?,
    autoplay: Boolean = false
) {
    var downloadState = FileRepository.downloadList[file.id]
    var firstFrame by remember { mutableStateOf<ImageBitmap?>(null) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        FileRepository.downloadFile(file)
    }
    LaunchedEffect(downloadState) {
        scope.launch {
            if (downloadState?.localPath != null) {
                firstFrame = getVideoFirstFrame(downloadState.localPath)?.asImageBitmap()
            }
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
        if (autoplay) {
            var exoPlayer: ExoPlayer? = remember { null }

            AndroidView({ TextureView(it) }, modifier = modifier) { textureView ->
                val player = ExoPlayer.Builder(textureView.context)
                    .setRenderersFactory(
                        DefaultRenderersFactory(textureView.context)
                        .setEnableDecoderFallback(true)
                ).build()
                exoPlayer = player
                player.setVideoTextureView(textureView)
                player.setMediaItem(MediaItem.fromUri(File(downloadState.localPath).toUri()))
                player.playWhenReady = true
                player.repeatMode = Player.REPEAT_MODE_ALL
                player.volume = 0f
                player.prepare()
            }
        } else {
            Box(modifier = modifier.background(Color.White), contentAlignment = Alignment.Center) {
                firstFrame
                    ?.let {
                        Image(
                            bitmap = it,
                            null,
                            Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                IconButton(onClick = {
                    navController?.navigate(VideoPlayerScreen(downloadState.localPath))
                }) {
                    Icon(
                        Icons.Rounded.PlayArrow,
                        null,
                        tint = Color.White,
                        modifier = Modifier
                            .scale(0.9f)
                            .background(BadgeGray.copy(alpha = 0.7f), CircleShape)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}