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

val NeogramIcons.Windows: ImageVector
    get() {
        if (_Windows != null) {
            return _Windows!!
        }
        _Windows = ImageVector.Builder(
            name = "Windows",
            defaultWidth = 19.dp,
            defaultHeight = 19.dp,
            viewportWidth = 19f,
            viewportHeight = 19f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(9.125f, 15.359f)
                horizontalLineTo(5.224f)
                curveTo(5.008f, 15.359f, 4.826f, 15.358f, 4.678f, 15.346f)
                curveTo(4.527f, 15.334f, 4.383f, 15.307f, 4.247f, 15.237f)
                curveTo(4.038f, 15.131f, 3.869f, 14.962f, 3.762f, 14.754f)
                curveTo(3.693f, 14.617f, 3.666f, 14.472f, 3.654f, 14.321f)
                curveTo(3.642f, 14.173f, 3.641f, 13.991f, 3.641f, 13.775f)
                verticalLineTo(9.876f)
                horizontalLineTo(9.125f)
                verticalLineTo(15.359f)
                close()
                moveTo(15.359f, 13.775f)
                curveTo(15.359f, 13.991f, 15.358f, 14.173f, 15.346f, 14.321f)
                curveTo(15.334f, 14.472f, 15.307f, 14.617f, 15.237f, 14.754f)
                curveTo(15.131f, 14.962f, 14.962f, 15.131f, 14.754f, 15.237f)
                curveTo(14.617f, 15.307f, 14.472f, 15.334f, 14.321f, 15.346f)
                curveTo(14.173f, 15.358f, 13.991f, 15.359f, 13.775f, 15.359f)
                horizontalLineTo(9.876f)
                verticalLineTo(9.876f)
                horizontalLineTo(15.359f)
                verticalLineTo(13.775f)
                close()
                moveTo(9.125f, 9.125f)
                horizontalLineTo(3.641f)
                verticalLineTo(5.224f)
                curveTo(3.641f, 5.008f, 3.642f, 4.826f, 3.654f, 4.678f)
                curveTo(3.666f, 4.527f, 3.693f, 4.383f, 3.762f, 4.247f)
                curveTo(3.869f, 4.038f, 4.038f, 3.869f, 4.247f, 3.762f)
                curveTo(4.383f, 3.693f, 4.527f, 3.666f, 4.678f, 3.654f)
                curveTo(4.826f, 3.642f, 5.008f, 3.641f, 5.224f, 3.641f)
                horizontalLineTo(9.125f)
                verticalLineTo(9.125f)
                close()
                moveTo(13.775f, 3.641f)
                curveTo(13.991f, 3.641f, 14.173f, 3.642f, 14.321f, 3.654f)
                curveTo(14.472f, 3.666f, 14.617f, 3.693f, 14.754f, 3.762f)
                curveTo(14.962f, 3.869f, 15.131f, 4.038f, 15.237f, 4.247f)
                curveTo(15.307f, 4.383f, 15.334f, 4.527f, 15.346f, 4.678f)
                curveTo(15.358f, 4.826f, 15.359f, 5.008f, 15.359f, 5.224f)
                verticalLineTo(9.125f)
                horizontalLineTo(9.876f)
                verticalLineTo(3.641f)
                horizontalLineTo(13.775f)
                close()
            }
        }.build()

        return _Windows!!
    }

@Suppress("ObjectPropertyName")
private var _Windows: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun WindowsPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Windows, contentDescription = null)
    }
}
