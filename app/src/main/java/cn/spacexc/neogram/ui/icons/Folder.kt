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

val NeogramIcons.Folder: ImageVector
    get() {
        if (_Folder != null) {
            return _Folder!!
        }
        _Folder = ImageVector.Builder(
            name = "Folder",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(10.559f, 4.5f)
                curveTo(12.065f, 4.5f, 13.403f, 5.464f, 13.879f, 6.894f)
                lineTo(13.967f, 7.158f)
                curveTo(14.035f, 7.362f, 14.226f, 7.5f, 14.441f, 7.5f)
                horizontalLineTo(17f)
                curveTo(18.933f, 7.5f, 20.5f, 9.067f, 20.5f, 11f)
                verticalLineTo(15f)
                curveTo(20.5f, 16.933f, 18.933f, 18.5f, 17f, 18.5f)
                horizontalLineTo(7f)
                curveTo(5.067f, 18.5f, 3.5f, 16.933f, 3.5f, 15f)
                verticalLineTo(8f)
                curveTo(3.5f, 6.067f, 5.067f, 4.5f, 7f, 4.5f)
                horizontalLineTo(10.559f)
                close()
                moveTo(5f, 8.991f)
                verticalLineTo(15f)
                lineTo(5.011f, 15.204f)
                curveTo(5.106f, 16.146f, 5.854f, 16.894f, 6.796f, 16.989f)
                lineTo(7f, 17f)
                horizontalLineTo(17f)
                lineTo(17.204f, 16.989f)
                curveTo(18.213f, 16.887f, 19f, 16.036f, 19f, 15f)
                verticalLineTo(11f)
                curveTo(19f, 9.895f, 18.105f, 9f, 17f, 9f)
                horizontalLineTo(14.441f)
                curveTo(14.382f, 9f, 14.323f, 8.996f, 14.265f, 8.991f)
                horizontalLineTo(5f)
                close()
                moveTo(7f, 6f)
                curveTo(5.983f, 6f, 5.147f, 6.759f, 5.02f, 7.741f)
                horizontalLineTo(12.585f)
                lineTo(12.544f, 7.633f)
                lineTo(12.456f, 7.367f)
                curveTo(12.201f, 6.602f, 11.513f, 6.07f, 10.719f, 6.007f)
                lineTo(10.559f, 6f)
                horizontalLineTo(7f)
                close()
            }
        }.build()

        return _Folder!!
    }

@Suppress("ObjectPropertyName")
private var _Folder: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun FolderPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Folder, contentDescription = null)
    }
}
