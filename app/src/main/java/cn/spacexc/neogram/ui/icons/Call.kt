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

val NeogramIcons.Call: ImageVector
    get() {
        if (_Call != null) {
            return _Call!!
        }
        _Call = ImageVector.Builder(
            name = "Call",
            defaultWidth = 22.dp,
            defaultHeight = 23.dp,
            viewportWidth = 22f,
            viewportHeight = 23f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.42512f
            ) {
                moveTo(4.082f, 5.047f)
                curveTo(4.372f, 4.637f, 5.178f, 4.117f, 5.762f, 3.774f)
                curveTo(6.141f, 3.551f, 6.62f, 3.643f, 6.908f, 3.976f)
                lineTo(8.893f, 6.271f)
                curveTo(9.144f, 6.561f, 9.196f, 6.974f, 9.024f, 7.317f)
                lineTo(8.172f, 9.021f)
                curveTo(7.997f, 9.371f, 8.054f, 9.792f, 8.316f, 10.082f)
                lineTo(11.51f, 13.631f)
                curveTo(11.866f, 14.026f, 12.477f, 14.053f, 12.892f, 13.72f)
                curveTo(13.25f, 13.432f, 13.683f, 13.115f, 14.058f, 12.926f)
                curveTo(14.872f, 12.516f, 15.28f, 12.451f, 16.094f, 12.926f)
                curveTo(16.908f, 13.401f, 18.333f, 14.826f, 18.333f, 16.055f)
                curveTo(18.333f, 17.283f, 16.968f, 17.676f, 15.473f, 17.955f)
                curveTo(13.977f, 18.233f, 11.969f, 17.152f, 10.476f, 16.055f)
                curveTo(8.983f, 14.957f, 4.71f, 10.288f, 4.082f, 8.716f)
                curveTo(3.454f, 7.143f, 3.607f, 5.718f, 4.082f, 5.047f)
                close()
            }
        }.build()

        return _Call!!
    }

@Suppress("ObjectPropertyName")
private var _Call: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CallPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Call, contentDescription = null)
    }
}
