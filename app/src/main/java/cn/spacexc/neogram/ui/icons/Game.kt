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

val NeogramIcons.Game: ImageVector
    get() {
        if (_Game != null) {
            return _Game!!
        }
        _Game = ImageVector.Builder(
            name = "Game",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(7.5f, 6.25f)
                lineTo(16.5f, 6.25f)
                arcTo(4.25f, 4.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 20.75f, 10.5f)
                lineTo(20.75f, 13.5f)
                arcTo(4.25f, 4.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16.5f, 17.75f)
                lineTo(7.5f, 17.75f)
                arcTo(4.25f, 4.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3.25f, 13.5f)
                lineTo(3.25f, 10.5f)
                arcTo(4.25f, 4.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7.5f, 6.25f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(7f, 12f)
                lineTo(11f, 12f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9f, 14f)
                verticalLineTo(10f)
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(16.2f, 9f)
                lineTo(16.2f, 9f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 17.4f, 10.2f)
                lineTo(17.4f, 10.2f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16.2f, 11.4f)
                lineTo(16.2f, 11.4f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 15f, 10.2f)
                lineTo(15f, 10.2f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16.2f, 9f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(14.2f, 12.6f)
                lineTo(14.2f, 12.6f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 15.4f, 13.8f)
                lineTo(15.4f, 13.8f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.2f, 15f)
                lineTo(14.2f, 15f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13f, 13.8f)
                lineTo(13f, 13.8f)
                arcTo(1.2f, 1.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.2f, 12.6f)
                close()
            }
        }.build()

        return _Game!!
    }

@Suppress("ObjectPropertyName")
private var _Game: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun GamePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Game, contentDescription = null)
    }
}
