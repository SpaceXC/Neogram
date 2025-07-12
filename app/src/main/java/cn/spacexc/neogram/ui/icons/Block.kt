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

val NeogramIcons.Block: ImageVector
    get() {
        if (_Block != null) {
            return _Block!!
        }
        _Block = ImageVector.Builder(
            name = "Block",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(12f, 4.75f)
                lineTo(12f, 4.75f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.25f, 12f)
                lineTo(19.25f, 12f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 19.25f)
                lineTo(12f, 19.25f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.75f, 12f)
                lineTo(4.75f, 12f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 4.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(8f, 12f)
                horizontalLineTo(16f)
            }
        }.build()

        return _Block!!
    }

@Suppress("ObjectPropertyName")
private var _Block: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun BlockPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Block, contentDescription = null)
    }
}
