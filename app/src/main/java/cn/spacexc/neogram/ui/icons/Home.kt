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

val NeogramIcons.Home: ImageVector
    get() {
        if (_Home != null) {
            return _Home!!
        }
        _Home = ImageVector.Builder(
            name = "Home",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(10.11f, 5.592f)
                curveTo(11.207f, 4.684f, 12.793f, 4.684f, 13.89f, 5.592f)
                lineTo(17.678f, 8.729f)
                curveTo(18.357f, 9.292f, 18.75f, 10.129f, 18.75f, 11.011f)
                verticalLineTo(15.788f)
                curveTo(18.75f, 17.424f, 17.424f, 18.75f, 15.788f, 18.75f)
                horizontalLineTo(8.212f)
                curveTo(6.576f, 18.75f, 5.25f, 17.424f, 5.25f, 15.788f)
                verticalLineTo(11.011f)
                lineTo(5.255f, 10.846f)
                curveTo(5.301f, 10.025f, 5.686f, 9.257f, 6.322f, 8.729f)
                lineTo(10.11f, 5.592f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9f, 16f)
                horizontalLineTo(15f)
            }
        }.build()

        return _Home!!
    }

@Suppress("ObjectPropertyName")
private var _Home: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Home, contentDescription = null)
    }
}
