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
            defaultWidth = 10.dp,
            defaultHeight = 12.dp,
            viewportWidth = 10f,
            viewportHeight = 12f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5f, 0f)
                curveTo(3.251f, 0f, 1.833f, 1.446f, 1.833f, 3.229f)
                verticalLineTo(4.372f)
                curveTo(1.732f, 4.401f, 1.631f, 4.438f, 1.53f, 4.484f)
                curveTo(0.953f, 4.75f, 0.491f, 5.221f, 0.23f, 5.81f)
                curveTo(0.091f, 6.124f, 0.042f, 6.441f, 0.02f, 6.743f)
                curveTo(-0f, 7.026f, -0f, 7.366f, 0f, 7.748f)
                verticalLineTo(7.748f)
                lineTo(0f, 7.782f)
                verticalLineTo(8.467f)
                lineTo(0f, 8.501f)
                verticalLineTo(8.501f)
                curveTo(-0f, 8.883f, -0f, 9.223f, 0.02f, 9.507f)
                curveTo(0.042f, 9.808f, 0.091f, 10.125f, 0.23f, 10.44f)
                curveTo(0.491f, 11.028f, 0.953f, 11.499f, 1.53f, 11.765f)
                curveTo(1.838f, 11.907f, 2.149f, 11.957f, 2.445f, 11.979f)
                curveTo(2.723f, 12f, 3.056f, 12f, 3.431f, 12f)
                lineTo(3.464f, 12f)
                horizontalLineTo(6.536f)
                lineTo(6.569f, 12f)
                curveTo(6.944f, 12f, 7.277f, 12f, 7.555f, 11.979f)
                curveTo(7.851f, 11.957f, 8.162f, 11.907f, 8.47f, 11.765f)
                curveTo(9.047f, 11.499f, 9.509f, 11.028f, 9.77f, 10.44f)
                curveTo(9.909f, 10.125f, 9.958f, 9.808f, 9.98f, 9.507f)
                curveTo(10f, 9.223f, 10f, 8.883f, 10f, 8.501f)
                lineTo(10f, 8.467f)
                verticalLineTo(7.782f)
                lineTo(10f, 7.748f)
                curveTo(10f, 7.366f, 10f, 7.026f, 9.98f, 6.743f)
                curveTo(9.958f, 6.441f, 9.909f, 6.124f, 9.77f, 5.81f)
                curveTo(9.509f, 5.221f, 9.047f, 4.75f, 8.47f, 4.484f)
                curveTo(8.369f, 4.438f, 8.268f, 4.401f, 8.167f, 4.372f)
                verticalLineTo(3.229f)
                curveTo(8.167f, 1.446f, 6.749f, 0f, 5f, 0f)
                close()
                moveTo(7.167f, 4.253f)
                verticalLineTo(3.229f)
                curveTo(7.167f, 2.009f, 6.197f, 1.02f, 5f, 1.02f)
                curveTo(3.803f, 1.02f, 2.833f, 2.009f, 2.833f, 3.229f)
                verticalLineTo(4.253f)
                curveTo(3.017f, 4.249f, 3.218f, 4.249f, 3.431f, 4.249f)
                lineTo(3.464f, 4.249f)
                horizontalLineTo(6.536f)
                lineTo(6.569f, 4.249f)
                curveTo(6.782f, 4.249f, 6.983f, 4.249f, 7.167f, 4.253f)
                close()
                moveTo(1f, 7.782f)
                curveTo(1f, 6.96f, 1f, 6.55f, 1.142f, 6.229f)
                curveTo(1.302f, 5.867f, 1.586f, 5.577f, 1.942f, 5.414f)
                curveTo(2.256f, 5.269f, 2.658f, 5.269f, 3.464f, 5.269f)
                horizontalLineTo(6.536f)
                curveTo(7.342f, 5.269f, 7.744f, 5.269f, 8.058f, 5.414f)
                curveTo(8.414f, 5.577f, 8.698f, 5.867f, 8.858f, 6.229f)
                curveTo(9f, 6.55f, 9f, 6.96f, 9f, 7.782f)
                verticalLineTo(8.467f)
                curveTo(9f, 9.289f, 9f, 9.7f, 8.858f, 10.02f)
                curveTo(8.698f, 10.382f, 8.414f, 10.672f, 8.058f, 10.836f)
                curveTo(7.744f, 10.98f, 7.342f, 10.98f, 6.536f, 10.98f)
                horizontalLineTo(3.464f)
                curveTo(2.658f, 10.98f, 2.256f, 10.98f, 1.942f, 10.836f)
                curveTo(1.586f, 10.672f, 1.302f, 10.382f, 1.142f, 10.02f)
                curveTo(1f, 9.7f, 1f, 9.289f, 1f, 8.467f)
                verticalLineTo(7.782f)
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
