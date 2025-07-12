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

val NeogramIcons.Settings: ImageVector
    get() {
        if (_Settings != null) {
            return _Settings!!
        }
        _Settings = ImageVector.Builder(
            name = "Settings",
            defaultWidth = 23.dp,
            defaultHeight = 24.dp,
            viewportWidth = 23f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(9.942f, 2.806f)
                curveTo(10.91f, 2.265f, 12.09f, 2.265f, 13.058f, 2.806f)
                lineTo(18.37f, 5.783f)
                curveTo(19.376f, 6.347f, 20f, 7.41f, 20f, 8.563f)
                verticalLineTo(14.349f)
                lineTo(19.992f, 14.564f)
                curveTo(19.92f, 15.634f, 19.314f, 16.602f, 18.37f, 17.131f)
                lineTo(13.058f, 20.106f)
                lineTo(12.874f, 20.201f)
                curveTo(12.005f, 20.616f, 10.995f, 20.616f, 10.126f, 20.201f)
                lineTo(9.942f, 20.106f)
                lineTo(4.63f, 17.131f)
                curveTo(3.624f, 16.567f, 3f, 15.503f, 3f, 14.349f)
                verticalLineTo(8.563f)
                curveTo(3f, 7.482f, 3.549f, 6.48f, 4.446f, 5.894f)
                lineTo(4.63f, 5.783f)
                lineTo(9.942f, 2.806f)
                close()
                moveTo(12.279f, 4.197f)
                curveTo(11.796f, 3.926f, 11.205f, 3.926f, 10.722f, 4.197f)
                lineTo(5.409f, 7.173f)
                curveTo(4.906f, 7.454f, 4.594f, 7.986f, 4.594f, 8.563f)
                verticalLineTo(14.349f)
                curveTo(4.594f, 14.926f, 4.906f, 15.458f, 5.409f, 15.74f)
                lineTo(10.722f, 18.715f)
                curveTo(11.205f, 18.986f, 11.796f, 18.986f, 12.279f, 18.715f)
                lineTo(17.591f, 15.74f)
                curveTo(18.094f, 15.458f, 18.406f, 14.926f, 18.406f, 14.349f)
                verticalLineTo(8.563f)
                curveTo(18.406f, 7.987f, 18.094f, 7.454f, 17.591f, 7.173f)
                lineTo(12.279f, 4.197f)
                close()
                moveTo(11.848f, 7.23f)
                curveTo(14.084f, 7.344f, 15.861f, 9.193f, 15.861f, 11.457f)
                lineTo(15.856f, 11.675f)
                curveTo(15.747f, 13.838f, 14.011f, 15.573f, 11.848f, 15.683f)
                lineTo(11.63f, 15.688f)
                horizontalLineTo(11.37f)
                lineTo(11.152f, 15.683f)
                curveTo(8.988f, 15.574f, 7.253f, 13.838f, 7.144f, 11.675f)
                lineTo(7.138f, 11.457f)
                curveTo(7.138f, 9.192f, 8.916f, 7.343f, 11.152f, 7.23f)
                lineTo(11.37f, 7.224f)
                horizontalLineTo(11.63f)
                lineTo(11.848f, 7.23f)
                close()
                moveTo(11.37f, 8.818f)
                curveTo(9.913f, 8.818f, 8.732f, 10f, 8.731f, 11.457f)
                curveTo(8.732f, 12.914f, 9.913f, 14.094f, 11.37f, 14.094f)
                horizontalLineTo(11.63f)
                curveTo(13.087f, 14.094f, 14.268f, 12.914f, 14.269f, 11.457f)
                curveTo(14.269f, 10f, 13.087f, 8.818f, 11.63f, 8.818f)
                horizontalLineTo(11.37f)
                close()
            }
        }.build()

        return _Settings!!
    }

@Suppress("ObjectPropertyName")
private var _Settings: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun SettingsPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Settings, contentDescription = null)
    }
}
