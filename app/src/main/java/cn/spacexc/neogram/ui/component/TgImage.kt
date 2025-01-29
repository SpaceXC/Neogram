package cn.spacexc.neogram.ui.component

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.telegram.ui.component.AsyncImage
import cn.spacexc.telegram.ui.component.shimmerPlaceHolder
import org.drinkless.tdlib.TdApi.DownloadFile
import org.drinkless.tdlib.TdApi.File

@Composable
fun TgImage(file: File, thumbnail: ByteArray?, modifier: Modifier = Modifier) {
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
        if(thumbnail != null) {
            val thumbnailBitmap =
                BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.size)
                    .asImageBitmap()
            Image(
                thumbnailBitmap,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.FillBounds
            )
        }
        else {
            Box(modifier = modifier.shimmerPlaceHolder(true))
        }
    } else {
        AsyncImage(
            url = localPath,
            contentDescription = null,
            modifier = modifier,
            placeholderEnabled = false
        )
    }
}