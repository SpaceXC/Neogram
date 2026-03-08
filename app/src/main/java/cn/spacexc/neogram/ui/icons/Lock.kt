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

val NeogramIcons.Lock: ImageVector
    get() {
        if (_Lock != null) {
            return _Lock!!
        }
        _Lock = ImageVector.Builder(
            name = "Lock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.905f, 4f)
                curveTo(9.501f, 4f, 7.552f, 5.949f, 7.552f, 8.353f)
                verticalLineTo(9.799f)
                curveTo(7.466f, 9.829f, 7.381f, 9.863f, 7.295f, 9.901f)
                curveTo(6.43f, 10.292f, 5.736f, 10.986f, 5.346f, 11.851f)
                curveTo(5.137f, 12.314f, 5.063f, 12.78f, 5.031f, 13.223f)
                curveTo(5f, 13.64f, 5f, 14.141f, 5f, 14.703f)
                verticalLineTo(14.703f)
                lineTo(5f, 14.752f)
                verticalLineTo(14.927f)
                lineTo(5f, 14.976f)
                verticalLineTo(14.976f)
                curveTo(5f, 15.538f, 5f, 16.039f, 5.031f, 16.456f)
                curveTo(5.063f, 16.899f, 5.137f, 17.365f, 5.346f, 17.828f)
                curveTo(5.736f, 18.694f, 6.43f, 19.387f, 7.295f, 19.778f)
                curveTo(7.758f, 19.986f, 8.224f, 20.06f, 8.667f, 20.093f)
                curveTo(9.084f, 20.123f, 9.585f, 20.123f, 10.147f, 20.123f)
                horizontalLineTo(10.196f)
                horizontalLineTo(13.614f)
                horizontalLineTo(13.664f)
                curveTo(14.226f, 20.123f, 14.726f, 20.123f, 15.143f, 20.093f)
                curveTo(15.587f, 20.06f, 16.052f, 19.986f, 16.515f, 19.778f)
                curveTo(17.381f, 19.387f, 18.074f, 18.694f, 18.465f, 17.828f)
                curveTo(18.674f, 17.365f, 18.747f, 16.899f, 18.78f, 16.456f)
                curveTo(18.81f, 16.039f, 18.81f, 15.538f, 18.81f, 14.976f)
                lineTo(18.81f, 14.927f)
                verticalLineTo(14.752f)
                lineTo(18.81f, 14.703f)
                curveTo(18.81f, 14.141f, 18.81f, 13.64f, 18.78f, 13.223f)
                curveTo(18.747f, 12.78f, 18.674f, 12.314f, 18.465f, 11.851f)
                curveTo(18.074f, 10.986f, 17.381f, 10.292f, 16.515f, 9.901f)
                curveTo(16.43f, 9.863f, 16.344f, 9.829f, 16.258f, 9.799f)
                verticalLineTo(8.353f)
                curveTo(16.258f, 5.949f, 14.309f, 4f, 11.905f, 4f)
                close()
                moveTo(14.759f, 9.567f)
                verticalLineTo(8.353f)
                curveTo(14.759f, 6.778f, 13.481f, 5.5f, 11.905f, 5.5f)
                curveTo(10.329f, 5.5f, 9.052f, 6.778f, 9.052f, 8.353f)
                verticalLineTo(9.567f)
                curveTo(9.38f, 9.556f, 9.748f, 9.556f, 10.147f, 9.556f)
                lineTo(10.196f, 9.556f)
                horizontalLineTo(13.614f)
                lineTo(13.664f, 9.556f)
                curveTo(14.062f, 9.556f, 14.43f, 9.556f, 14.759f, 9.567f)
                close()
                moveTo(6.5f, 14.752f)
                curveTo(6.5f, 13.543f, 6.5f, 12.939f, 6.713f, 12.468f)
                curveTo(6.953f, 11.936f, 7.38f, 11.509f, 7.912f, 11.269f)
                curveTo(8.383f, 11.056f, 8.988f, 11.056f, 10.196f, 11.056f)
                horizontalLineTo(13.614f)
                curveTo(14.823f, 11.056f, 15.427f, 11.056f, 15.898f, 11.269f)
                curveTo(16.431f, 11.509f, 16.857f, 11.936f, 17.098f, 12.468f)
                curveTo(17.31f, 12.939f, 17.31f, 13.543f, 17.31f, 14.752f)
                verticalLineTo(14.927f)
                curveTo(17.31f, 16.136f, 17.31f, 16.74f, 17.098f, 17.211f)
                curveTo(16.857f, 17.743f, 16.431f, 18.17f, 15.898f, 18.41f)
                curveTo(15.427f, 18.623f, 14.823f, 18.623f, 13.614f, 18.623f)
                horizontalLineTo(10.196f)
                curveTo(8.988f, 18.623f, 8.383f, 18.623f, 7.912f, 18.41f)
                curveTo(7.38f, 18.17f, 6.953f, 17.743f, 6.713f, 17.211f)
                curveTo(6.5f, 16.74f, 6.5f, 16.136f, 6.5f, 14.927f)
                verticalLineTo(14.752f)
                close()
            }
        }.build()

        return _Lock!!
    }

@Suppress("ObjectPropertyName")
private var _Lock: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun LockPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Lock, contentDescription = null)
    }
}
