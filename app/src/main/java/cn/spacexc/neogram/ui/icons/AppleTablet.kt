package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.AppleTablet: ImageVector
    get() {
        if (_AppleTablet != null) {
            return _AppleTablet!!
        }
        _AppleTablet = ImageVector.Builder(
            name = "AppleTablet",
            defaultWidth = 19.dp,
            defaultHeight = 19.dp,
            viewportWidth = 19f,
            viewportHeight = 19f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.1875f
            ) {
                moveTo(7.125f, 3.365f)
                lineTo(11.875f, 3.365f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.052f, 5.542f)
                lineTo(14.052f, 13.458f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 11.875f, 15.635f)
                lineTo(7.125f, 15.635f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.948f, 13.458f)
                lineTo(4.948f, 5.542f)
                arcTo(2.177f, 2.177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7.125f, 3.365f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(8.708f, 5.542f)
                curveTo(8.708f, 5.104f, 9.063f, 4.75f, 9.5f, 4.75f)
                curveTo(9.937f, 4.75f, 10.292f, 5.104f, 10.292f, 5.542f)
                curveTo(10.292f, 5.979f, 9.937f, 6.333f, 9.5f, 6.333f)
                curveTo(9.063f, 6.333f, 8.708f, 5.979f, 8.708f, 5.542f)
                close()
            }
        }.build()

        return _AppleTablet!!
    }

@Suppress("ObjectPropertyName")
private var _AppleTablet: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AppleTabletPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.AppleTablet, contentDescription = null)
    }
}
