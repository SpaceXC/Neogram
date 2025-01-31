package cn.spacexc.neogram.ui.component

import android.view.TextureView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import org.drinkless.tdlib.TdApi
import java.io.File

@Composable
fun TgVoiceNote(file: TdApi.File, modifier: Modifier) {
    var audioPath by remember { mutableStateOf(file.local.path) }
    var exoPlayer: ExoPlayer? = remember { null }
    var isPlaying by remember { mutableStateOf(false) }
    var isEnded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.DownloadFile(file.id, 1, 0, 0, true), {
            if (it is TdApi.File) {
                audioPath = it.local.path
            }
        })
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer?.release()
        }
    }
    if (audioPath.isEmpty()) {
        Box(modifier = modifier.shimmerPlaceHolder(true))
    } else {
        Row(modifier = Modifier.padding(10.dp)) {
            LaunchedEffect(Unit) {
                val player = ExoPlayer.Builder(context).build()
                exoPlayer = player
                player.setMediaItem(MediaItem.fromUri(File(audioPath).toUri()))
                player.playWhenReady = false
                player.repeatMode = Player.REPEAT_MODE_OFF
                player.prepare()
                exoPlayer.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(playing: Boolean) {
                        super.onIsPlayingChanged(playing)
                        isPlaying = playing
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        when(playbackState) {
                            ExoPlayer.STATE_ENDED -> {
                                isEnded = true
                            }
                        }
                    }
                })
            }
            IconButton(onClick = {
                if (isPlaying) exoPlayer?.pause() else {
                    if (isEnded) {
                        isEnded = false
                        exoPlayer?.seekTo(0)
                        exoPlayer?.play()
                    }
                    else {
                        exoPlayer?.play()
                    }
                }
            }) {
                Icon(
                    imageVector = if (isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}