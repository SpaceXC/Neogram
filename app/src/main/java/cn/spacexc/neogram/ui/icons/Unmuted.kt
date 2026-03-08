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

val NeogramIcons.Unmuted: ImageVector
    get() {
        if (_Unmuted != null) {
            return _Unmuted!!
        }
        _Unmuted = ImageVector.Builder(
            name = "Unmuted",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 22f,
            viewportHeight = 22f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f
            ) {
                moveTo(11f, 3.896f)
                curveTo(13.911f, 3.896f, 16.271f, 6.256f, 16.271f, 9.166f)
                verticalLineTo(12.417f)
                curveTo(16.271f, 12.833f, 16.479f, 13.221f, 16.826f, 13.45f)
                curveTo(17.683f, 14.018f, 17.282f, 15.354f, 16.253f, 15.354f)
                horizontalLineTo(5.779f)
                curveTo(4.735f, 15.354f, 4.293f, 14.026f, 5.127f, 13.4f)
                lineTo(5.262f, 13.285f)
                curveTo(5.559f, 13.003f, 5.729f, 12.61f, 5.729f, 12.196f)
                verticalLineTo(9.166f)
                curveTo(5.729f, 6.255f, 8.089f, 3.896f, 11f, 3.896f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(9.167f, 15.583f)
                curveTo(9.167f, 16.596f, 9.987f, 17.417f, 11f, 17.417f)
                curveTo(12.012f, 17.417f, 12.833f, 16.596f, 12.833f, 15.583f)
                verticalLineTo(15.059f)
                curveTo(12.833f, 14.843f, 12.657f, 14.667f, 12.441f, 14.667f)
                horizontalLineTo(9.56f)
                curveTo(9.343f, 14.667f, 9.167f, 14.843f, 9.167f, 15.059f)
                verticalLineTo(15.583f)
                close()
            }
        }.build()

        return _Unmuted!!
    }

@Suppress("ObjectPropertyName")
private var _Unmuted: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun UnmutedPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Unmuted, contentDescription = null)
    }
}
