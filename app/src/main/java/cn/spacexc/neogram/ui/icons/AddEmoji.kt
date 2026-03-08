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

val NeogramIcons.AddEmoji: ImageVector
    get() {
        if (_AddEmoji != null) {
            return _AddEmoji!!
        }
        _AddEmoji = ImageVector.Builder(
            name = "AddEmoji",
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
                moveTo(13f, 19f)
                horizontalLineTo(12f)
                curveTo(8.134f, 19f, 5f, 15.866f, 5f, 12f)
                curveTo(5f, 8.134f, 8.134f, 5f, 12f, 5f)
                curveTo(15.866f, 5f, 19f, 8.134f, 19f, 12f)
                verticalLineTo(12.5f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(15f, 17f)
                horizontalLineTo(19f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(17.035f, 15f)
                lineTo(16.965f, 18.999f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(10f, 9f)
                verticalLineTo(10f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9f, 13f)
                curveTo(9f, 13f, 10.2f, 14f, 12.2f, 14f)
                curveTo(14.2f, 14f, 15f, 13f, 15f, 13f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(14f, 9f)
                verticalLineTo(10f)
            }
        }.build()

        return _AddEmoji!!
    }

@Suppress("ObjectPropertyName")
private var _AddEmoji: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AddEmojiPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.AddEmoji, contentDescription = null)
    }
}
