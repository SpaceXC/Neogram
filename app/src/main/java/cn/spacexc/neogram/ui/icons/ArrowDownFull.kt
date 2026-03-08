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

val NeogramIcons.ArrowDownFull: ImageVector
    get() {
        if (_ArrowDownFull != null) {
            return _ArrowDownFull!!
        }
        _ArrowDownFull = ImageVector.Builder(
            name = "ArrowDownFull",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.247f, 9.03f)
                lineTo(11.47f, 15.253f)
                curveTo(11.763f, 15.545f, 12.237f, 15.545f, 12.53f, 15.253f)
                lineTo(18.753f, 9.03f)
                curveTo(19.045f, 8.737f, 19.045f, 8.263f, 18.753f, 7.97f)
                curveTo(18.46f, 7.677f, 17.985f, 7.677f, 17.692f, 7.97f)
                lineTo(12f, 13.662f)
                lineTo(6.308f, 7.97f)
                curveTo(6.015f, 7.677f, 5.54f, 7.677f, 5.247f, 7.97f)
                curveTo(4.955f, 8.263f, 4.955f, 8.737f, 5.247f, 9.03f)
                close()
            }
        }.build()

        return _ArrowDownFull!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowDownFull: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ArrowDownFullPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ArrowDownFull, contentDescription = null)
    }
}
