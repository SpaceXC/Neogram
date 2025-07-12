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

val NeogramIcons.Pause: ImageVector
    get() {
        if (_Pause != null) {
            return _Pause!!
        }
        _Pause = ImageVector.Builder(
            name = "Pause",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(8.5f, 5.25f)
                curveTo(9.743f, 5.25f, 10.75f, 6.257f, 10.75f, 7.5f)
                verticalLineTo(16.5f)
                curveTo(10.75f, 17.743f, 9.743f, 18.75f, 8.5f, 18.75f)
                curveTo(7.257f, 18.75f, 6.25f, 17.743f, 6.25f, 16.5f)
                verticalLineTo(7.5f)
                curveTo(6.25f, 6.257f, 7.257f, 5.25f, 8.5f, 5.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(16.5f, 5.25f)
                curveTo(17.743f, 5.25f, 18.75f, 6.257f, 18.75f, 7.5f)
                verticalLineTo(16.5f)
                curveTo(18.75f, 17.743f, 17.743f, 18.75f, 16.5f, 18.75f)
                curveTo(15.257f, 18.75f, 14.25f, 17.743f, 14.25f, 16.5f)
                verticalLineTo(7.5f)
                curveTo(14.25f, 6.257f, 15.257f, 5.25f, 16.5f, 5.25f)
                close()
            }
        }.build()

        return _Pause!!
    }

@Suppress("ObjectPropertyName")
private var _Pause: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun PausePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Pause, contentDescription = null)
    }
}
