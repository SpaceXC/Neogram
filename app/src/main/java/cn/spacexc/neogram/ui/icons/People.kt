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

val NeogramIcons.People: ImageVector
    get() {
        if (_People != null) {
            return _People!!
        }
        _People = ImageVector.Builder(
            name = "People",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6.893f, 7.257f)
                curveTo(6.893f, 5.458f, 8.354f, 4f, 10.157f, 4f)
                curveTo(11.96f, 4f, 13.421f, 5.458f, 13.421f, 7.257f)
                curveTo(13.421f, 9.055f, 11.96f, 10.513f, 10.157f, 10.513f)
                curveTo(8.354f, 10.513f, 6.893f, 9.055f, 6.893f, 7.257f)
                close()
                moveTo(10.157f, 5.496f)
                curveTo(9.183f, 5.496f, 8.393f, 6.284f, 8.393f, 7.257f)
                curveTo(8.393f, 8.229f, 9.183f, 9.017f, 10.157f, 9.017f)
                curveTo(11.132f, 9.017f, 11.921f, 8.229f, 11.921f, 7.257f)
                curveTo(11.921f, 6.284f, 11.132f, 5.496f, 10.157f, 5.496f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(7.774f, 12.088f)
                horizontalLineTo(12.226f)
                curveTo(14.863f, 12.088f, 17f, 14.22f, 17f, 16.85f)
                curveTo(17f, 18.038f, 16.035f, 19f, 14.845f, 19f)
                horizontalLineTo(5.155f)
                curveTo(3.965f, 19f, 3f, 18.038f, 3f, 16.85f)
                curveTo(3f, 14.22f, 5.137f, 12.088f, 7.774f, 12.088f)
                close()
                moveTo(7.774f, 13.584f)
                curveTo(5.966f, 13.584f, 4.5f, 15.047f, 4.5f, 16.85f)
                curveTo(4.5f, 17.211f, 4.793f, 17.504f, 5.155f, 17.504f)
                horizontalLineTo(14.845f)
                curveTo(15.207f, 17.504f, 15.5f, 17.211f, 15.5f, 16.85f)
                curveTo(15.5f, 15.047f, 14.034f, 13.584f, 12.226f, 13.584f)
                horizontalLineTo(7.774f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(13.027f, 10.077f)
                curveTo(13.333f, 10.18f, 13.66f, 10.235f, 14f, 10.235f)
                curveTo(15.672f, 10.235f, 17.027f, 8.895f, 17.027f, 7.242f)
                curveTo(17.027f, 5.589f, 15.672f, 4.249f, 14f, 4.249f)
                curveTo(13.67f, 4.249f, 13.352f, 4.302f, 13.055f, 4.398f)
                curveTo(13.948f, 5.028f, 14.529f, 6.061f, 14.529f, 7.228f)
                curveTo(14.529f, 8.407f, 13.936f, 9.449f, 13.027f, 10.077f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(13.027f, 10.077f)
                curveTo(13.333f, 10.18f, 13.66f, 10.235f, 14f, 10.235f)
                curveTo(15.672f, 10.235f, 17.027f, 8.895f, 17.027f, 7.242f)
                curveTo(17.027f, 5.589f, 15.672f, 4.249f, 14f, 4.249f)
                curveTo(13.67f, 4.249f, 13.352f, 4.302f, 13.055f, 4.398f)
                curveTo(13.948f, 5.028f, 14.529f, 6.061f, 14.529f, 7.228f)
                curveTo(14.529f, 8.407f, 13.936f, 9.449f, 13.027f, 10.077f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(16.071f, 18.869f)
                horizontalLineTo(18.845f)
                curveTo(20.035f, 18.869f, 21f, 17.907f, 21f, 16.72f)
                curveTo(21f, 14.09f, 18.863f, 11.957f, 16.226f, 11.957f)
                horizontalLineTo(13.686f)
                curveTo(16.323f, 11.957f, 18.226f, 14.09f, 18.226f, 16.72f)
                curveTo(18.226f, 17.907f, 17.261f, 18.869f, 16.071f, 18.869f)
                close()
            }
        }.build()

        return _People!!
    }

@Suppress("ObjectPropertyName")
private var _People: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun PeoplePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.People, contentDescription = null)
    }
}
