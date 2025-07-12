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

val NeogramIcons.AccountBox: ImageVector
    get() {
        if (_AccountBox != null) {
            return _AccountBox!!
        }
        _AccountBox = ImageVector.Builder(
            name = "AccountBox",
            defaultWidth = 22.dp,
            defaultHeight = 23.dp,
            viewportWidth = 22f,
            viewportHeight = 23f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(13.017f, 3.808f)
                curveTo(13.764f, 3.808f, 14.407f, 3.807f, 14.935f, 3.85f)
                curveTo(15.478f, 3.895f, 16.019f, 3.993f, 16.539f, 4.258f)
                curveTo(17.316f, 4.653f, 17.947f, 5.284f, 18.342f, 6.06f)
                curveTo(18.607f, 6.58f, 18.705f, 7.121f, 18.749f, 7.665f)
                curveTo(18.792f, 8.192f, 18.791f, 8.836f, 18.791f, 9.584f)
                verticalLineTo(13.617f)
                curveTo(18.791f, 14.364f, 18.792f, 15.007f, 18.749f, 15.535f)
                curveTo(18.705f, 16.079f, 18.607f, 16.619f, 18.342f, 17.139f)
                curveTo(17.947f, 17.916f, 17.316f, 18.547f, 16.539f, 18.942f)
                curveTo(16.019f, 19.207f, 15.479f, 19.305f, 14.935f, 19.349f)
                curveTo(14.849f, 19.356f, 14.759f, 19.36f, 14.666f, 19.365f)
                verticalLineTo(19.392f)
                horizontalLineTo(7.333f)
                verticalLineTo(19.365f)
                curveTo(7.241f, 19.36f, 7.151f, 19.356f, 7.065f, 19.349f)
                curveTo(6.521f, 19.305f, 5.98f, 19.207f, 5.46f, 18.942f)
                curveTo(4.684f, 18.547f, 4.053f, 17.916f, 3.658f, 17.139f)
                curveTo(3.393f, 16.619f, 3.295f, 16.078f, 3.25f, 15.535f)
                curveTo(3.207f, 15.007f, 3.208f, 14.364f, 3.208f, 13.617f)
                verticalLineTo(9.584f)
                curveTo(3.208f, 8.836f, 3.207f, 8.192f, 3.25f, 7.665f)
                curveTo(3.295f, 7.121f, 3.393f, 6.58f, 3.658f, 6.06f)
                curveTo(4.053f, 5.284f, 4.684f, 4.653f, 5.46f, 4.258f)
                curveTo(5.98f, 3.993f, 6.521f, 3.895f, 7.065f, 3.85f)
                curveTo(7.592f, 3.807f, 8.236f, 3.808f, 8.984f, 3.808f)
                horizontalLineTo(13.017f)
                close()
                moveTo(11f, 14.35f)
                curveTo(8.984f, 14.35f, 7.348f, 15.977f, 7.333f, 17.99f)
                curveTo(7.523f, 18.002f, 7.731f, 18.009f, 7.963f, 18.012f)
                lineTo(8.984f, 18.016f)
                horizontalLineTo(13.017f)
                lineTo(14.036f, 18.012f)
                curveTo(14.268f, 18.009f, 14.476f, 18.002f, 14.665f, 17.99f)
                curveTo(14.651f, 16.041f, 13.117f, 14.453f, 11.189f, 14.355f)
                lineTo(11f, 14.35f)
                close()
                moveTo(7.963f, 5.188f)
                curveTo(7.07f, 5.202f, 6.526f, 5.258f, 6.085f, 5.483f)
                lineTo(5.896f, 5.59f)
                curveTo(5.465f, 5.854f, 5.114f, 6.232f, 4.883f, 6.685f)
                lineTo(4.83f, 6.798f)
                curveTo(4.583f, 7.373f, 4.583f, 8.14f, 4.583f, 9.584f)
                verticalLineTo(13.617f)
                lineTo(4.588f, 14.637f)
                curveTo(4.602f, 15.53f, 4.658f, 16.074f, 4.883f, 16.515f)
                curveTo(5.127f, 16.994f, 5.508f, 17.388f, 5.973f, 17.653f)
                curveTo(6.137f, 15.361f, 7.831f, 13.493f, 10.04f, 13.067f)
                curveTo(8.609f, 12.652f, 7.563f, 11.332f, 7.563f, 9.766f)
                curveTo(7.563f, 7.868f, 9.102f, 6.329f, 11f, 6.329f)
                curveTo(12.899f, 6.329f, 14.438f, 7.868f, 14.438f, 9.766f)
                curveTo(14.438f, 11.332f, 13.39f, 12.652f, 11.958f, 13.067f)
                curveTo(14.169f, 13.493f, 15.863f, 15.361f, 16.026f, 17.654f)
                curveTo(16.429f, 17.424f, 16.769f, 17.1f, 17.011f, 16.705f)
                lineTo(17.117f, 16.515f)
                curveTo(17.341f, 16.074f, 17.398f, 15.53f, 17.412f, 14.637f)
                lineTo(17.416f, 13.617f)
                verticalLineTo(9.584f)
                curveTo(17.416f, 8.14f, 17.416f, 7.373f, 17.169f, 6.798f)
                lineTo(17.117f, 6.685f)
                curveTo(16.886f, 6.233f, 16.535f, 5.854f, 16.105f, 5.59f)
                lineTo(15.915f, 5.483f)
                curveTo(15.474f, 5.258f, 14.93f, 5.202f, 14.036f, 5.188f)
                lineTo(13.017f, 5.183f)
                horizontalLineTo(8.984f)
                lineTo(7.963f, 5.188f)
                close()
                moveTo(11f, 7.704f)
                curveTo(9.861f, 7.704f, 8.938f, 8.627f, 8.938f, 9.766f)
                curveTo(8.938f, 10.905f, 9.861f, 11.829f, 11f, 11.829f)
                curveTo(12.139f, 11.829f, 13.063f, 10.905f, 13.063f, 9.766f)
                curveTo(13.063f, 8.628f, 12.139f, 7.704f, 11f, 7.704f)
                close()
            }
        }.build()

        return _AccountBox!!
    }

@Suppress("ObjectPropertyName")
private var _AccountBox: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AccountBoxPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.AccountBox, contentDescription = null)
    }
}
