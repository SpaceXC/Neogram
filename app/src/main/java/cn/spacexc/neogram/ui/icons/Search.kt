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

val NeogramIcons.Search: ImageVector
    get() {
        if (_Search != null) {
            return _Search!!
        }
        _Search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(15.383f, 14.323f)
                curveTo(16.084f, 13.4f, 16.5f, 12.248f, 16.5f, 11f)
                curveTo(16.5f, 7.962f, 14.038f, 5.5f, 11f, 5.5f)
                curveTo(7.962f, 5.5f, 5.5f, 7.962f, 5.5f, 11f)
                curveTo(5.5f, 14.038f, 7.962f, 16.5f, 11f, 16.5f)
                curveTo(12.248f, 16.5f, 13.4f, 16.084f, 14.323f, 15.383f)
                lineTo(16.47f, 17.53f)
                curveTo(16.763f, 17.823f, 17.237f, 17.823f, 17.53f, 17.53f)
                curveTo(17.823f, 17.237f, 17.823f, 16.763f, 17.53f, 16.47f)
                lineTo(15.383f, 14.323f)
                close()
                moveTo(15f, 11f)
                curveTo(15f, 13.209f, 13.209f, 15f, 11f, 15f)
                curveTo(8.791f, 15f, 7f, 13.209f, 7f, 11f)
                curveTo(7f, 8.791f, 8.791f, 7f, 11f, 7f)
                curveTo(13.209f, 7f, 15f, 8.791f, 15f, 11f)
                close()
            }
        }.build()

        return _Search!!
    }

@Suppress("ObjectPropertyName")
private var _Search: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Search, contentDescription = null)
    }
}
