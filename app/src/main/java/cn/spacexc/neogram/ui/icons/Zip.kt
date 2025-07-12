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

val NeogramIcons.Zip: ImageVector
    get() {
        if (_Zip != null) {
            return _Zip!!
        }
        _Zip = ImageVector.Builder(
            name = "Zip",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(8f, 4.25f)
                lineTo(16f, 4.25f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.75f, 8f)
                lineTo(19.75f, 16f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 19.75f)
                lineTo(8f, 19.75f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.25f, 16f)
                lineTo(4.25f, 8f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 4.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(15.875f, 4.25f)
                curveTo(18.015f, 4.25f, 19.75f, 5.985f, 19.75f, 8.125f)
                curveTo(19.75f, 9.575f, 18.575f, 10.75f, 17.125f, 10.75f)
                horizontalLineTo(6.875f)
                curveTo(5.425f, 10.75f, 4.25f, 9.575f, 4.25f, 8.125f)
                curveTo(4.25f, 5.985f, 5.985f, 4.25f, 8.125f, 4.25f)
                horizontalLineTo(15.875f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(8f, 4.25f)
                lineTo(16f, 4.25f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.75f, 8f)
                lineTo(19.75f, 11f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 14.75f)
                lineTo(8f, 14.75f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.25f, 11f)
                lineTo(4.25f, 8f)
                arcTo(3.75f, 3.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 4.25f)
                close()
            }
        }.build()

        return _Zip!!
    }

@Suppress("ObjectPropertyName")
private var _Zip: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ZipPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Zip, contentDescription = null)
    }
}
