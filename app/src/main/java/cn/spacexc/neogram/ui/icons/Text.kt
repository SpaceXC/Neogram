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

val NeogramIcons.Text: ImageVector
    get() {
        if (_Text != null) {
            return _Text!!
        }
        _Text = ImageVector.Builder(
            name = "Text",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 22f,
            viewportHeight = 22f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11.917f, 5.5f)
                lineTo(10.083f, 4.583f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(5.5f, 8.25f)
                lineTo(16.5f, 8.25f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(7.38f, 8.25f)
                curveTo(9.129f, 12.569f, 11.711f, 15.247f, 16.958f, 16.722f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(14.473f, 8.25f)
                curveTo(12.724f, 12.569f, 10.142f, 15.247f, 4.894f, 16.722f)
            }
        }.build()

        return _Text!!
    }

@Suppress("ObjectPropertyName")
private var _Text: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Text, contentDescription = null)
    }
}
