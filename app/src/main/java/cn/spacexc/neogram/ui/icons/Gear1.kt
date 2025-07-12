package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Gear1: ImageVector
    get() {
        if (_Gear1 != null) {
            return _Gear1!!
        }
        _Gear1 = ImageVector.Builder(
            name = "Gear1",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12.75f, 5.165f)
                lineTo(17.544f, 7.933f)
                curveTo(18.008f, 8.201f, 18.294f, 8.696f, 18.294f, 9.232f)
                verticalLineTo(14.768f)
                curveTo(18.294f, 15.304f, 18.008f, 15.799f, 17.544f, 16.067f)
                lineTo(12.75f, 18.835f)
                curveTo(12.286f, 19.103f, 11.714f, 19.103f, 11.25f, 18.835f)
                lineTo(6.456f, 16.067f)
                curveTo(5.992f, 15.799f, 5.706f, 15.304f, 5.706f, 14.768f)
                verticalLineTo(9.232f)
                curveTo(5.706f, 8.696f, 5.992f, 8.201f, 6.456f, 7.933f)
                lineTo(11.25f, 5.165f)
                curveTo(11.714f, 4.897f, 12.286f, 4.897f, 12.75f, 5.165f)
                close()
                moveTo(10.5f, 3.866f)
                curveTo(11.428f, 3.33f, 12.572f, 3.33f, 13.5f, 3.866f)
                lineTo(18.294f, 6.634f)
                curveTo(19.222f, 7.17f, 19.794f, 8.16f, 19.794f, 9.232f)
                verticalLineTo(14.768f)
                curveTo(19.794f, 15.84f, 19.222f, 16.83f, 18.294f, 17.366f)
                lineTo(13.5f, 20.134f)
                curveTo(12.572f, 20.67f, 11.428f, 20.67f, 10.5f, 20.134f)
                lineTo(5.706f, 17.366f)
                curveTo(4.778f, 16.83f, 4.206f, 15.84f, 4.206f, 14.768f)
                verticalLineTo(9.232f)
                curveTo(4.206f, 8.16f, 4.778f, 7.17f, 5.706f, 6.634f)
                lineTo(10.5f, 3.866f)
                close()
                moveTo(12f, 14.5f)
                curveTo(10.619f, 14.5f, 9.5f, 13.381f, 9.5f, 12f)
                curveTo(9.5f, 10.619f, 10.619f, 9.5f, 12f, 9.5f)
                curveTo(13.381f, 9.5f, 14.5f, 10.619f, 14.5f, 12f)
                curveTo(14.5f, 13.381f, 13.381f, 14.5f, 12f, 14.5f)
                close()
                moveTo(8f, 12f)
                curveTo(8f, 9.791f, 9.791f, 8f, 12f, 8f)
                curveTo(14.209f, 8f, 16f, 9.791f, 16f, 12f)
                curveTo(16f, 14.209f, 14.209f, 16f, 12f, 16f)
                curveTo(9.791f, 16f, 8f, 14.209f, 8f, 12f)
                close()
            }
        }.build()

        return _Gear1!!
    }

@Suppress("ObjectPropertyName")
private var _Gear1: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun Gear1Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Gear1, contentDescription = null)
    }
}
