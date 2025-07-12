package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Delete: ImageVector
    get() {
        if (_Delete != null) {
            return _Delete!!
        }
        _Delete = ImageVector.Builder(
            name = "Delete",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.699f, 7f)
                curveTo(5.313f, 7f, 5f, 7.313f, 5f, 7.699f)
                curveTo(5f, 8.085f, 5.313f, 8.398f, 5.699f, 8.398f)
                horizontalLineTo(6.364f)
                lineTo(6.397f, 8.76f)
                lineTo(7.107f, 16.724f)
                lineTo(7.11f, 16.756f)
                lineTo(7.11f, 16.756f)
                curveTo(7.128f, 16.959f, 7.148f, 17.177f, 7.178f, 17.365f)
                curveTo(7.212f, 17.577f, 7.274f, 17.849f, 7.423f, 18.129f)
                curveTo(7.681f, 18.615f, 8.099f, 18.997f, 8.606f, 19.211f)
                curveTo(8.898f, 19.335f, 9.174f, 19.372f, 9.389f, 19.387f)
                curveTo(9.579f, 19.401f, 9.798f, 19.401f, 10.001f, 19.401f)
                lineTo(10.034f, 19.401f)
                horizontalLineTo(13.905f)
                lineTo(13.938f, 19.401f)
                curveTo(14.141f, 19.401f, 14.36f, 19.401f, 14.55f, 19.387f)
                curveTo(14.765f, 19.372f, 15.04f, 19.335f, 15.333f, 19.211f)
                curveTo(15.84f, 18.997f, 16.257f, 18.615f, 16.516f, 18.129f)
                curveTo(16.665f, 17.849f, 16.727f, 17.577f, 16.761f, 17.365f)
                curveTo(16.791f, 17.177f, 16.811f, 16.959f, 16.829f, 16.756f)
                lineTo(16.831f, 16.724f)
                lineTo(17.542f, 8.76f)
                lineTo(17.575f, 8.398f)
                horizontalLineTo(18.24f)
                curveTo(18.626f, 8.398f, 18.939f, 8.085f, 18.939f, 7.699f)
                curveTo(18.939f, 7.313f, 18.626f, 7f, 18.24f, 7f)
                horizontalLineTo(5.699f)
                close()
                moveTo(7.789f, 8.636f)
                horizontalLineTo(9.193f)
                horizontalLineTo(14.746f)
                horizontalLineTo(16.15f)
                lineTo(16.025f, 10.034f)
                lineTo(15.439f, 16.599f)
                verticalLineTo(16.599f)
                curveTo(15.398f, 17.062f, 15.377f, 17.293f, 15.282f, 17.472f)
                curveTo(15.174f, 17.675f, 15f, 17.834f, 14.788f, 17.924f)
                curveTo(14.602f, 18.002f, 14.369f, 18.002f, 13.905f, 18.002f)
                horizontalLineTo(10.034f)
                curveTo(9.569f, 18.002f, 9.337f, 18.002f, 9.15f, 17.924f)
                curveTo(8.939f, 17.834f, 8.765f, 17.675f, 8.657f, 17.472f)
                curveTo(8.562f, 17.293f, 8.541f, 17.062f, 8.5f, 16.599f)
                lineTo(7.914f, 10.034f)
                lineTo(7.789f, 8.636f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(10f, 5f)
                horizontalLineTo(14f)
            }
        }.build()

        return _Delete!!
    }

@Suppress("ObjectPropertyName")
private var _Delete: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun DeletePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Delete, contentDescription = null)
    }
}
