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

val NeogramIcons.ArrowRight: ImageVector
    get() {
        if (_ArrowRight != null) {
            return _ArrowRight!!
        }
        _ArrowRight = ImageVector.Builder(
            name = "ArrowRight",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.419f, 18.364f)
                lineTo(15.641f, 12.141f)
                curveTo(15.934f, 11.849f, 15.934f, 11.374f, 15.641f, 11.081f)
                lineTo(9.419f, 4.859f)
                curveTo(9.126f, 4.566f, 8.651f, 4.566f, 8.359f, 4.859f)
                curveTo(8.066f, 5.151f, 8.066f, 5.626f, 8.359f, 5.919f)
                lineTo(14.05f, 11.611f)
                lineTo(8.359f, 17.303f)
                curveTo(8.066f, 17.596f, 8.066f, 18.071f, 8.359f, 18.364f)
                curveTo(8.651f, 18.657f, 9.126f, 18.657f, 9.419f, 18.364f)
                close()
            }
        }.build()

        return _ArrowRight!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowRight: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ArrowRightPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ArrowRight, contentDescription = null)
    }
}
