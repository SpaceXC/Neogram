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

val NeogramIcons.Bot: ImageVector
    get() {
        if (_Bot != null) {
            return _Bot!!
        }
        _Bot = ImageVector.Builder(
            name = "Bot",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(19.5f, 13.5f)
                curveTo(19.904f, 11.884f, 19.976f, 11.221f, 19.923f, 8.938f)
                curveTo(19.898f, 7.858f, 19.008f, 7f, 17.928f, 7f)
                horizontalLineTo(5.962f)
                curveTo(4.909f, 7f, 4.027f, 7.819f, 4.006f, 8.872f)
                curveTo(3.88f, 15.22f, 5.696f, 18.129f, 12.233f, 17.996f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9f, 10f)
                lineTo(9f, 13f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(15f, 10f)
                lineTo(15f, 13f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12f, 5f)
                lineTo(12f, 7f)
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(17.217f, 14.244f)
                curveTo(17.346f, 13.943f, 17.819f, 13.911f, 17.976f, 14.2f)
                curveTo(18.604f, 15.673f, 19.338f, 16.496f, 20.727f, 17.035f)
                curveTo(21.063f, 17.174f, 21.098f, 17.685f, 20.779f, 17.855f)
                curveTo(19.328f, 18.326f, 18.67f, 19.223f, 17.91f, 20.829f)
                curveTo(17.753f, 21.072f, 17.364f, 21.052f, 17.23f, 20.797f)
                curveTo(16.338f, 19.109f, 15.512f, 18.283f, 14.25f, 17.743f)
                curveTo(13.924f, 17.604f, 13.913f, 17.134f, 14.24f, 16.996f)
                curveTo(15.761f, 16.358f, 16.614f, 15.643f, 17.217f, 14.244f)
                close()
            }
        }.build()

        return _Bot!!
    }

@Suppress("ObjectPropertyName")
private var _Bot: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun BotPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Bot, contentDescription = null)
    }
}
