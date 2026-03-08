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

val NeogramIcons.ArrowUp: ImageVector
    get() {
        if (_ArrowUp != null) {
            return _ArrowUp!!
        }
        _ArrowUp = ImageVector.Builder(
            name = "ArrowUp",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.247f, 14.192f)
                lineTo(11.47f, 7.97f)
                curveTo(11.763f, 7.677f, 12.237f, 7.677f, 12.53f, 7.97f)
                lineTo(18.753f, 14.192f)
                curveTo(19.045f, 14.485f, 19.045f, 14.96f, 18.753f, 15.253f)
                curveTo(18.46f, 15.545f, 17.985f, 15.545f, 17.692f, 15.253f)
                lineTo(12f, 9.561f)
                lineTo(6.308f, 15.253f)
                curveTo(6.015f, 15.545f, 5.54f, 15.545f, 5.247f, 15.253f)
                curveTo(4.955f, 14.96f, 4.955f, 14.485f, 5.247f, 14.192f)
                close()
            }
        }.build()

        return _ArrowUp!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowUp: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ArrowUpPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ArrowUp, contentDescription = null)
    }
}
