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

val NeogramIcons.AppIcon: ImageVector
    get() {
        if (_AppIcon != null) {
            return _AppIcon!!
        }
        _AppIcon = ImageVector.Builder(
            name = "AppIcon",
            defaultWidth = 71.dp,
            defaultHeight = 24.dp,
            viewportWidth = 71f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(1f, 23f)
                verticalLineTo(12f)
                curveTo(1f, 5.925f, 5.925f, 1f, 12f, 1f)
                horizontalLineTo(17f)
                curveTo(18.657f, 1f, 20f, 2.343f, 20f, 4f)
                verticalLineTo(23f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(51f, 10.5f)
                verticalLineTo(13.5f)
                curveTo(51f, 18.747f, 55.253f, 23f, 60.5f, 23f)
                curveTo(65.747f, 23f, 70f, 18.747f, 70f, 13.5f)
                verticalLineTo(10.5f)
                curveTo(70f, 5.253f, 65.747f, 1f, 60.5f, 1f)
                curveTo(55.253f, 1f, 51f, 5.253f, 51f, 10.5f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(26f, 14.5f)
                horizontalLineTo(32.766f)
                curveTo(39.522f, 14.5f, 45f, 9.022f, 45f, 2.266f)
                verticalLineTo(2.266f)
                curveTo(45f, 1.567f, 44.433f, 1f, 43.734f, 1f)
                horizontalLineTo(37f)
                curveTo(30.925f, 1f, 26f, 5.925f, 26f, 12f)
                verticalLineTo(14f)
                curveTo(26f, 18.971f, 30.029f, 23f, 35f, 23f)
                horizontalLineTo(45f)
            }
        }.build()

        return _AppIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AppIcon: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AppIconPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.AppIcon, contentDescription = null)
    }
}
