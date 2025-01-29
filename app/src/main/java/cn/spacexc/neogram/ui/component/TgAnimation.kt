package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import org.drinkless.tdlib.TdApi
import java.io.File

@Composable
fun TgAnimation(animation: TdApi.Animation, modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.DownloadFile(animation.animation.id, 1, 0, 0, true), {
            println("Download Animation $${(it as TdApi.File).local.path}")
        })
    }
    var json by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.DownloadFile(animation.animation.id, 1, 0, 0, true), {
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