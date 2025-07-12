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

val NeogramIcons.Love: ImageVector
    get() {
        if (_Love != null) {
            return _Love!!
        }
        _Love = ImageVector.Builder(
            name = "Love",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(5.545f, 6.522f)
                curveTo(7.256f, 4.798f, 10.008f, 4.831f, 11.68f, 6.596f)
                lineTo(12.056f, 6.992f)
                lineTo(12.351f, 6.695f)
                curveTo(14.04f, 4.983f, 16.755f, 4.979f, 18.449f, 6.686f)
                curveTo(20.167f, 8.417f, 20.186f, 11.252f, 18.491f, 13.006f)
                lineTo(13.584f, 18.086f)
                curveTo(12.727f, 18.973f, 11.323f, 18.971f, 10.47f, 18.08f)
                lineTo(9.648f, 17.223f)
                curveTo(9.528f, 17.097f, 9.416f, 16.966f, 9.31f, 16.832f)
                lineTo(5.474f, 12.784f)
                curveTo(3.815f, 11.033f, 3.846f, 8.234f, 5.545f, 6.522f)
                close()
            }
        }.build()

        return _Love!!
    }

@Suppress("ObjectPropertyName")
private var _Love: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun LovePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Love, contentDescription = null)
    }
}
