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

val NeogramIcons.Link: ImageVector
    get() {
        if (_Link != null) {
            return _Link!!
        }
        _Link = ImageVector.Builder(
            name = "Link",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(15f, 8.5f)
                verticalLineTo(8.5f)
                curveTo(15f, 6.567f, 13.433f, 5f, 11.5f, 5f)
                lineTo(8.5f, 5f)
                curveTo(6.567f, 5f, 5f, 6.567f, 5f, 8.5f)
                verticalLineTo(8.5f)
                curveTo(5f, 10.433f, 6.567f, 12f, 8.5f, 12f)
                curveTo(9.021f, 12f, 9.537f, 12f, 10f, 12f)
                curveTo(12f, 12f, 12f, 13.5f, 12f, 13.5f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeAlpha = 0.5f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9f, 14.5f)
                verticalLineTo(14.5f)
                curveTo(9f, 16.433f, 10.567f, 18f, 12.5f, 18f)
                lineTo(15.5f, 18f)
                curveTo(17.433f, 18f, 19f, 16.433f, 19f, 14.5f)
                verticalLineTo(14.5f)
                curveTo(19f, 12.567f, 17.433f, 11f, 15.5f, 11f)
                curveTo(14.633f, 11f, 13.851f, 11f, 13.5f, 11f)
                curveTo(12.5f, 11f, 12.5f, 9.5f, 12.5f, 9.5f)
            }
        }.build()

        return _Link!!
    }

@Suppress("ObjectPropertyName")
private var _Link: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun LinkPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Link, contentDescription = null)
    }
}
