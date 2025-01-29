package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import org.drinkless.tdlib.TdApi
import java.io.File
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream

@Composable
fun TgSticker(sticker: TdApi.Sticker, modifier: Modifier) {
    /**
        StickerFormatWebp.CONSTRUCTOR,
        StickerFormatTgs.CONSTRUCTOR,
        StickerFormatWebm.CONSTRUCTOR
     */
    when (sticker.format) {
        is TdApi.StickerFormatWebp, is TdApi.StickerFormatWebm -> {
            TgImage(sticker.sticker, null, modifier)
        }
        is TdApi.StickerFormatTgs -> {
            var json by remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                TdClient.send(TdApi.DownloadFile(sticker.sticker.id, 1, 0, 0, true), {
                    if(it is TdApi.File) {
                        json = decompressGzipAndSaveAsJson(it.local.path)?.readText() ?: ""
                    }
                })
            }
            if(json.isEmpty()) {
                Box(modifier = modifier.shimmerPlaceHolder(true))
            }
            else {
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