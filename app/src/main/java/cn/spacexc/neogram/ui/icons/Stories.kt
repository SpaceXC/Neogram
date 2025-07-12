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

val NeogramIcons.Stories: ImageVector
    get() {
        if (_Stories != null) {
            return _Stories!!
        }
        _Stories = ImageVector.Builder(
            name = "Stories",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12.5f, 5f)
                horizontalLineTo(12f)
                curveTo(8.134f, 5f, 5f, 8.134f, 5f, 12f)
                verticalLineTo(12f)
                curveTo(5f, 15.866f, 8.134f, 19f, 12f, 19f)
                verticalLineTo(19f)
                verticalLineTo(19f)
                curveTo(15.866f, 19f, 19f, 15.866f, 19f, 12f)
                verticalLineTo(11f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12f, 5f)
                verticalLineTo(5f)
                curveTo(15.866f, 5f, 19f, 8.134f, 19f, 12f)
                verticalLineTo(12f)
            }
        }.build()

        return _Stories!!
    }

@Suppress("ObjectPropertyName")
private var _Stories: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun StoriesPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Stories, contentDescription = null)
    }
}
