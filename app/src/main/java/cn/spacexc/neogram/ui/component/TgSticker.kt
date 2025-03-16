package cn.spacexc.neogram.ui.component

import android.media.MediaPlayer
import android.view.SurfaceView
import android.view.TextureView
import android.widget.VideoView
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import org.drinkless.tdlib.TdApi
import java.io.File
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TgSticker(
    animatedContentScope: AnimatedContentScope,
    sticker: TdApi.Sticker,
    modifier: Modifier,
    navController: NavController,
    id: String
) {
    /**
    StickerFormatWebp.CONSTRUCTOR,
    StickerFormatTgs.CONSTRUCTOR,
    StickerFormatWebm.CONSTRUCTOR
     */
    when (sticker.format) {
        is TdApi.StickerFormatWebp -> {
            TgImage(animatedContentScope, sticker.sticker, null, modifier, navController, id)
        }

        is TdApi.StickerFormatWebm -> {
            TgVideo(sticker.sticker, modifier)
        }

        is TdApi.StickerFormatTgs -> {
            var json by remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                if (sticker.sticker.local.path.isNotEmpty()) {
                    json = decompressGzipAndSaveAsJson(sticker.sticker.local.path)?.readText() ?: ""
                } else {
                    TdClient.send(TdApi.DownloadFile(sticker.sticker.id, 1, 0, 0, true), {
                        if (it is TdApi.File) {
                            json = decompressGzipAndSaveAsJson(it.local.path)?.readText() ?: ""
                        }
                    })
                }
            }
            if (json.isEmpty()) {
                Box(modifier = modifier.shimmerPlaceHolder(true))
            } else {
                DotLottieAnimation(
                    source = DotLottieSource.Json(json),
                    autoplay = true,
                    loop = true,
                    speed = 1f,
                    useFrameInterpolation = false,
                    playMode = Mode.FORWARD,
                    modifier = modifier
                )
            }
        }
    }
}


fun decompressGzipAndSaveAsJson(gzipFilePath: String): File? {
    val gzipFile = File(gzipFilePath)
    if (!gzipFile.exists()) {
        throw IllegalArgumentException("File does not exist: $gzipFilePath")
    }

    // Construct the output file path with .json extension
    val jsonFilePath = gzipFilePath.replaceAfterLast(".", "json")
    val jsonFile = File(jsonFilePath)

    if (jsonFile.exists()) return jsonFile

    return try {
        // Decompress the GZIP file and write to the .json file
        gzipFile.inputStream().use { inputStream ->
            GZIPInputStream(inputStream).use { gzipInputStream ->
                FileOutputStream(jsonFile).use { outputStream ->
                    val buffer = ByteArray(1024)
                    var bytesRead: Int
                    while (gzipInputStream.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                    }
                }
            }
        }
        jsonFile // Return the resulting file
    } catch (e: Exception) {
        e.printStackTrace()
        null // Return null if an error occurs
    }
}