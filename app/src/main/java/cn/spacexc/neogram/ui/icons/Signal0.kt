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

val NeogramIcons.Signal0: ImageVector
    get() {
        if (_Signal0 != null) {
            return _Signal0!!
        }
        _Signal0 = ImageVector.Builder(
            name = "Signal0",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeAlpha = 0.2f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(8f, 8f)
                lineTo(8f, 15f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeAlpha = 0.2f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12f, 11f)
                lineTo(12f, 15f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeAlpha = 0.2f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(16f, 14f)
                lineTo(16f, 15f)
            }
        }.build()

        return _Signal0!!
    }

@Suppress("ObjectPropertyName")
private var _Signal0: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun Signal0Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Signal0, contentDescription = null)
    }
}
