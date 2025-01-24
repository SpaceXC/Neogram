package cn.spacexc.telegram.ui.component

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.toSize
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.Companion.DefaultTransform
import coil.compose.AsyncImagePainter.State
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.transform.Transformation

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String?,
    transform: (State) -> State = DefaultTransform,
    transformations: List<Transformation> = emptyList(),
    onState: ((State) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DefaultFilterQuality,
    placeholderEnabled: Boolean = true
) {
    var size by remember { mutableStateOf(Size(0f, 0f)) }
    var isLoading by remember {
        mutableStateOf(true)
    }
    Box(modifier = modifier
        .onSizeChanged {
            size = it.toSize()
        }
        .shimmerPlaceHolder(isLoading && placeholderEnabled)
    ) {
        if (size.width != 0f) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .transformations(transformations)
                    .crossfade(true).build(),
                contentDescription = contentDescription,
                modifier = Modifier.matchParentSize(),
                transform = transform,
                onState = {
                    isLoading = when (it) {
                        is State.Success -> false
                        is State.Loading -> true
                        else -> true
                    }
                    onState?.invoke(it)
                },
                alignment = alignment,
                contentScale = contentScale,
                alpha = alpha,
                colorFilter = colorFilter,
                filterQuality = filterQuality,
                imageLoader = ImageLoader(LocalContext.current).newBuilder()
                    .components {
                        if (Build.VERSION.SDK_INT >= 28) {
                            add(ImageDecoderDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }.build()
            )
        }
    }
}