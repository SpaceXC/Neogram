package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import cn.spacexc.neogram.data.file.FileRepository
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.currentProgressFlow
import cn.spacexc.neogram.utils.formatFileSize
import cn.spacexc.neogram.utils.toMinSec
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import org.drinkless.tdlib.TdApi
import java.io.File

@Composable
fun TgVoiceNote(file: TdApi.File, modifier: Modifier) {
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
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            var duration by remember { mutableLongStateOf(0L) }
            var isReady by remember { mutableStateOf(false) }
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
            if (exoPlayer != null) {
                val currentPosition by exoPlayer!!.currentProgressFlow().collectAsState(0L)
                if (isReady) {
                    IconButton(onClick = {
                        if (isPlaying) exoPlayer!!.pause() else {
                            if (isEnded) {
                                isEnded = false
                                exoPlayer!!.seekTo(0)
                                exoPlayer!!.play()
                            } else {
                                exoPlayer!!.play()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                } else {
                    CircularProgressIndicator(color = NeoBlue)
                }
                Column {
                    Text(
                        "${currentPosition.toMinSec()}/${duration.toMinSec()}",
                        color = Color.White,
                        fontFamily = miSans,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}