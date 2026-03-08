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

val NeogramIcons.ApplePhone: ImageVector
    get() {
        if (_ApplePhone != null) {
            return _ApplePhone!!
        }
        _ApplePhone = ImageVector.Builder(
            name = "ApplePhone",
            defaultWidth = 19.dp,
            defaultHeight = 19.dp,
            viewportWidth = 19f,
            viewportHeight = 19f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f
            ) {
                moveTo(7.917f, 3.365f)
                lineTo(11.083f, 3.365f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13.26f, 5.542f)
                lineTo(13.26f, 13.458f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 11.083f, 15.635f)
                lineTo(7.917f, 15.635f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.74f, 13.458f)
                lineTo(5.74f, 5.542f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7.917f, 3.365f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(8.708f, 5.542f)
                horizontalLineTo(10.292f)
            }
        }.build()

        return _ApplePhone!!
    }

@Suppress("ObjectPropertyName")
private var _ApplePhone: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ApplePhonePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ApplePhone, contentDescription = null)
    }
}
