package cn.spacexc.neogram.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.drinkless.tdlib.TdApi

@Composable
fun TgAnimation(animation: TdApi.Animation, modifier: Modifier = Modifier) {
    when (animation.mimeType) {
        "video/mp4" -> {
            TgVideo(animation.animation, modifier)
        }

        "image/gif" -> {
            TgImage(animation.animation, null, modifier)
        }
    }
}