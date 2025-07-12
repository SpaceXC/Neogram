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

val NeogramIcons.File: ImageVector
    get() {
        if (_File != null) {
            return _File!!
        }
        _File = ImageVector.Builder(
            name = "File",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(13.75f, 4.25f)
                curveTo(14.852f, 4.25f, 15.589f, 4.24f, 16.205f, 4.449f)
                curveTo(17.309f, 4.824f, 18.176f, 5.691f, 18.551f, 6.795f)
                curveTo(18.76f, 7.411f, 18.75f, 8.148f, 18.75f, 9.25f)
                verticalLineTo(14.2f)
                curveTo(18.75f, 15.028f, 18.751f, 15.694f, 18.707f, 16.231f)
                curveTo(18.662f, 16.778f, 18.567f, 17.258f, 18.341f, 17.702f)
                curveTo(17.981f, 18.408f, 17.408f, 18.981f, 16.702f, 19.341f)
                curveTo(16.258f, 19.567f, 15.778f, 19.662f, 15.231f, 19.707f)
                curveTo(14.694f, 19.751f, 14.028f, 19.75f, 13.2f, 19.75f)
                horizontalLineTo(10.75f)
                curveTo(9.946f, 19.75f, 9.299f, 19.75f, 8.775f, 19.709f)
                curveTo(8.244f, 19.667f, 7.776f, 19.577f, 7.342f, 19.363f)
                curveTo(6.601f, 18.998f, 6.002f, 18.399f, 5.637f, 17.658f)
                curveTo(5.423f, 17.224f, 5.333f, 16.756f, 5.291f, 16.225f)
                curveTo(5.249f, 15.701f, 5.25f, 15.054f, 5.25f, 14.25f)
                verticalLineTo(11.25f)
                curveTo(5.25f, 9.869f, 6.369f, 8.75f, 7.75f, 8.75f)
                curveTo(8.302f, 8.75f, 8.75f, 8.302f, 8.75f, 7.75f)
                verticalLineTo(7.25f)
                curveTo(8.75f, 5.593f, 10.093f, 4.25f, 11.75f, 4.25f)
                horizontalLineTo(13.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeAlpha = 0.48f,
                strokeLineWidth = 1.5f
            ) {
                moveTo(10f, 5f)
                verticalLineTo(5f)
                curveTo(8.602f, 5f, 7.903f, 5f, 7.352f, 5.228f)
                curveTo(6.617f, 5.533f, 6.033f, 6.117f, 5.728f, 6.852f)
                curveTo(5.5f, 7.403f, 5.5f, 8.102f, 5.5f, 9.5f)
                verticalLineTo(10f)
            }
        }.build()

        return _File!!
    }

@Suppress("ObjectPropertyName")
private var _File: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun FilePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.File, contentDescription = null)
    }
}
