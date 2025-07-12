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

val NeogramIcons.Channel: ImageVector
    get() {
        if (_Channel != null) {
            return _Channel!!
        }
        _Channel = ImageVector.Builder(
            name = "Channel",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(17.207f, 5.866f)
                lineTo(5.607f, 8.476f)
                curveTo(4.375f, 8.753f, 3.5f, 9.847f, 3.5f, 11.11f)
                verticalLineTo(11.89f)
                curveTo(3.5f, 13.153f, 4.375f, 14.247f, 5.607f, 14.524f)
                lineTo(5.778f, 14.563f)
                curveTo(5.778f, 14.744f, 5.794f, 14.925f, 5.825f, 15.105f)
                curveTo(5.9f, 15.532f, 6.062f, 15.94f, 6.299f, 16.307f)
                curveTo(6.536f, 16.673f, 6.844f, 16.991f, 7.203f, 17.243f)
                curveTo(7.562f, 17.496f, 7.967f, 17.68f, 8.395f, 17.785f)
                curveTo(8.823f, 17.89f, 9.267f, 17.915f, 9.702f, 17.858f)
                curveTo(10.138f, 17.801f, 10.558f, 17.662f, 10.938f, 17.448f)
                curveTo(11.318f, 17.233f, 11.651f, 16.946f, 11.915f, 16.603f)
                curveTo(12.054f, 16.422f, 12.172f, 16.228f, 12.268f, 16.023f)
                lineTo(17.207f, 17.134f)
                curveTo(18.895f, 17.514f, 20.5f, 16.23f, 20.5f, 14.5f)
                verticalLineTo(8.5f)
                curveTo(20.5f, 6.77f, 18.895f, 5.486f, 17.207f, 5.866f)
                close()
                moveTo(10.734f, 15.678f)
                lineTo(7.315f, 14.908f)
                curveTo(7.358f, 15.111f, 7.44f, 15.309f, 7.558f, 15.492f)
                curveTo(7.689f, 15.693f, 7.861f, 15.872f, 8.066f, 16.016f)
                curveTo(8.271f, 16.161f, 8.505f, 16.267f, 8.753f, 16.328f)
                curveTo(9.002f, 16.389f, 9.258f, 16.403f, 9.507f, 16.371f)
                curveTo(9.756f, 16.338f, 9.991f, 16.259f, 10.2f, 16.141f)
                curveTo(10.409f, 16.024f, 10.587f, 15.869f, 10.726f, 15.688f)
                curveTo(10.729f, 15.685f, 10.731f, 15.681f, 10.734f, 15.678f)
                close()
                moveTo(5f, 11.11f)
                curveTo(5f, 10.549f, 5.389f, 10.063f, 5.937f, 9.939f)
                lineTo(17.537f, 7.329f)
                curveTo(18.287f, 7.16f, 19f, 7.731f, 19f, 8.5f)
                verticalLineTo(14.5f)
                curveTo(19f, 15.269f, 18.287f, 15.84f, 17.537f, 15.671f)
                lineTo(5.937f, 13.061f)
                curveTo(5.389f, 12.938f, 5f, 12.451f, 5f, 11.89f)
                verticalLineTo(11.11f)
                close()
            }
        }.build()

        return _Channel!!
    }

@Suppress("ObjectPropertyName")
private var _Channel: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ChannelPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Channel, contentDescription = null)
    }
}
