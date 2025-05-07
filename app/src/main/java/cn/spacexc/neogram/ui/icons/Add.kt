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

val NeogramIcons.Add: ImageVector
    get() {
        if (_Add != null) {
            return _Add!!
        }
        _Add = ImageVector.Builder(
            name = "Add",
            defaultWidth = 12.dp,
            defaultHeight = 12.dp,
            viewportWidth = 12f,
            viewportHeight = 12f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(6f, 11.667f)
                curveTo(5.858f, 11.667f, 5.739f, 11.619f, 5.644f, 11.523f)
                curveTo(5.548f, 11.427f, 5.5f, 11.308f, 5.5f, 11.167f)
                verticalLineTo(6.5f)
                horizontalLineTo(0.833f)
                curveTo(0.692f, 6.5f, 0.573f, 6.452f, 0.477f, 6.356f)
                curveTo(0.381f, 6.26f, 0.333f, 6.141f, 0.333f, 6f)
                curveTo(0.333f, 5.858f, 0.381f, 5.739f, 0.477f, 5.644f)
                curveTo(0.573f, 5.548f, 0.692f, 5.5f, 0.833f, 5.5f)
                horizontalLineTo(5.5f)
                verticalLineTo(0.833f)
                curveTo(5.5f, 0.692f, 5.548f, 0.573f, 5.644f, 0.477f)
                curveTo(5.74f, 0.381f, 5.858f, 0.333f, 6f, 0.333f)
                curveTo(6.142f, 0.333f, 6.261f, 0.381f, 6.356f, 0.477f)
                curveTo(6.452f, 0.573f, 6.5f, 0.692f, 6.5f, 0.833f)
                verticalLineTo(5.5f)
                horizontalLineTo(11.167f)
                curveTo(11.308f, 5.5f, 11.427f, 5.548f, 11.523f, 5.644f)
                curveTo(11.619f, 5.74f, 11.667f, 5.859f, 11.667f, 6f)
                curveTo(11.667f, 6.142f, 11.619f, 6.261f, 11.523f, 6.356f)
                curveTo(11.427f, 6.452f, 11.308f, 6.5f, 11.167f, 6.5f)
                horizontalLineTo(6.5f)
                verticalLineTo(11.167f)
                curveTo(6.5f, 11.308f, 6.452f, 11.427f, 6.356f, 11.523f)
                curveTo(6.26f, 11.619f, 6.141f, 11.667f, 6f, 11.667f)
                close()
            }
        }.build()

        return _Add!!
    }

@Suppress("ObjectPropertyName")
private var _Add: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AddPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Add, contentDescription = null)
    }
}
