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

val NeogramIcons.Play: ImageVector
    get() {
        if (_Play != null) {
            return _Play!!
        }
        _Play = ImageVector.Builder(
            name = "Play",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(19.269f, 10.748f)
                curveTo(19.599f, 11.612f, 19.576f, 12.574f, 19.197f, 13.424f)
                curveTo(18.897f, 14.098f, 18.361f, 14.553f, 17.871f, 14.899f)
                curveTo(17.375f, 15.25f, 16.712f, 15.63f, 15.95f, 16.07f)
                lineTo(12.05f, 18.322f)
                curveTo(11.288f, 18.762f, 10.627f, 19.145f, 10.075f, 19.399f)
                curveTo(9.53f, 19.651f, 8.868f, 19.888f, 8.134f, 19.81f)
                curveTo(7.147f, 19.707f, 6.251f, 19.19f, 5.668f, 18.388f)
                curveTo(5.234f, 17.791f, 5.109f, 17.098f, 5.054f, 16.501f)
                curveTo(4.998f, 15.896f, 5f, 15.132f, 5f, 14.252f)
                verticalLineTo(9.748f)
                curveTo(5f, 8.868f, 4.998f, 8.104f, 5.054f, 7.499f)
                curveTo(5.109f, 6.902f, 5.234f, 6.209f, 5.668f, 5.612f)
                curveTo(6.251f, 4.81f, 7.147f, 4.293f, 8.134f, 4.189f)
                curveTo(8.868f, 4.112f, 9.53f, 4.349f, 10.075f, 4.601f)
                curveTo(10.627f, 4.855f, 11.288f, 5.238f, 12.05f, 5.678f)
                lineTo(15.95f, 7.93f)
                curveTo(16.712f, 8.37f, 17.375f, 8.75f, 17.871f, 9.101f)
                curveTo(18.361f, 9.447f, 18.897f, 9.902f, 19.197f, 10.576f)
                lineTo(19.269f, 10.748f)
                close()
                moveTo(17.827f, 12.814f)
                curveTo(18.058f, 12.296f, 18.058f, 11.704f, 17.827f, 11.186f)
                curveTo(17.563f, 10.593f, 16.775f, 10.138f, 15.2f, 9.229f)
                lineTo(11.3f, 6.978f)
                lineTo(10.256f, 6.381f)
                curveTo(9.34f, 5.873f, 8.776f, 5.63f, 8.291f, 5.681f)
                curveTo(7.727f, 5.74f, 7.215f, 6.036f, 6.882f, 6.494f)
                curveTo(6.5f, 7.02f, 6.5f, 7.929f, 6.5f, 9.748f)
                verticalLineTo(14.252f)
                lineTo(6.506f, 15.453f)
                curveTo(6.522f, 16.414f, 6.584f, 17.007f, 6.814f, 17.402f)
                lineTo(6.882f, 17.506f)
                curveTo(7.173f, 17.907f, 7.602f, 18.184f, 8.082f, 18.286f)
                lineTo(8.291f, 18.319f)
                curveTo(8.776f, 18.37f, 9.34f, 18.127f, 10.256f, 17.619f)
                lineTo(11.3f, 17.022f)
                lineTo(15.2f, 14.771f)
                curveTo(16.677f, 13.919f, 17.462f, 13.465f, 17.771f, 12.923f)
                lineTo(17.827f, 12.814f)
                close()
            }
        }.build()

        return _Play!!
    }

@Suppress("ObjectPropertyName")
private var _Play: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun PlayPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Play, contentDescription = null)
    }
}
