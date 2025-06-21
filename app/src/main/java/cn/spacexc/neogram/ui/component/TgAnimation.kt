package cn.spacexc.neogram.ui.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.drinkless.tdlib.TdApi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TgAnimation(
    animatedContentScope: AnimatedContentScope,
    animation: TdApi.Animation,
    modifier: Modifier = Modifier,
    navController: NavController,
    id: String
) {
    when (animation.mimeType) {
        "video/mp4" -> {
            TgVideo(animation.animation, modifier, navController, autoplay = true)
        }

        "image/gif" -> {
            TgImage(animatedContentScope, animation.animation, null, modifier, navController, id)
        }
    }
}