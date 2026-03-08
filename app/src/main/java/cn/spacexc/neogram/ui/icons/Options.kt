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

val NeogramIcons.Options: ImageVector
    get() {
        if (_Options != null) {
            return _Options!!
        }
        _Options = ImageVector.Builder(
            name = "Options",
            defaultWidth = 14.dp,
            defaultHeight = 11.dp,
            viewportWidth = 14f,
            viewportHeight = 11f
        ).apply {
            path(fill = SolidColor(Color.White)) {
                moveTo(0.732f, 11f)
                curveTo(0.328f, 11f, 0f, 10.672f, 0f, 10.268f)
                curveTo(0f, 9.864f, 0.328f, 9.536f, 0.732f, 9.536f)
                horizontalLineTo(13.268f)
                curveTo(13.672f, 9.536f, 14f, 9.864f, 14f, 10.268f)
                curveTo(14f, 10.672f, 13.672f, 11f, 13.268f, 11f)
                horizontalLineTo(0.732f)
                close()
                moveTo(0.732f, 6.232f)
                curveTo(0.328f, 6.232f, 0f, 5.904f, 0f, 5.5f)
                curveTo(0f, 5.096f, 0.328f, 4.768f, 0.732f, 4.768f)
                horizontalLineTo(13.268f)
                curveTo(13.672f, 4.768f, 14f, 5.096f, 14f, 5.5f)
                curveTo(14f, 5.904f, 13.672f, 6.232f, 13.268f, 6.232f)
                horizontalLineTo(0.732f)
                close()
                moveTo(0.732f, 1.464f)
                curveTo(0.328f, 1.464f, 0f, 1.136f, 0f, 0.732f)
                curveTo(0f, 0.328f, 0.328f, 0f, 0.732f, 0f)
                horizontalLineTo(13.268f)
                curveTo(13.672f, 0f, 14f, 0.328f, 14f, 0.732f)
                curveTo(14f, 1.136f, 13.672f, 1.464f, 13.268f, 1.464f)
                horizontalLineTo(0.732f)
                close()
            }
        }.build()

        return _Options!!
    }

@Suppress("ObjectPropertyName")
private var _Options: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun OptionsPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Options, contentDescription = null)
    }
}
