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

val NeogramIcons.Earphone: ImageVector
    get() {
        if (_Earphone != null) {
            return _Earphone!!
        }
        _Earphone = ImageVector.Builder(
            name = "Earphone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(7.8f, 6.75f)
                horizontalLineTo(8.733f)
                curveTo(9.865f, 6.75f, 10.783f, 7.668f, 10.783f, 8.8f)
                verticalLineTo(9.733f)
                curveTo(10.783f, 10.866f, 9.865f, 11.783f, 8.733f, 11.783f)
                curveTo(7.601f, 11.783f, 6.683f, 10.866f, 6.683f, 9.733f)
                verticalLineTo(7.866f)
                curveTo(6.684f, 7.25f, 7.183f, 6.75f, 7.8f, 6.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(15.267f, 6.75f)
                horizontalLineTo(16.2f)
                curveTo(16.817f, 6.75f, 17.316f, 7.25f, 17.316f, 7.866f)
                verticalLineTo(9.733f)
                curveTo(17.316f, 10.866f, 16.399f, 11.783f, 15.267f, 11.783f)
                curveTo(14.134f, 11.783f, 13.217f, 10.866f, 13.217f, 9.733f)
                verticalLineTo(8.8f)
                curveTo(13.217f, 7.668f, 14.134f, 6.75f, 15.267f, 6.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(5f, 6.933f)
                verticalLineTo(18.133f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(19f, 6.933f)
                verticalLineTo(18.133f)
            }
        }.build()

        return _Earphone!!
    }

@Suppress("ObjectPropertyName")
private var _Earphone: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun EarphonePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Earphone, contentDescription = null)
    }
}
