package cn.spacexc.neogram.ui.screen.image

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.telegram.ui.component.AsyncImage
import kotlinx.serialization.Serializable
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Serializable
data class ImageViewerScreen(val imagePath: String)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ImageViewerScreen(
    animatedContentScope: AnimatedContentScope,
    navController: NavController,
    imagePath: String
) {
    TitleFrame("图片预览", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        AsyncImage(
            modifier = Modifier
                .sharedElement(rememberSharedContentState(imagePath), animatedContentScope)
                .padding(top = it, bottom = 8.dp)
                .padding(horizontal = 8.dp)
                .fillMaxSize()
                .zoomable(rememberZoomState()),
            contentDescription = null,
            url = imagePath
        )
    }
}