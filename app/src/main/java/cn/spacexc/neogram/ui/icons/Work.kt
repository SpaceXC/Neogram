package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Work: ImageVector
    get() {
        if (_Work != null) {
            return _Work!!
        }
        _Work = ImageVector.Builder(
            name = "Work",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.794f, 8.306f)
                horizontalLineTo(14.206f)
                curveTo(14f, 7.276f, 13.091f, 6.5f, 12f, 6.5f)
                curveTo(10.909f, 6.5f, 10f, 7.276f, 9.794f, 8.306f)
                close()
                moveTo(15.729f, 8.351f)
                curveTo(16.055f, 8.387f, 16.402f, 8.459f, 16.75f, 8.616f)
                curveTo(17.527f, 8.967f, 18.149f, 9.589f, 18.5f, 10.366f)
                curveTo(18.691f, 10.789f, 18.756f, 11.21f, 18.784f, 11.594f)
                curveTo(18.81f, 11.95f, 18.81f, 12.376f, 18.81f, 12.839f)
                verticalLineTo(12.839f)
                lineTo(18.81f, 12.886f)
                verticalLineTo(14.293f)
                lineTo(18.81f, 14.34f)
                verticalLineTo(14.34f)
                curveTo(18.81f, 14.803f, 18.81f, 15.229f, 18.784f, 15.585f)
                curveTo(18.756f, 15.969f, 18.691f, 16.39f, 18.5f, 16.813f)
                curveTo(18.149f, 17.59f, 17.527f, 18.212f, 16.75f, 18.563f)
                curveTo(16.327f, 18.754f, 15.906f, 18.819f, 15.522f, 18.847f)
                curveTo(15.166f, 18.873f, 14.74f, 18.873f, 14.277f, 18.873f)
                horizontalLineTo(14.277f)
                lineTo(14.23f, 18.873f)
                horizontalLineTo(9.58f)
                lineTo(9.533f, 18.873f)
                horizontalLineTo(9.533f)
                curveTo(9.07f, 18.873f, 8.644f, 18.873f, 8.288f, 18.847f)
                curveTo(7.904f, 18.819f, 7.483f, 18.754f, 7.06f, 18.563f)
                curveTo(6.283f, 18.212f, 5.661f, 17.59f, 5.31f, 16.813f)
                curveTo(5.119f, 16.39f, 5.054f, 15.969f, 5.026f, 15.585f)
                curveTo(5f, 15.229f, 5f, 14.803f, 5f, 14.34f)
                verticalLineTo(14.34f)
                lineTo(5f, 14.293f)
                verticalLineTo(12.886f)
                lineTo(5f, 12.839f)
                verticalLineTo(12.839f)
                curveTo(5f, 12.376f, 5f, 11.95f, 5.026f, 11.594f)
                curveTo(5.054f, 11.21f, 5.119f, 10.789f, 5.31f, 10.366f)
                curveTo(5.661f, 9.589f, 6.283f, 8.967f, 7.06f, 8.616f)
                curveTo(7.478f, 8.427f, 7.893f, 8.362f, 8.273f, 8.333f)
                curveTo(8.48f, 6.458f, 10.07f, 5f, 12f, 5f)
                curveTo(13.936f, 5f, 15.53f, 6.468f, 15.729f, 8.351f)
                close()
                moveTo(6.5f, 12.886f)
                curveTo(6.5f, 11.879f, 6.5f, 11.375f, 6.677f, 10.983f)
                curveTo(6.878f, 10.539f, 7.233f, 10.184f, 7.677f, 9.983f)
                curveTo(8.069f, 9.806f, 8.573f, 9.806f, 9.58f, 9.806f)
                horizontalLineTo(14.23f)
                curveTo(15.237f, 9.806f, 15.741f, 9.806f, 16.133f, 9.983f)
                curveTo(16.577f, 10.184f, 16.933f, 10.539f, 17.133f, 10.983f)
                curveTo(17.31f, 11.375f, 17.31f, 11.879f, 17.31f, 12.886f)
                verticalLineTo(14.293f)
                curveTo(17.31f, 15.3f, 17.31f, 15.804f, 17.133f, 16.196f)
                curveTo(16.933f, 16.64f, 16.577f, 16.996f, 16.133f, 17.196f)
                curveTo(15.741f, 17.373f, 15.237f, 17.373f, 14.23f, 17.373f)
                horizontalLineTo(9.58f)
                curveTo(8.573f, 17.373f, 8.069f, 17.373f, 7.677f, 17.196f)
                curveTo(7.233f, 16.996f, 6.878f, 16.64f, 6.677f, 16.196f)
                curveTo(6.5f, 15.804f, 6.5f, 15.3f, 6.5f, 14.293f)
                verticalLineTo(12.886f)
                close()
            }
        }.build()

        return _Work!!
    }

@Suppress("ObjectPropertyName")
private var _Work: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun WorkPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Work, contentDescription = null)
    }
}
