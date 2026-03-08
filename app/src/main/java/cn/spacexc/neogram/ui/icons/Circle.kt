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

val NeogramIcons.Circle: ImageVector
    get() {
        if (_Circle != null) {
            return _Circle!!
        }
        _Circle = ImageVector.Builder(
            name = "Circle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(12f, 5.75f)
                lineTo(12f, 5.75f)
                arcTo(6.25f, 6.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 18.25f, 12f)
                lineTo(18.25f, 12f)
                arcTo(6.25f, 6.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 18.25f)
                lineTo(12f, 18.25f)
                arcTo(6.25f, 6.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.75f, 12f)
                lineTo(5.75f, 12f)
                arcTo(6.25f, 6.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 5.75f)
                close()
            }
        }.build()

        return _Circle!!
    }

@Suppress("ObjectPropertyName")
private var _Circle: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CirclePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Circle, contentDescription = null)
    }
}
