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

val NeogramIcons.CallIncome: ImageVector
    get() {
        if (_CallIncome != null) {
            return _CallIncome!!
        }
        _CallIncome = ImageVector.Builder(
            name = "CallIncome",
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
                moveTo(17.057f, 8.861f)
                curveTo(17.471f, 8.845f, 17.819f, 9.167f, 17.836f, 9.581f)
                curveTo(17.852f, 9.995f, 17.53f, 10.343f, 17.116f, 10.359f)
                lineTo(13.42f, 10.506f)
                curveTo(13.265f, 10.512f, 13.118f, 10.47f, 12.995f, 10.394f)
                curveTo(12.914f, 10.343f, 12.842f, 10.277f, 12.785f, 10.2f)
                curveTo(12.714f, 10.103f, 12.665f, 9.988f, 12.648f, 9.863f)
                lineTo(12.641f, 9.786f)
                lineTo(12.495f, 6.089f)
                curveTo(12.479f, 5.675f, 12.801f, 5.327f, 13.215f, 5.31f)
                curveTo(13.629f, 5.294f, 13.977f, 5.616f, 13.994f, 6.03f)
                lineTo(14.11f, 8.976f)
                lineTo(17.057f, 8.861f)
                close()
            }
        }.build()

        return _CallIncome!!
    }

@Suppress("ObjectPropertyName")
private var _CallIncome: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CallIncomePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.CallIncome, contentDescription = null)
    }
}
