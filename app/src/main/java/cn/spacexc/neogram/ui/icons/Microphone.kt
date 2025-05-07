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

val NeogramIcons.Microphone: ImageVector
    get() {
        if (_Microphone != null) {
            return _Microphone!!
        }
        _Microphone = ImageVector.Builder(
            name = "Microphone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(480f, 540f)
                quadToRelative(-41.92f, 0f, -70.96f, -29.04f)
                quadTo(380f, 481.92f, 380f, 440f)
                verticalLineToRelative(-240f)
                quadToRelative(0f, -41.92f, 29.04f, -70.96f)
                quadTo(438.08f, 100f, 480f, 100f)
                quadToRelative(41.92f, 0f, 70.96f, 29.04f)
                quadTo(580f, 158.08f, 580f, 200f)
                verticalLineToRelative(240f)
                quadToRelative(0f, 41.92f, -29.04f, 70.96f)
                quadTo(521.92f, 540f, 480f, 540f)
                close()
                moveTo(480f, 320f)
                close()
                moveTo(450f, 800f)
                verticalLineToRelative(-101.85f)
                quadToRelative(-89.31f, -10.31f, -153.08f, -73.38f)
                quadToRelative(-63.76f, -63.08f, -74.69f, -153.77f)
                quadToRelative(-1.61f, -12.77f, 6.69f, -21.88f)
                quadTo(237.23f, 440f, 250f, 440f)
                quadToRelative(12.77f, 0f, 21.38f, 8.81f)
                quadToRelative(8.62f, 8.8f, 11.47f, 21.57f)
                quadToRelative(10.92f, 73.47f, 67.19f, 121.54f)
                quadTo(406.31f, 640f, 480f, 640f)
                quadToRelative(74.69f, 0f, 130.46f, -48.58f)
                quadToRelative(55.77f, -48.57f, 66.69f, -121.04f)
                quadToRelative(2.85f, -12.77f, 11.47f, -21.57f)
                quadTo(697.23f, 440f, 710f, 440f)
                reflectiveQuadToRelative(21.08f, 9.12f)
                quadToRelative(8.3f, 9.11f, 6.69f, 21.88f)
                quadToRelative(-10.93f, 88.69f, -74.19f, 152.58f)
                quadToRelative(-63.27f, 63.88f, -153.58f, 74.57f)
                lineTo(510f, 800f)
                quadToRelative(0f, 12.77f, -8.62f, 21.38f)
                quadTo(492.77f, 830f, 480f, 830f)
                reflectiveQuadToRelative(-21.38f, -8.62f)
                quadTo(450f, 812.77f, 450f, 800f)
                close()
                moveTo(480f, 480f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(520f, 440f)
                verticalLineToRelative(-240f)
                quadToRelative(0f, -17f, -11.5f, -28.5f)
                reflectiveQuadTo(480f, 160f)
                quadToRelative(-17f, 0f, -28.5f, 11.5f)
                reflectiveQuadTo(440f, 200f)
                verticalLineToRelative(240f)
                quadToRelative(0f, 17f, 11.5f, 28.5f)
                reflectiveQuadTo(480f, 480f)
                close()
            }
        }.build()

        return _Microphone!!
    }

@Suppress("ObjectPropertyName")
private var _Microphone: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun MicrophonePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Microphone, contentDescription = null)
    }
}
