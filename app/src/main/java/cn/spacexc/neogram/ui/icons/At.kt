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

val NeogramIcons.At: ImageVector
    get() {
        if (_At != null) {
            return _At!!
        }
        _At = ImageVector.Builder(
            name = "At",
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
                moveTo(18f, 17f)
                curveTo(16.5f, 19f, 14.296f, 18.898f, 12f, 19f)
                verticalLineTo(19f)
                curveTo(8.134f, 19f, 5f, 15.866f, 5f, 12f)
                verticalLineTo(12f)
                curveTo(5f, 8.134f, 8.134f, 5f, 12f, 5f)
                horizontalLineTo(12.5f)
                horizontalLineTo(12.818f)
                curveTo(15.68f, 5f, 18f, 7.32f, 18f, 10.182f)
                verticalLineTo(10.5f)
                curveTo(18f, 11.605f, 17.105f, 12.5f, 16f, 12.5f)
                horizontalLineTo(15.75f)
                curveTo(14.783f, 12.5f, 14f, 11.717f, 14f, 10.75f)
                verticalLineTo(10.75f)
                curveTo(14f, 9.783f, 13.217f, 9f, 12.25f, 9f)
                horizontalLineTo(11.75f)
                curveTo(10.507f, 9f, 9.5f, 10.007f, 9.5f, 11.25f)
                verticalLineTo(11.75f)
                curveTo(9.5f, 13.269f, 10.731f, 14.5f, 12.25f, 14.5f)
                horizontalLineTo(12.5f)
                verticalLineTo(14.5f)
                curveTo(13.328f, 14.5f, 14f, 15.172f, 14f, 16f)
                verticalLineTo(16f)
            }
        }.build()

        return _At!!
    }

@Suppress("ObjectPropertyName")
private var _At: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AtPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.At, contentDescription = null)
    }
}
