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

val NeogramIcons.ArrowLink: ImageVector
    get() {
        if (_ArrowLink != null) {
            return _ArrowLink!!
        }
        _ArrowLink = ImageVector.Builder(
            name = "ArrowLink",
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
                moveTo(8.133f, 7.461f)
                lineTo(14.933f, 7.479f)
                curveTo(16.037f, 7.482f, 16.93f, 8.379f, 16.927f, 9.484f)
                lineTo(16.91f, 16.283f)
            }
        }.build()

        return _ArrowLink!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowLink: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ArrowLinkPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ArrowLink, contentDescription = null)
    }
}
