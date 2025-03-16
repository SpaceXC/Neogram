package cn.spacexc.neogram.ui.component

import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.ui.screen.image.ImageViewerScreen
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.telegram.ui.component.clickAlpha
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import org.drinkless.tdlib.TdApi.DownloadFile
import org.drinkless.tdlib.TdApi.File
import java.util.UUID

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TgImage(
    animatedContentScope: AnimatedContentScope,
    file: File,
    thumbnail: ByteArray?,
    modifier: Modifier = Modifier,
    navController: NavController?,
    id: String? = null
) {
    var localPath by remember { mutableStateOf(file.local.path) }
    var id by remember { mutableStateOf(if (id == null) UUID.randomUUID().toString() else id) }
    LaunchedEffect(Unit) {
        if (localPath.isEmpty()) {
            TdClient.send(DownloadFile(file.id, 1, 0, 0, true), {
                if (it is File) {
                    localPath = it.local.path
                }
            }, {})
        }
    }
    if (localPath.isEmpty()) {
        if (thumbnail != null) {
            val thumbnailBitmap =
                BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.size)
                    .asImageBitmap()
            Image(
                thumbnailBitmap,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
        } else {
            Box(modifier = modifier.shimmerPlaceHolder(true))
        }
    } else {
        AsyncImage(
            url = localPath,
            contentDescription = null,
            modifier = modifier
                .sharedBounds(
                    rememberSharedContentState(id),
                    animatedContentScope
                )
                .clickAlpha(enabled = navController != null, onClick = {
                    LogUtils.info("Image", "Clicked")
                    navController?.navigate(ImageViewerScreen(localPath, id))
                }),
            placeholderEnabled = false
        )
    }
}