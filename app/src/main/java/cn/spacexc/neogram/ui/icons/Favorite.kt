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

val NeogramIcons.Favorite: ImageVector
    get() {
        if (_Favorite != null) {
            return _Favorite!!
        }
        _Favorite = ImageVector.Builder(
            name = "Favorite",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(10.336f, 5.695f)
                curveTo(10.86f, 4.083f, 13.14f, 4.083f, 13.664f, 5.695f)
                lineTo(14.51f, 8.296f)
                curveTo(14.543f, 8.399f, 14.639f, 8.469f, 14.747f, 8.469f)
                horizontalLineTo(17.481f)
                curveTo(19.177f, 8.469f, 19.882f, 10.638f, 18.511f, 11.635f)
                lineTo(16.298f, 13.242f)
                curveTo(16.21f, 13.306f, 16.174f, 13.419f, 16.207f, 13.521f)
                lineTo(17.053f, 16.122f)
                curveTo(17.577f, 17.734f, 15.731f, 19.075f, 14.359f, 18.079f)
                lineTo(12.146f, 16.472f)
                curveTo(12.059f, 16.408f, 11.941f, 16.408f, 11.854f, 16.472f)
                lineTo(9.641f, 18.079f)
                curveTo(8.269f, 19.075f, 6.423f, 17.734f, 6.947f, 16.122f)
                lineTo(7.793f, 13.521f)
                curveTo(7.826f, 13.419f, 7.79f, 13.306f, 7.702f, 13.242f)
                lineTo(5.489f, 11.635f)
                curveTo(4.118f, 10.638f, 4.823f, 8.469f, 6.519f, 8.469f)
                horizontalLineTo(9.253f)
                curveTo(9.361f, 8.469f, 9.457f, 8.399f, 9.49f, 8.296f)
                lineTo(10.336f, 5.695f)
                close()
            }
        }.build()

        return _Favorite!!
    }

@Suppress("ObjectPropertyName")
private var _Favorite: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun FavoritePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Favorite, contentDescription = null)
    }
}
