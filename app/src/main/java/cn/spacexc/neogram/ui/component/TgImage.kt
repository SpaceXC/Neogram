package cn.spacexc.neogram.ui.component

import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.file.FileRepository
import cn.spacexc.neogram.ui.screen.image.ImageViewerScreen
import cn.spacexc.neogram.ui.theme.BadgeGray
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.formatFileSize
import cn.spacexc.neogram.ui.component.modifier.clickAlpha
import cn.spacexc.neogram.ui.component.modifier.shimmerPlaceHolder
import coil3.transform.Transformation
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
    id: String? = null,
    transformations: List<Transformation> = listOf(),
) {
    val downloadState = FileRepository.downloadList[file.id]
    var id by remember { mutableStateOf(id ?: UUID.randomUUID().toString()) }
    LaunchedEffect(Unit) {
        FileRepository.downloadFile(file)
    }
    if (!downloadState?.localPath.isNullOrEmpty() || !file.local.path.isEmpty()) {
        AsyncImage(
            url = downloadState?.localPath ?: file.local.path,
            contentDescription = null,
            modifier = modifier
                .sharedBounds(
                    rememberSharedContentState(id),
                    animatedContentScope
                )
                .clickAlpha(enabled = navController != null, onClick = {
                    LogUtils.info("Image", "Clicked")
                    navController?.navigate(
                        ImageViewerScreen(
                            downloadState?.localPath ?: file.local.path, id
                        )
                    )
                }),
            placeholderEnabled = false,
            loadOriginal = true,
            transformations = transformations
        )
    } else {
        Box {
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
    }
}

@Composable
fun TgImage(
    file: File,
    thumbnail: ByteArray?,
    modifier: Modifier = Modifier,
    transformations: List<Transformation> = listOf(),
) {
    var localPath by remember { mutableStateOf(file.local.path) }
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
            modifier = modifier,
            placeholderEnabled = false,
            transformations = transformations,
        )
    }
}