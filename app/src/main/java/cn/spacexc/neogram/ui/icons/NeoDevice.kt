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

val NeogramIcons.NeoDevice: ImageVector
    get() {
        if (_NeoDevice != null) {
            return _NeoDevice!!
        }
        _NeoDevice = ImageVector.Builder(
            name = "NeoDevice",
            defaultWidth = 21.dp,
            defaultHeight = 21.dp,
            viewportWidth = 21f,
            viewportHeight = 21f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.37931f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11.708f, 5.516f)
                horizontalLineTo(10.129f)
                curveTo(8.966f, 5.516f, 8.024f, 6.459f, 8.024f, 7.622f)
                verticalLineTo(7.885f)
                moveTo(9.603f, 14.99f)
                horizontalLineTo(10.655f)
                curveTo(11.818f, 14.99f, 12.761f, 14.047f, 12.761f, 12.885f)
                verticalLineTo(12.622f)
                moveTo(15.392f, 14.99f)
                lineTo(15.392f, 12.314f)
                curveTo(15.392f, 9.668f, 12.078f, 8.476f, 10.392f, 10.516f)
                curveTo(8.707f, 12.557f, 5.392f, 11.365f, 5.392f, 8.718f)
                verticalLineTo(4.99f)
            }
        }.build()

        return _NeoDevice!!
    }

@Suppress("ObjectPropertyName")
private var _NeoDevice: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun NeoDevicePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.NeoDevice, contentDescription = null)
    }
}
