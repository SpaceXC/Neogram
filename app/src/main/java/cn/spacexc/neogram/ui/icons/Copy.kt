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

val NeogramIcons.Copy: ImageVector
    get() {
        if (_Copy != null) {
            return _Copy!!
        }
        _Copy = ImageVector.Builder(
            name = "Copy",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(18f, 13f)
                curveTo(18f, 12.186f, 18f, 11.728f, 17.932f, 11.371f)
                lineTo(17.897f, 11.224f)
                curveTo(17.637f, 10.253f, 16.911f, 9.481f, 15.968f, 9.16f)
                lineTo(15.776f, 9.103f)
                curveTo(15.395f, 9f, 14.93f, 9f, 14f, 9f)
                curveTo(13.07f, 9f, 12.605f, 9f, 12.224f, 9.103f)
                lineTo(12.032f, 9.16f)
                curveTo(11.089f, 9.481f, 10.363f, 10.253f, 10.102f, 11.224f)
                lineTo(10.068f, 11.371f)
                curveTo(10f, 11.728f, 10f, 12.186f, 10f, 13f)
                verticalLineTo(15f)
                curveTo(10f, 15.93f, 10f, 16.395f, 10.102f, 16.776f)
                curveTo(10.38f, 17.812f, 11.188f, 18.62f, 12.224f, 18.897f)
                curveTo(12.605f, 19f, 13.07f, 19f, 14f, 19f)
                curveTo(14.93f, 19f, 15.395f, 19f, 15.776f, 18.897f)
                curveTo(16.812f, 18.62f, 17.62f, 17.812f, 17.897f, 16.776f)
                curveTo(18f, 16.395f, 18f, 15.93f, 18f, 15f)
                verticalLineTo(13f)
                close()
                moveTo(19.5f, 15f)
                curveTo(19.5f, 15.833f, 19.513f, 16.545f, 19.347f, 17.165f)
                curveTo(18.931f, 18.718f, 17.718f, 19.931f, 16.165f, 20.347f)
                curveTo(15.545f, 20.513f, 14.833f, 20.5f, 14f, 20.5f)
                curveTo(13.167f, 20.5f, 12.455f, 20.513f, 11.835f, 20.347f)
                curveTo(10.282f, 19.931f, 9.069f, 18.718f, 8.653f, 17.165f)
                curveTo(8.487f, 16.545f, 8.5f, 15.833f, 8.5f, 15f)
                verticalLineTo(13f)
                curveTo(8.5f, 12.167f, 8.487f, 11.455f, 8.653f, 10.835f)
                curveTo(9.069f, 9.282f, 10.282f, 8.069f, 11.835f, 7.653f)
                curveTo(12.455f, 7.487f, 13.167f, 7.5f, 14f, 7.5f)
                curveTo(14.833f, 7.5f, 15.545f, 7.487f, 16.165f, 7.653f)
                curveTo(17.718f, 8.069f, 18.931f, 9.282f, 19.347f, 10.835f)
                curveTo(19.513f, 11.455f, 19.5f, 12.167f, 19.5f, 13f)
                verticalLineTo(15f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0.5f
            ) {
                moveTo(10.5f, 4f)
                curveTo(11.333f, 4f, 12.045f, 3.987f, 12.665f, 4.154f)
                curveTo(14.218f, 4.57f, 15.431f, 5.783f, 15.847f, 7.335f)
                curveTo(15.865f, 7.401f, 15.879f, 7.469f, 15.893f, 7.537f)
                curveTo(15.416f, 7.451f, 14.89f, 7.444f, 14.307f, 7.445f)
                curveTo(13.992f, 6.607f, 13.318f, 5.95f, 12.468f, 5.66f)
                lineTo(12.277f, 5.603f)
                curveTo(11.895f, 5.501f, 11.43f, 5.5f, 10.5f, 5.5f)
                curveTo(9.571f, 5.5f, 9.105f, 5.501f, 8.724f, 5.603f)
                lineTo(8.532f, 5.66f)
                curveTo(7.59f, 5.982f, 6.863f, 6.753f, 6.603f, 7.724f)
                lineTo(6.569f, 7.871f)
                curveTo(6.5f, 8.229f, 6.5f, 8.687f, 6.5f, 9.5f)
                verticalLineTo(11.5f)
                curveTo(6.5f, 12.43f, 6.501f, 12.895f, 6.603f, 13.277f)
                curveTo(6.857f, 14.225f, 7.558f, 14.982f, 8.469f, 15.316f)
                curveTo(8.49f, 15.621f, 8.527f, 15.908f, 8.6f, 16.179f)
                curveTo(8.672f, 16.447f, 8.769f, 16.703f, 8.885f, 16.948f)
                curveTo(8.694f, 16.926f, 8.511f, 16.894f, 8.335f, 16.847f)
                curveTo(6.783f, 16.431f, 5.57f, 15.218f, 5.154f, 13.665f)
                curveTo(4.987f, 13.045f, 5f, 12.333f, 5f, 11.5f)
                verticalLineTo(9.5f)
                curveTo(5f, 8.667f, 4.987f, 7.955f, 5.154f, 7.335f)
                curveTo(5.57f, 5.783f, 6.783f, 4.57f, 8.335f, 4.154f)
                curveTo(8.955f, 3.987f, 9.667f, 4f, 10.5f, 4f)
                close()
            }
        }.build()

        return _Copy!!
    }

@Suppress("ObjectPropertyName")
private var _Copy: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CopyPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Copy, contentDescription = null)
    }
}
