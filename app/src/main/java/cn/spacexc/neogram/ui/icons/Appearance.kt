package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Appearance: ImageVector
    get() {
        if (_Appearance != null) {
            return _Appearance!!
        }
        _Appearance = ImageVector.Builder(
            name = "Appearance",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(12f, 4.25f)
                lineTo(12f, 4.25f)
                arcTo(7.75f, 7.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.75f, 12f)
                lineTo(19.75f, 12f)
                arcTo(7.75f, 7.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 19.75f)
                lineTo(12f, 19.75f)
                arcTo(7.75f, 7.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.25f, 12f)
                lineTo(4.25f, 12f)
                arcTo(7.75f, 7.75f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 4.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9.5f, 18.389f)
                verticalLineTo(11.975f)
                curveTo(9.5f, 11.774f, 9.561f, 11.578f, 9.674f, 11.412f)
                lineTo(11.174f, 9.212f)
                curveTo(11.571f, 8.63f, 12.429f, 8.63f, 12.826f, 9.212f)
                lineTo(14.326f, 11.412f)
                curveTo(14.439f, 11.578f, 14.5f, 11.774f, 14.5f, 11.975f)
                verticalLineTo(19f)
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(9f, 13f)
                curveTo(9f, 12.448f, 9.448f, 12f, 10f, 12f)
                lineTo(12f, 12f)
                lineTo(14f, 12f)
                curveTo(14.552f, 12f, 15f, 12.448f, 15f, 13f)
                horizontalLineTo(9f)
                close()
            }
        }.build()

        return _Appearance!!
    }

@Suppress("ObjectPropertyName")
private var _Appearance: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AppearancePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Appearance, contentDescription = null)
    }
}
