package cn.spacexc.neogram.ui.screen.image

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.component.AsyncImage
import kotlinx.serialization.Serializable
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.IntSize

@Serializable
data class ImageViewerScreen(val imagePath: String, val imageId: String)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ImageViewerScreen(
    animatedContentScope: AnimatedContentScope,
    navController: NavController,
    imagePath: String,
    imageId: String
) {
    val zoomableState = rememberZoomState()
    var currentScale by remember { mutableFloatStateOf(1f) }
    TitleFrame("图片预览", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        AsyncImage(
            modifier = Modifier
                .sharedBounds(rememberSharedContentState(imageId), animatedContentScope)
                .padding(top = it, bottom = 8.dp)
                .padding(horizontal = 8.dp)
                .fillMaxSize()
                .zoomable(zoomableState)
                /*.pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            // 监听手指移动
                            do {
                                val event = awaitPointerEvent()
                            } while (event.changes.any { it.pressed })
                            // 手指抬起后执行，无论是否拖动过
                            currentScale = zoomableState.scale
                            println("Released after tap or drag")
                        }
                    }
                }*/,
            contentDescription = null,
            url = imagePath,
            loadOriginal = true
        )
    }
}