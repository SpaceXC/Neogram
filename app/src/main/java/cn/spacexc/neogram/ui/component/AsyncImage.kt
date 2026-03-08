package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import cn.spacexc.neogram.ui.component.modifier.shimmerPlaceHolder
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.Companion.DefaultTransform
import coil3.compose.AsyncImagePainter.State
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.Transformation

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
    placeholderEnabled: Boolean = true,
    loadOriginal: Boolean = false
) {
    //var size by remember { mutableStateOf(Size(0f, 0f)) }
    var isLoading by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = modifier
            .shimmerPlaceHolder(isLoading && placeholderEnabled)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .transformations(transformations)
                .apply { if (loadOriginal) size(coil3.size.Size.ORIGINAL) }
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier
                .matchParentSize(),
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
            //imageLoader = ImageLoader(LocalContext.current).newBuilder().build()
        )
    }
}