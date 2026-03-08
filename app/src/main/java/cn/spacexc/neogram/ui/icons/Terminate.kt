package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Terminate: ImageVector
    get() {
        if (_Terminate != null) {
            return _Terminate!!
        }
        _Terminate = ImageVector.Builder(
            name = "Terminate",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFF4848)),
                strokeLineWidth = 1.25f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8.839f, 5.286f)
                curveTo(8.839f, 5.154f, 8.814f, 5.024f, 8.766f, 4.903f)
                curveTo(8.717f, 4.782f, 8.647f, 4.671f, 8.557f, 4.579f)
                curveTo(8.468f, 4.486f, 8.363f, 4.412f, 8.246f, 4.362f)
                curveTo(8.13f, 4.312f, 8.005f, 4.286f, 7.879f, 4.286f)
                curveTo(7.753f, 4.286f, 7.628f, 4.312f, 7.512f, 4.362f)
                curveTo(7.395f, 4.412f, 7.289f, 4.486f, 7.2f, 4.579f)
                curveTo(7.111f, 4.671f, 7.041f, 4.782f, 6.992f, 4.903f)
                curveTo(6.944f, 5.024f, 6.919f, 5.154f, 6.919f, 5.286f)
                verticalLineTo(7.19f)
                moveTo(8.839f, 5.286f)
                verticalLineTo(4.333f)
                curveTo(8.839f, 4.068f, 8.94f, 3.814f, 9.12f, 3.626f)
                curveTo(9.3f, 3.439f, 9.544f, 3.333f, 9.798f, 3.333f)
                curveTo(10.053f, 3.333f, 10.297f, 3.439f, 10.477f, 3.626f)
                curveTo(10.657f, 3.814f, 10.758f, 4.068f, 10.758f, 4.333f)
                verticalLineTo(5.286f)
                moveTo(8.839f, 5.286f)
                lineTo(8.884f, 9.048f)
                moveTo(6.919f, 7.19f)
                curveTo(6.919f, 6.925f, 6.818f, 6.671f, 6.638f, 6.483f)
                curveTo(6.458f, 6.296f, 6.214f, 6.19f, 5.96f, 6.19f)
                curveTo(5.705f, 6.19f, 5.461f, 6.296f, 5.281f, 6.483f)
                curveTo(5.101f, 6.671f, 5f, 6.925f, 5f, 7.19f)
                verticalLineTo(12.381f)
                curveTo(5f, 13.518f, 5.433f, 14.608f, 6.205f, 15.411f)
                curveTo(6.976f, 16.215f, 8.022f, 16.667f, 9.113f, 16.667f)
                horizontalLineTo(10.342f)
                curveTo(11.191f, 16.667f, 12.004f, 16.315f, 12.604f, 15.69f)
                lineTo(13.659f, 14.59f)
                curveTo(14.259f, 13.965f, 14.596f, 13.118f, 14.596f, 12.234f)
                lineTo(14.598f, 10.949f)
                curveTo(14.599f, 10.836f, 14.642f, 10.729f, 14.719f, 10.649f)
                curveTo(14.808f, 10.557f, 14.879f, 10.446f, 14.927f, 10.325f)
                curveTo(14.975f, 10.204f, 15f, 10.074f, 15f, 9.942f)
                curveTo(15f, 9.811f, 14.975f, 9.681f, 14.927f, 9.559f)
                curveTo(14.879f, 9.438f, 14.808f, 9.328f, 14.719f, 9.235f)
                curveTo(14.63f, 9.142f, 14.524f, 9.068f, 14.407f, 9.018f)
                curveTo(14.291f, 8.968f, 14.166f, 8.942f, 14.04f, 8.942f)
                curveTo(13.914f, 8.942f, 13.789f, 8.968f, 13.673f, 9.018f)
                curveTo(13.556f, 9.068f, 13.45f, 9.142f, 13.361f, 9.235f)
                curveTo(12.927f, 9.687f, 12.681f, 10.301f, 12.679f, 10.941f)
                moveTo(6.919f, 7.19f)
                verticalLineTo(10f)
                moveTo(10.758f, 5.286f)
                verticalLineTo(9.524f)
                moveTo(10.758f, 5.286f)
                curveTo(10.758f, 5.021f, 10.859f, 4.766f, 11.039f, 4.579f)
                curveTo(11.219f, 4.391f, 11.463f, 4.286f, 11.717f, 4.286f)
                curveTo(11.972f, 4.286f, 12.216f, 4.391f, 12.396f, 4.579f)
                curveTo(12.576f, 4.766f, 12.677f, 5.021f, 12.677f, 5.286f)
                verticalLineTo(11.905f)
                moveTo(12.677f, 11.905f)
                curveTo(12.317f, 11.905f, 11.961f, 11.978f, 11.628f, 12.122f)
                curveTo(11.296f, 12.266f, 10.994f, 12.476f, 10.74f, 12.742f)
                moveTo(12.677f, 11.905f)
                horizontalLineTo(12.678f)
            }
        }.build()

        return _Terminate!!
    }

@Suppress("ObjectPropertyName")
private var _Terminate: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun TerminatePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Terminate, contentDescription = null)
    }
}
