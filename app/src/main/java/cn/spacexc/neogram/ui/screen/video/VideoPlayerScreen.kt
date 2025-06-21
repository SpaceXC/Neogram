package cn.spacexc.neogram.ui.screen.video

import android.view.TextureView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class VideoPlayerScreen(val videoPath: String)

@Composable
fun VideoPlayerScreen(navController: NavController, videoPath: String) {
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }
    var aspectRatio by remember { mutableFloatStateOf(1f) }
    var isPlaying by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer?.stop()
            exoPlayer?.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            { TextureView(it) },
            modifier = Modifier
                .aspectRatio(aspectRatio)
                .then(if (aspectRatio > 1f) Modifier.fillMaxWidth() else Modifier.fillMaxHeight())
                .align(Alignment.Center)
        ) { textureView ->
            val player = ExoPlayer.Builder(textureView.context).build()
            exoPlayer = player
            player.setVideoTextureView(textureView)
            player.setMediaItem(MediaItem.fromUri(File(videoPath).toUri()))
            player.playWhenReady = true
            player.repeatMode = Player.REPEAT_MODE_ALL
            player.volume = 0f

            player.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(playing: Boolean) {
                    super.onIsPlayingChanged(playing)
                    isPlaying = playing
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    when (playbackState) {
                        ExoPlayer.STATE_ENDED -> {

                        }

                        Player.STATE_BUFFERING -> {

                        }

                        Player.STATE_IDLE -> {

                        }

                        Player.STATE_READY -> {
                            aspectRatio =
                                player.videoSize.width.toFloat() / player.videoSize.height.toFloat()
                        }
                    }
                }
            })
            player.prepare()
        }
    }
}