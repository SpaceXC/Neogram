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

val NeogramIcons.Settings1: ImageVector
    get() {
        if (_Settings1 != null) {
            return _Settings1!!
        }
        _Settings1 = ImageVector.Builder(
            name = "Settings1",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(16.327f, 4.962f)
                lineTo(14.868f, 5.099f)
                lineTo(13.921f, 3.962f)
                curveTo(12.921f, 2.762f, 11.079f, 2.762f, 10.079f, 3.962f)
                lineTo(9.131f, 5.099f)
                lineTo(7.9f, 4.983f)
                curveTo(6.176f, 4.821f, 4.813f, 6.417f, 5.244f, 8.094f)
                lineTo(5.426f, 8.802f)
                lineTo(3.993f, 10.03f)
                curveTo(2.761f, 11.087f, 2.846f, 13.019f, 4.167f, 13.963f)
                lineTo(5.43f, 14.865f)
                lineTo(5.18f, 15.987f)
                curveTo(4.809f, 17.644f, 6.163f, 19.181f, 7.854f, 19.021f)
                lineTo(9.131f, 18.901f)
                lineTo(10.079f, 20.038f)
                curveTo(11.079f, 21.238f, 12.921f, 21.238f, 13.921f, 20.038f)
                lineTo(14.868f, 18.901f)
                lineTo(16.296f, 19.035f)
                curveTo(17.872f, 19.184f, 19.187f, 17.85f, 19.016f, 16.278f)
                lineTo(18.902f, 15.219f)
                lineTo(20.198f, 13.923f)
                curveTo(21.314f, 12.808f, 21.13f, 10.95f, 19.817f, 10.075f)
                lineTo(18.902f, 9.465f)
                lineTo(19.052f, 7.659f)
                curveTo(19.182f, 6.107f, 17.877f, 4.816f, 16.327f, 4.962f)
                close()
                moveTo(11.232f, 4.922f)
                curveTo(11.632f, 4.442f, 12.368f, 4.442f, 12.768f, 4.922f)
                lineTo(13.885f, 6.262f)
                curveTo(14.096f, 6.515f, 14.418f, 6.648f, 14.747f, 6.617f)
                lineTo(16.467f, 6.455f)
                curveTo(17.087f, 6.397f, 17.609f, 6.913f, 17.558f, 7.534f)
                lineTo(17.382f, 9.634f)
                curveTo(17.352f, 9.997f, 17.521f, 10.348f, 17.824f, 10.55f)
                lineTo(18.985f, 11.323f)
                curveTo(19.51f, 11.673f, 19.584f, 12.416f, 19.138f, 12.863f)
                lineTo(17.672f, 14.328f)
                curveTo(17.458f, 14.542f, 17.352f, 14.842f, 17.385f, 15.143f)
                lineTo(17.525f, 16.439f)
                curveTo(17.593f, 17.068f, 17.067f, 17.601f, 16.437f, 17.542f)
                lineTo(14.747f, 17.383f)
                curveTo(14.418f, 17.352f, 14.096f, 17.485f, 13.885f, 17.738f)
                lineTo(12.768f, 19.078f)
                curveTo(12.368f, 19.558f, 11.632f, 19.558f, 11.232f, 19.078f)
                lineTo(10.115f, 17.738f)
                curveTo(9.904f, 17.485f, 9.582f, 17.352f, 9.253f, 17.383f)
                lineTo(7.713f, 17.528f)
                curveTo(7.037f, 17.591f, 6.495f, 16.977f, 6.643f, 16.314f)
                lineTo(6.966f, 14.869f)
                curveTo(7.054f, 14.477f, 6.899f, 14.071f, 6.572f, 13.837f)
                lineTo(5.039f, 12.742f)
                curveTo(4.511f, 12.365f, 4.476f, 11.592f, 4.969f, 11.169f)
                lineTo(6.638f, 9.738f)
                curveTo(6.928f, 9.49f, 7.051f, 9.1f, 6.956f, 8.73f)
                lineTo(6.697f, 7.721f)
                curveTo(6.525f, 7.05f, 7.07f, 6.412f, 7.759f, 6.477f)
                lineTo(9.253f, 6.617f)
                curveTo(9.582f, 6.648f, 9.904f, 6.515f, 10.115f, 6.262f)
                lineTo(11.232f, 4.922f)
                close()
                moveTo(12f, 14.5f)
                curveTo(10.619f, 14.5f, 9.5f, 13.381f, 9.5f, 12f)
                curveTo(9.5f, 10.619f, 10.619f, 9.5f, 12f, 9.5f)
                curveTo(13.381f, 9.5f, 14.5f, 10.619f, 14.5f, 12f)
                curveTo(14.5f, 13.381f, 13.381f, 14.5f, 12f, 14.5f)
                close()
                moveTo(8f, 12f)
                curveTo(8f, 9.791f, 9.791f, 8f, 12f, 8f)
                curveTo(14.209f, 8f, 16f, 9.791f, 16f, 12f)
                curveTo(16f, 14.209f, 14.209f, 16f, 12f, 16f)
                curveTo(9.791f, 16f, 8f, 14.209f, 8f, 12f)
                close()
            }
        }.build()

        return _Settings1!!
    }

@Suppress("ObjectPropertyName")
private var _Settings1: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun Settings1Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Settings1, contentDescription = null)
    }
}
