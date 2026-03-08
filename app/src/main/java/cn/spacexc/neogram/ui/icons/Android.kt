package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Android: ImageVector
    get() {
        if (_Android != null) {
            return _Android!!
        }
        _Android = ImageVector.Builder(
            name = "Android",
            defaultWidth = 19.dp,
            defaultHeight = 19.dp,
            viewportWidth = 19f,
            viewportHeight = 19f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9.324f, 7.587f)
                curveTo(11.192f, 7.587f, 12.576f, 8.106f, 13.587f, 8.864f)
                curveTo(14.591f, 9.618f, 15.19f, 10.578f, 15.545f, 11.409f)
                curveTo(15.837f, 12.093f, 15.694f, 12.779f, 15.29f, 13.276f)
                curveTo(14.896f, 13.76f, 14.272f, 14.052f, 13.594f, 14.052f)
                horizontalLineTo(5.053f)
                curveTo(4.376f, 14.052f, 3.752f, 13.76f, 3.358f, 13.276f)
                curveTo(2.954f, 12.779f, 2.811f, 12.093f, 3.103f, 11.409f)
                curveTo(3.458f, 10.578f, 4.057f, 9.618f, 5.061f, 8.864f)
                curveTo(6.072f, 8.106f, 7.455f, 7.587f, 9.324f, 7.587f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11.963f, 7.741f)
                lineTo(13.722f, 5.542f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(6.685f, 7.741f)
                lineTo(4.926f, 5.542f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(7.565f, 10.819f)
                lineTo(7.565f, 9.94f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11.083f, 10.819f)
                lineTo(11.083f, 9.94f)
            }
        }.build()

        return _Android!!
    }

@Suppress("ObjectPropertyName")
private var _Android: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AndroidPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Android, contentDescription = null)
    }
}
