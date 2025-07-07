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

val NeogramIcons.Edit: ImageVector
    get() {
        if (_Edit != null) {
            return _Edit!!
        }
        _Edit = ImageVector.Builder(
            name = "Edit",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(17.646f, 5.115f)
                curveTo(18.057f, 4.704f, 18.723f, 4.704f, 19.133f, 5.115f)
                curveTo(19.544f, 5.526f, 19.544f, 6.191f, 19.133f, 6.602f)
                lineTo(14.708f, 11.027f)
                curveTo(13.717f, 12.019f, 12.469f, 12.715f, 11.105f, 13.038f)
                curveTo(11.057f, 13.049f, 11.015f, 13.001f, 11.032f, 12.954f)
                curveTo(11.471f, 11.716f, 12.187f, 10.575f, 13.116f, 9.646f)
                lineTo(17.646f, 5.115f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0.2f
            ) {
                moveTo(17.646f, 5.115f)
                curveTo(18.057f, 4.704f, 18.723f, 4.704f, 19.133f, 5.115f)
                curveTo(19.544f, 5.526f, 19.544f, 6.191f, 19.133f, 6.602f)
                lineTo(14.708f, 11.027f)
                curveTo(13.717f, 12.019f, 12.469f, 12.715f, 11.105f, 13.038f)
                curveTo(11.057f, 13.049f, 11.015f, 13.001f, 11.032f, 12.954f)
                curveTo(11.471f, 11.716f, 12.187f, 10.575f, 13.116f, 9.646f)
                lineTo(17.646f, 5.115f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.894f, 4f)
                lineTo(15.31f, 4f)
                curveTo(15.767f, 4f, 16.138f, 4.371f, 16.138f, 4.828f)
                curveTo(16.138f, 5.285f, 15.767f, 5.655f, 15.31f, 5.655f)
                horizontalLineTo(9.926f)
                curveTo(9.08f, 5.655f, 8.49f, 5.656f, 8.03f, 5.69f)
                curveTo(7.578f, 5.723f, 7.316f, 5.785f, 7.116f, 5.875f)
                curveTo(6.565f, 6.124f, 6.124f, 6.565f, 5.875f, 7.116f)
                curveTo(5.785f, 7.316f, 5.723f, 7.578f, 5.69f, 8.03f)
                curveTo(5.656f, 8.49f, 5.655f, 9.08f, 5.655f, 9.926f)
                verticalLineTo(14.075f)
                curveTo(5.655f, 14.92f, 5.656f, 15.51f, 5.69f, 15.97f)
                curveTo(5.723f, 16.422f, 5.785f, 16.684f, 5.875f, 16.884f)
                curveTo(6.124f, 17.435f, 6.565f, 17.876f, 7.116f, 18.125f)
                curveTo(7.316f, 18.215f, 7.578f, 18.277f, 8.03f, 18.31f)
                curveTo(8.49f, 18.344f, 9.08f, 18.345f, 9.926f, 18.345f)
                horizontalLineTo(14.075f)
                curveTo(14.92f, 18.345f, 15.51f, 18.344f, 15.97f, 18.31f)
                curveTo(16.422f, 18.277f, 16.684f, 18.215f, 16.884f, 18.125f)
                curveTo(17.435f, 17.876f, 17.876f, 17.435f, 18.125f, 16.884f)
                curveTo(18.215f, 16.684f, 18.277f, 16.422f, 18.31f, 15.97f)
                curveTo(18.344f, 15.51f, 18.345f, 14.92f, 18.345f, 14.075f)
                verticalLineTo(9.241f)
                curveTo(18.345f, 8.784f, 18.715f, 8.414f, 19.172f, 8.414f)
                curveTo(19.629f, 8.414f, 20f, 8.784f, 20f, 9.241f)
                verticalLineTo(14.106f)
                curveTo(20f, 14.913f, 20f, 15.563f, 19.961f, 16.092f)
                curveTo(19.921f, 16.635f, 19.837f, 17.115f, 19.633f, 17.565f)
                curveTo(19.219f, 18.483f, 18.483f, 19.219f, 17.565f, 19.633f)
                curveTo(17.115f, 19.837f, 16.635f, 19.921f, 16.092f, 19.961f)
                curveTo(15.563f, 20f, 14.913f, 20f, 14.106f, 20f)
                horizontalLineTo(9.894f)
                curveTo(9.087f, 20f, 8.437f, 20f, 7.908f, 19.961f)
                curveTo(7.365f, 19.921f, 6.885f, 19.837f, 6.435f, 19.633f)
                curveTo(5.517f, 19.219f, 4.781f, 18.483f, 4.367f, 17.565f)
                curveTo(4.163f, 17.115f, 4.079f, 16.635f, 4.039f, 16.092f)
                curveTo(4f, 15.563f, 4f, 14.913f, 4f, 14.106f)
                verticalLineTo(9.894f)
                curveTo(4f, 9.087f, 4f, 8.437f, 4.039f, 7.908f)
                curveTo(4.079f, 7.365f, 4.163f, 6.885f, 4.367f, 6.435f)
                curveTo(4.781f, 5.517f, 5.517f, 4.781f, 6.435f, 4.367f)
                curveTo(6.885f, 4.163f, 7.365f, 4.079f, 7.908f, 4.039f)
                curveTo(8.437f, 4f, 9.087f, 4f, 9.894f, 4f)
                close()
            }
        }.build()

        return _Edit!!
    }

@Suppress("ObjectPropertyName")
private var _Edit: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun EditPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Edit, contentDescription = null)
    }
}
