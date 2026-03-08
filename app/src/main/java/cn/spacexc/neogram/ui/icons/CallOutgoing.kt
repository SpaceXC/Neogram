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

val NeogramIcons.CallOutgoing: ImageVector
    get() {
        if (_CallOutgoing != null) {
            return _CallOutgoing!!
        }
        _CallOutgoing = ImageVector.Builder(
            name = "CallOutgoing",
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
                moveTo(13.089f, 7.73f)
                curveTo(12.675f, 7.747f, 12.327f, 7.424f, 12.31f, 7.011f)
                curveTo(12.294f, 6.597f, 12.616f, 6.248f, 13.03f, 6.232f)
                lineTo(16.726f, 6.085f)
                curveTo(16.882f, 6.079f, 17.028f, 6.121f, 17.151f, 6.197f)
                curveTo(17.233f, 6.248f, 17.304f, 6.314f, 17.361f, 6.391f)
                curveTo(17.433f, 6.488f, 17.48f, 6.604f, 17.498f, 6.729f)
                lineTo(17.505f, 6.805f)
                lineTo(17.651f, 10.502f)
                curveTo(17.667f, 10.916f, 17.345f, 11.265f, 16.931f, 11.281f)
                curveTo(16.517f, 11.297f, 16.169f, 10.975f, 16.152f, 10.561f)
                lineTo(16.036f, 7.615f)
                lineTo(13.089f, 7.73f)
                close()
            }
        }.build()

        return _CallOutgoing!!
    }

@Suppress("ObjectPropertyName")
private var _CallOutgoing: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CallOutgoingPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.CallOutgoing, contentDescription = null)
    }
}
