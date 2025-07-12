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

val NeogramIcons.Select: ImageVector
    get() {
        if (_Select != null) {
            return _Select!!
        }
        _Select = ImageVector.Builder(
            name = "Select",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(12f, 12f)
                moveToRelative(-6.75f, 0f)
                arcToRelative(6.75f, 6.75f, 0f, isMoreThanHalf = true, isPositiveArc = true, 13.5f, 0f)
                arcToRelative(6.75f, 6.75f, 0f, isMoreThanHalf = true, isPositiveArc = true, -13.5f, 0f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11f, 14f)
                lineTo(9f, 12f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11f, 14f)
                lineTo(15f, 10f)
            }
        }.build()

        return _Select!!
    }

@Suppress("ObjectPropertyName")
private var _Select: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun SelectPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Select, contentDescription = null)
    }
}
