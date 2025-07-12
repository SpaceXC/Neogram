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

val NeogramIcons.Reset: ImageVector
    get() {
        if (_Reset != null) {
            return _Reset!!
        }
        _Reset = ImageVector.Builder(
            name = "Reset",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(18.732f, 4.812f)
                curveTo(18.803f, 4.404f, 19.193f, 4.132f, 19.601f, 4.203f)
                curveTo(20.008f, 4.275f, 20.281f, 4.664f, 20.209f, 5.072f)
                lineTo(19.4f, 9.676f)
                curveTo(19.332f, 10.058f, 18.986f, 10.321f, 18.607f, 10.293f)
                lineTo(18.531f, 10.284f)
                lineTo(13.927f, 9.475f)
                curveTo(13.519f, 9.403f, 13.247f, 9.015f, 13.318f, 8.607f)
                curveTo(13.39f, 8.199f, 13.779f, 7.926f, 14.187f, 7.997f)
                lineTo(17.231f, 8.532f)
                curveTo(17.222f, 8.522f, 17.212f, 8.512f, 17.203f, 8.501f)
                lineTo(16.892f, 8.11f)
                curveTo(14.743f, 5.408f, 10.811f, 4.959f, 8.11f, 7.108f)
                curveTo(5.408f, 9.256f, 4.96f, 13.188f, 7.109f, 15.89f)
                curveTo(9.257f, 18.591f, 13.188f, 19.04f, 15.89f, 16.892f)
                lineTo(16.673f, 16.27f)
                curveTo(16.997f, 16.012f, 17.469f, 16.066f, 17.727f, 16.39f)
                curveTo(17.984f, 16.714f, 17.931f, 17.186f, 17.607f, 17.444f)
                lineTo(16.823f, 18.066f)
                curveTo(13.473f, 20.73f, 8.598f, 20.174f, 5.934f, 16.823f)
                curveTo(3.27f, 13.473f, 3.826f, 8.598f, 7.176f, 5.934f)
                curveTo(10.526f, 3.27f, 15.401f, 3.826f, 18.066f, 7.176f)
                lineTo(18.27f, 7.433f)
                lineTo(18.732f, 4.812f)
                close()
            }
        }.build()

        return _Reset!!
    }

@Suppress("ObjectPropertyName")
private var _Reset: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ResetPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Reset, contentDescription = null)
    }
}
