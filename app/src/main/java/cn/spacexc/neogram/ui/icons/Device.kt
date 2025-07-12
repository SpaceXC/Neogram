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

val NeogramIcons.Device: ImageVector
    get() {
        if (_Device != null) {
            return _Device!!
        }
        _Device = ImageVector.Builder(
            name = "Device",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 22f,
            viewportHeight = 22f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(11.229f, 16.5f)
                curveTo(11.608f, 16.5f, 11.916f, 16.808f, 11.916f, 17.187f)
                curveTo(11.916f, 17.567f, 11.608f, 17.875f, 11.229f, 17.875f)
                horizontalLineTo(2.521f)
                curveTo(2.141f, 17.875f, 1.833f, 17.567f, 1.833f, 17.187f)
                curveTo(1.834f, 16.808f, 2.141f, 16.5f, 2.521f, 16.5f)
                horizontalLineTo(11.229f)
                close()
                moveTo(14.666f, 3.667f)
                curveTo(15.075f, 3.667f, 15.45f, 3.666f, 15.763f, 3.687f)
                curveTo(16.089f, 3.709f, 16.447f, 3.76f, 16.811f, 3.911f)
                curveTo(17.597f, 4.236f, 18.222f, 4.861f, 18.547f, 5.647f)
                curveTo(18.698f, 6.011f, 18.749f, 6.369f, 18.771f, 6.695f)
                curveTo(18.784f, 6.887f, 18.788f, 7.102f, 18.789f, 7.334f)
                horizontalLineTo(17.415f)
                curveTo(17.412f, 6.86f, 17.396f, 6.552f, 17.324f, 6.304f)
                lineTo(17.277f, 6.174f)
                curveTo(17.114f, 5.78f, 16.82f, 5.458f, 16.449f, 5.258f)
                lineTo(16.285f, 5.181f)
                curveTo(15.948f, 5.042f, 15.52f, 5.042f, 14.666f, 5.042f)
                horizontalLineTo(7.517f)
                lineTo(6.836f, 5.045f)
                curveTo(6.241f, 5.054f, 5.878f, 5.092f, 5.584f, 5.242f)
                lineTo(5.458f, 5.312f)
                curveTo(5.171f, 5.488f, 4.937f, 5.741f, 4.784f, 6.043f)
                lineTo(4.717f, 6.196f)
                curveTo(4.583f, 6.571f, 4.583f, 7.077f, 4.583f, 7.975f)
                verticalLineTo(11.275f)
                lineTo(4.586f, 11.955f)
                curveTo(4.596f, 12.551f, 4.634f, 12.913f, 4.784f, 13.208f)
                curveTo(4.959f, 13.552f, 5.24f, 13.833f, 5.584f, 14.009f)
                curveTo(5.878f, 14.158f, 6.241f, 14.195f, 6.836f, 14.205f)
                lineTo(7.517f, 14.209f)
                horizontalLineTo(11.916f)
                verticalLineTo(15.584f)
                horizontalLineTo(7.517f)
                curveTo(7.026f, 15.584f, 6.575f, 15.584f, 6.201f, 15.553f)
                curveTo(5.81f, 15.521f, 5.382f, 15.449f, 4.96f, 15.234f)
                curveTo(4.357f, 14.927f, 3.866f, 14.435f, 3.558f, 13.832f)
                curveTo(3.343f, 13.41f, 3.27f, 12.982f, 3.238f, 12.592f)
                curveTo(3.207f, 12.217f, 3.208f, 11.766f, 3.208f, 11.275f)
                verticalLineTo(7.975f)
                curveTo(3.208f, 7.485f, 3.207f, 7.034f, 3.238f, 6.659f)
                curveTo(3.27f, 6.268f, 3.343f, 5.841f, 3.558f, 5.419f)
                curveTo(3.866f, 4.815f, 4.357f, 4.324f, 4.96f, 4.016f)
                curveTo(5.382f, 3.801f, 5.81f, 3.728f, 6.201f, 3.696f)
                curveTo(6.575f, 3.665f, 7.026f, 3.667f, 7.517f, 3.667f)
                horizontalLineTo(14.666f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f
            ) {
                moveTo(14.667f, 8.479f)
                lineTo(17.417f, 8.479f)
                arcTo(1.604f, 1.604f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.021f, 10.083f)
                lineTo(19.021f, 16.5f)
                arcTo(1.604f, 1.604f, 0f, isMoreThanHalf = false, isPositiveArc = true, 17.417f, 18.104f)
                lineTo(14.667f, 18.104f)
                arcTo(1.604f, 1.604f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13.063f, 16.5f)
                lineTo(13.063f, 10.083f)
                arcTo(1.604f, 1.604f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14.667f, 8.479f)
                close()
            }
        }.build()

        return _Device!!
    }

@Suppress("ObjectPropertyName")
private var _Device: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun DevicePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Device, contentDescription = null)
    }
}
