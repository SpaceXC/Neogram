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

val NeogramIcons.CallError: ImageVector
    get() {
        if (_CallError != null) {
            return _CallError!!
        }
        _CallError = ImageVector.Builder(
            name = "CallError",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.55468f
            ) {
                moveTo(4.453f, 5.287f)
                curveTo(4.77f, 4.84f, 5.649f, 4.273f, 6.286f, 3.899f)
                curveTo(6.7f, 3.656f, 7.222f, 3.756f, 7.536f, 4.119f)
                lineTo(9.701f, 6.623f)
                curveTo(9.975f, 6.939f, 10.032f, 7.39f, 9.844f, 7.764f)
                lineTo(8.915f, 9.623f)
                curveTo(8.724f, 10.005f, 8.787f, 10.464f, 9.072f, 10.78f)
                lineTo(12.556f, 14.652f)
                curveTo(12.944f, 15.083f, 13.611f, 15.112f, 14.064f, 14.749f)
                curveTo(14.454f, 14.435f, 14.927f, 14.089f, 15.336f, 13.883f)
                curveTo(16.224f, 13.436f, 16.669f, 13.365f, 17.557f, 13.883f)
                curveTo(18.445f, 14.401f, 20f, 15.956f, 20f, 17.296f)
                curveTo(20f, 18.636f, 18.51f, 19.065f, 16.879f, 19.369f)
                curveTo(15.248f, 19.673f, 13.057f, 18.493f, 11.429f, 17.296f)
                curveTo(9.8f, 16.099f, 5.138f, 11.006f, 4.453f, 9.29f)
                curveTo(3.769f, 7.575f, 3.935f, 6.02f, 4.453f, 5.287f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(17.843f, 4.269f)
                curveTo(18.124f, 3.966f, 18.598f, 3.947f, 18.902f, 4.228f)
                curveTo(19.206f, 4.51f, 19.225f, 4.984f, 18.944f, 5.288f)
                lineTo(16.942f, 7.453f)
                lineTo(19.108f, 9.456f)
                curveTo(19.412f, 9.737f, 19.431f, 10.211f, 19.15f, 10.516f)
                curveTo(18.869f, 10.82f, 18.394f, 10.838f, 18.09f, 10.557f)
                lineTo(15.663f, 8.315f)
                lineTo(13.536f, 10.618f)
                curveTo(13.255f, 10.922f, 12.781f, 10.941f, 12.476f, 10.66f)
                curveTo(12.172f, 10.379f, 12.153f, 9.905f, 12.434f, 9.6f)
                lineTo(14.435f, 7.434f)
                lineTo(12.27f, 5.433f)
                curveTo(11.966f, 5.151f, 11.947f, 4.677f, 12.228f, 4.373f)
                curveTo(12.509f, 4.069f, 12.984f, 4.05f, 13.288f, 4.331f)
                lineTo(15.714f, 6.572f)
                lineTo(17.843f, 4.269f)
                close()
            }
        }.build()

        return _CallError!!
    }

@Suppress("ObjectPropertyName")
private var _CallError: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CallErrorPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.CallError, contentDescription = null)
    }
}
