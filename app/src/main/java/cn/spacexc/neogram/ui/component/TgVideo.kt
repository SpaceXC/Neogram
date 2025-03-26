package cn.spacexc.neogram.ui.component

import android.view.TextureView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import org.drinkless.tdlib.TdApi
import java.io.File

@Composable
fun TgVideo(file: TdApi.File, modifier: Modifier) {
    var videoPath by remember { mutableStateOf(file.local.path) }
    var exoPlayer: ExoPlayer? = remember { null }
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.DownloadFile(file.id, 1, 0, 0, true), {
            if (it is TdApi.File) {
                videoPath = it.local.path
            }
        })
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer?.stop()
            exoPlayer?.release()
            exoPlayer?.release()
        }
    }
    if (videoPath.isEmpty()) {
        Box(modifier = modifier.shimmerPlaceHolder(true))
    } else {
        AndroidView({ TextureView(it) }, modifier = modifier) { textureView ->
            val player = ExoPlayer.Builder(textureView.context).build()
            exoPlayer = player
            player.setVideoTextureView(textureView)
            player.setMediaItem(MediaItem.fromUri(File(videoPath).toUri()))
            player.playWhenReady = true
            player.repeatMode = Player.REPEAT_MODE_ALL
            player.volume = 0f
            player.prepare()
        }
    }
}