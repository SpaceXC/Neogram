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

val NeogramIcons.Weblink: ImageVector
    get() {
        if (_Weblink != null) {
            return _Weblink!!
        }
        _Weblink = ImageVector.Builder(
            name = "Weblink",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(4.25f, 12f)
                curveTo(4.25f, 7.72f, 7.72f, 4.25f, 12f, 4.25f)
                curveTo(16.28f, 4.25f, 19.75f, 7.72f, 19.75f, 12f)
                curveTo(19.75f, 12.414f, 19.414f, 12.75f, 19f, 12.75f)
                curveTo(18.586f, 12.75f, 18.25f, 12.414f, 18.25f, 12f)
                curveTo(18.25f, 11.572f, 18.207f, 11.154f, 18.125f, 10.75f)
                horizontalLineTo(15.662f)
                curveTo(15.719f, 11.154f, 15.75f, 11.571f, 15.75f, 12f)
                curveTo(15.75f, 12.414f, 15.414f, 12.75f, 15f, 12.75f)
                curveTo(14.586f, 12.75f, 14.25f, 12.414f, 14.25f, 12f)
                curveTo(14.25f, 11.571f, 14.212f, 11.153f, 14.144f, 10.75f)
                horizontalLineTo(9.856f)
                curveTo(9.788f, 11.153f, 9.75f, 11.571f, 9.75f, 12f)
                curveTo(9.75f, 12.429f, 9.788f, 12.847f, 9.856f, 13.25f)
                horizontalLineTo(12f)
                curveTo(12.414f, 13.25f, 12.75f, 13.586f, 12.75f, 14f)
                curveTo(12.75f, 14.414f, 12.414f, 14.75f, 12f, 14.75f)
                horizontalLineTo(10.252f)
                curveTo(10.502f, 15.446f, 10.82f, 16.075f, 11.143f, 16.614f)
                curveTo(11.496f, 17.202f, 11.849f, 17.672f, 12.112f, 17.994f)
                curveTo(12.243f, 18.154f, 12.351f, 18.277f, 12.425f, 18.358f)
                curveTo(12.462f, 18.398f, 12.49f, 18.428f, 12.508f, 18.447f)
                lineTo(12.527f, 18.467f)
                lineTo(12.531f, 18.47f)
                curveTo(12.666f, 18.606f, 12.75f, 18.793f, 12.75f, 19f)
                curveTo(12.75f, 19.414f, 12.414f, 19.75f, 12f, 19.75f)
                curveTo(7.72f, 19.75f, 4.25f, 16.28f, 4.25f, 12f)
                close()
                moveTo(8.25f, 12f)
                curveTo(8.25f, 12.429f, 8.281f, 12.846f, 8.338f, 13.25f)
                horizontalLineTo(5.875f)
                curveTo(5.793f, 12.846f, 5.75f, 12.428f, 5.75f, 12f)
                curveTo(5.75f, 11.572f, 5.793f, 11.154f, 5.875f, 10.75f)
                horizontalLineTo(8.338f)
                curveTo(8.281f, 11.154f, 8.25f, 11.571f, 8.25f, 12f)
                close()
                moveTo(10.25f, 18.002f)
                curveTo(8.552f, 17.507f, 7.153f, 16.312f, 6.386f, 14.75f)
                horizontalLineTo(8.672f)
                curveTo(8.982f, 15.77f, 9.424f, 16.664f, 9.857f, 17.386f)
                curveTo(9.989f, 17.606f, 10.121f, 17.812f, 10.25f, 18.002f)
                close()
                moveTo(13.748f, 9.25f)
                curveTo(13.498f, 8.554f, 13.18f, 7.925f, 12.857f, 7.386f)
                curveTo(12.552f, 6.878f, 12.247f, 6.457f, 12f, 6.145f)
                curveTo(11.753f, 6.457f, 11.448f, 6.878f, 11.143f, 7.386f)
                curveTo(10.82f, 7.925f, 10.502f, 8.554f, 10.252f, 9.25f)
                horizontalLineTo(13.748f)
                close()
                moveTo(15.328f, 9.25f)
                horizontalLineTo(17.614f)
                curveTo(16.847f, 7.688f, 15.448f, 6.493f, 13.75f, 5.998f)
                curveTo(13.879f, 6.188f, 14.011f, 6.394f, 14.143f, 6.614f)
                curveTo(14.576f, 7.336f, 15.018f, 8.23f, 15.328f, 9.25f)
                close()
                moveTo(8.672f, 9.25f)
                horizontalLineTo(6.386f)
                curveTo(7.153f, 7.688f, 8.552f, 6.493f, 10.25f, 5.998f)
                curveTo(10.121f, 6.188f, 9.989f, 6.394f, 9.857f, 6.614f)
                curveTo(9.424f, 7.336f, 8.982f, 8.23f, 8.672f, 9.25f)
                close()
                moveTo(14.465f, 14.475f)
                curveTo(14.332f, 14.61f, 14.25f, 14.795f, 14.25f, 15f)
                verticalLineTo(18f)
                curveTo(14.25f, 18.414f, 14.586f, 18.75f, 15f, 18.75f)
                curveTo(15.414f, 18.75f, 15.75f, 18.414f, 15.75f, 18f)
                verticalLineTo(16.811f)
                lineTo(18.47f, 19.53f)
                curveTo(18.763f, 19.823f, 19.237f, 19.823f, 19.53f, 19.53f)
                curveTo(19.823f, 19.237f, 19.823f, 18.763f, 19.53f, 18.47f)
                lineTo(16.811f, 15.75f)
                horizontalLineTo(18f)
                curveTo(18.414f, 15.75f, 18.75f, 15.414f, 18.75f, 15f)
                curveTo(18.75f, 14.586f, 18.414f, 14.25f, 18f, 14.25f)
                horizontalLineTo(15f)
                curveTo(14.898f, 14.25f, 14.801f, 14.27f, 14.713f, 14.307f)
                curveTo(14.627f, 14.343f, 14.545f, 14.395f, 14.475f, 14.465f)
                curveTo(14.471f, 14.468f, 14.468f, 14.471f, 14.465f, 14.475f)
                close()
            }
        }.build()

        return _Weblink!!
    }

@Suppress("ObjectPropertyName")
private var _Weblink: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun WeblinkPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Weblink, contentDescription = null)
    }
}
