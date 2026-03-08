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

val NeogramIcons.FilterList: ImageVector
    get() {
        if (_FilterList != null) {
            return _FilterList!!
        }
        _FilterList = ImageVector.Builder(
            name = "FilterList",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(4.25f, 8f)
                curveTo(4.25f, 7.586f, 4.586f, 7.25f, 5f, 7.25f)
                horizontalLineTo(19f)
                curveTo(19.414f, 7.25f, 19.75f, 7.586f, 19.75f, 8f)
                curveTo(19.75f, 8.414f, 19.414f, 8.75f, 19f, 8.75f)
                horizontalLineTo(5f)
                curveTo(4.586f, 8.75f, 4.25f, 8.414f, 4.25f, 8f)
                close()
                moveTo(7.25f, 12f)
                curveTo(7.25f, 11.586f, 7.586f, 11.25f, 8f, 11.25f)
                horizontalLineTo(16f)
                curveTo(16.414f, 11.25f, 16.75f, 11.586f, 16.75f, 12f)
                curveTo(16.75f, 12.414f, 16.414f, 12.75f, 16f, 12.75f)
                horizontalLineTo(8f)
                curveTo(7.586f, 12.75f, 7.25f, 12.414f, 7.25f, 12f)
                close()
                moveTo(11f, 15.25f)
                curveTo(10.586f, 15.25f, 10.25f, 15.586f, 10.25f, 16f)
                curveTo(10.25f, 16.414f, 10.586f, 16.75f, 11f, 16.75f)
                horizontalLineTo(13f)
                curveTo(13.414f, 16.75f, 13.75f, 16.414f, 13.75f, 16f)
                curveTo(13.75f, 15.586f, 13.414f, 15.25f, 13f, 15.25f)
                horizontalLineTo(11f)
                close()
            }
        }.build()

        return _FilterList!!
    }

@Suppress("ObjectPropertyName")
private var _FilterList: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun FilterListPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.FilterList, contentDescription = null)
    }
}
