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

val NeogramIcons.Back: ImageVector
    get() {
        if (_Back != null) {
            return _Back!!
        }
        _Back = ImageVector.Builder(
            name = "Back",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(5.47f, 10.305f)
                curveTo(5.177f, 10.598f, 5.177f, 11.073f, 5.47f, 11.366f)
                lineTo(10.305f, 16.201f)
                curveTo(10.598f, 16.494f, 11.073f, 16.494f, 11.366f, 16.201f)
                curveTo(11.659f, 15.909f, 11.659f, 15.434f, 11.366f, 15.141f)
                lineTo(7.655f, 11.43f)
                lineTo(14.063f, 11.43f)
                curveTo(14.736f, 11.43f, 15.2f, 11.43f, 15.561f, 11.457f)
                curveTo(15.914f, 11.483f, 16.11f, 11.531f, 16.254f, 11.596f)
                curveTo(16.671f, 11.784f, 17.004f, 12.118f, 17.192f, 12.534f)
                curveTo(17.258f, 12.678f, 17.306f, 12.874f, 17.332f, 13.228f)
                curveTo(17.358f, 13.589f, 17.359f, 14.053f, 17.359f, 14.725f)
                verticalLineTo(17.329f)
                curveTo(17.359f, 17.743f, 17.695f, 18.079f, 18.109f, 18.079f)
                curveTo(18.523f, 18.079f, 18.859f, 17.743f, 18.859f, 17.329f)
                verticalLineTo(14.725f)
                verticalLineTo(14.697f)
                verticalLineTo(14.697f)
                verticalLineTo(14.697f)
                curveTo(18.859f, 14.059f, 18.859f, 13.54f, 18.828f, 13.118f)
                curveTo(18.795f, 12.681f, 18.727f, 12.288f, 18.56f, 11.917f)
                curveTo(18.221f, 11.167f, 17.621f, 10.567f, 16.872f, 10.229f)
                curveTo(16.5f, 10.061f, 16.107f, 9.993f, 15.671f, 9.961f)
                curveTo(15.248f, 9.93f, 14.729f, 9.93f, 14.092f, 9.93f)
                horizontalLineTo(14.063f)
                lineTo(7.967f, 9.93f)
                lineTo(11.366f, 6.53f)
                curveTo(11.659f, 6.237f, 11.659f, 5.763f, 11.366f, 5.47f)
                curveTo(11.073f, 5.177f, 10.598f, 5.177f, 10.305f, 5.47f)
                lineTo(5.47f, 10.305f)
                close()
            }
        }.build()

        return _Back!!
    }

@Suppress("ObjectPropertyName")
private var _Back: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun BackPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Back, contentDescription = null)
    }
}
