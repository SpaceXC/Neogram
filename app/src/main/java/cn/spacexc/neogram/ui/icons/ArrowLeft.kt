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

val NeogramIcons.ArrowLeft: ImageVector
    get() {
        if (_ArrowLeft != null) {
            return _ArrowLeft!!
        }
        _ArrowLeft = ImageVector.Builder(
            name = "ArrowLeft",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(14.581f, 18.364f)
                lineTo(8.359f, 12.141f)
                curveTo(8.066f, 11.849f, 8.066f, 11.374f, 8.359f, 11.081f)
                lineTo(14.581f, 4.859f)
                curveTo(14.874f, 4.566f, 15.349f, 4.566f, 15.641f, 4.859f)
                curveTo(15.934f, 5.151f, 15.934f, 5.626f, 15.641f, 5.919f)
                lineTo(9.95f, 11.611f)
                lineTo(15.641f, 17.303f)
                curveTo(15.934f, 17.596f, 15.934f, 18.071f, 15.641f, 18.364f)
                curveTo(15.349f, 18.657f, 14.874f, 18.657f, 14.581f, 18.364f)
                close()
            }
        }.build()

        return _ArrowLeft!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowLeft: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ArrowLeftPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ArrowLeft, contentDescription = null)
    }
}
