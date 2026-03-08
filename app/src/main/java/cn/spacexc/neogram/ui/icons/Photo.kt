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

val NeogramIcons.Photo: ImageVector
    get() {
        if (_Photo != null) {
            return _Photo!!
        }
        _Photo = ImageVector.Builder(
            name = "Photo",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 0.5f
            ) {
                moveTo(14.962f, 11.251f)
                curveTo(14.953f, 11.252f, 14.944f, 11.253f, 14.935f, 11.254f)
                curveTo(14.898f, 11.257f, 14.861f, 11.263f, 14.823f, 11.272f)
                curveTo(14.809f, 11.275f, 14.796f, 11.281f, 14.781f, 11.286f)
                curveTo(14.756f, 11.293f, 14.729f, 11.299f, 14.704f, 11.31f)
                curveTo(14.698f, 11.313f, 14.692f, 11.317f, 14.686f, 11.32f)
                curveTo(14.674f, 11.325f, 14.664f, 11.332f, 14.653f, 11.338f)
                curveTo(14.622f, 11.354f, 14.593f, 11.37f, 14.566f, 11.389f)
                curveTo(14.552f, 11.399f, 14.539f, 11.411f, 14.526f, 11.422f)
                curveTo(14.502f, 11.442f, 14.478f, 11.462f, 14.457f, 11.484f)
                curveTo(14.447f, 11.494f, 14.438f, 11.506f, 14.429f, 11.517f)
                curveTo(14.408f, 11.542f, 14.388f, 11.568f, 14.37f, 11.596f)
                curveTo(14.34f, 11.643f, 14.313f, 11.694f, 14.293f, 11.748f)
                lineTo(11.793f, 18.747f)
                curveTo(11.654f, 19.138f, 11.858f, 19.567f, 12.247f, 19.706f)
                curveTo(12.637f, 19.846f, 13.067f, 19.642f, 13.206f, 19.252f)
                lineTo(15.064f, 14.051f)
                lineTo(17.311f, 19.295f)
                curveTo(17.474f, 19.676f, 17.914f, 19.852f, 18.295f, 19.689f)
                curveTo(18.676f, 19.526f, 18.853f, 19.085f, 18.69f, 18.705f)
                lineTo(15.689f, 11.705f)
                lineTo(15.681f, 11.688f)
                curveTo(15.677f, 11.68f, 15.674f, 11.673f, 15.67f, 11.665f)
                lineTo(15.656f, 11.636f)
                curveTo(15.647f, 11.62f, 15.635f, 11.606f, 15.625f, 11.591f)
                curveTo(15.615f, 11.575f, 15.605f, 11.558f, 15.593f, 11.542f)
                curveTo(15.574f, 11.518f, 15.553f, 11.496f, 15.531f, 11.474f)
                curveTo(15.52f, 11.462f, 15.508f, 11.449f, 15.495f, 11.438f)
                curveTo(15.472f, 11.417f, 15.447f, 11.4f, 15.421f, 11.382f)
                curveTo(15.406f, 11.372f, 15.392f, 11.36f, 15.376f, 11.351f)
                curveTo(15.368f, 11.346f, 15.359f, 11.343f, 15.351f, 11.338f)
                curveTo(15.323f, 11.323f, 15.294f, 11.31f, 15.263f, 11.298f)
                curveTo(15.249f, 11.293f, 15.234f, 11.288f, 15.22f, 11.284f)
                curveTo(15.19f, 11.274f, 15.16f, 11.267f, 15.129f, 11.261f)
                curveTo(15.122f, 11.26f, 15.116f, 11.258f, 15.109f, 11.257f)
                curveTo(15.102f, 11.256f, 15.096f, 11.257f, 15.089f, 11.256f)
                curveTo(15.047f, 11.251f, 15.005f, 11.249f, 14.962f, 11.251f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12f, 19f)
                lineTo(9f, 9f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(6f, 19f)
                lineTo(9f, 9f)
            }
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
        }.build()

        return _Photo!!
    }

@Suppress("ObjectPropertyName")
private var _Photo: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun PhotoPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Photo, contentDescription = null)
    }
}
