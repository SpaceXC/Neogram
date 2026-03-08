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

val NeogramIcons.All: ImageVector
    get() {
        if (_All != null) {
            return _All!!
        }
        _All = ImageVector.Builder(
            name = "All",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(17.5f, 11.026f)
                curveTo(17.5f, 13.9f, 14.779f, 16.551f, 11f, 16.551f)
                curveTo(10.406f, 16.551f, 9.677f, 16.408f, 9.179f, 16.286f)
                curveTo(8.46f, 16.11f, 7.706f, 16.246f, 7.096f, 16.644f)
                curveTo(6.755f, 16.866f, 6.253f, 17.164f, 5.747f, 17.365f)
                curveTo(5.673f, 17.395f, 5.602f, 17.421f, 5.534f, 17.444f)
                curveTo(5.578f, 17.288f, 5.627f, 17.131f, 5.68f, 16.958f)
                lineTo(5.702f, 16.887f)
                curveTo(5.765f, 16.683f, 5.837f, 16.448f, 5.894f, 16.224f)
                curveTo(5.948f, 16.01f, 6.009f, 15.726f, 6.02f, 15.432f)
                curveTo(6.03f, 15.179f, 6.017f, 14.619f, 5.619f, 14.121f)
                curveTo(5.232f, 13.637f, 4.938f, 13.104f, 4.749f, 12.543f)
                curveTo(4.587f, 12.061f, 4.5f, 11.553f, 4.5f, 11.026f)
                curveTo(4.5f, 8.151f, 7.221f, 5.5f, 11f, 5.5f)
                curveTo(14.779f, 5.5f, 17.5f, 8.151f, 17.5f, 11.026f)
                close()
                moveTo(13.445f, 17.717f)
                curveTo(13.901f, 17.78f, 14.399f, 17.804f, 14.938f, 17.804f)
                curveTo(15.398f, 17.804f, 15.937f, 17.702f, 16.317f, 17.612f)
                curveTo(16.807f, 17.496f, 17.326f, 17.628f, 17.7f, 17.964f)
                curveTo(17.97f, 18.206f, 18.368f, 18.532f, 18.774f, 18.766f)
                curveTo(18.977f, 18.883f, 19.16f, 18.964f, 19.315f, 19.005f)
                curveTo(19.394f, 19.027f, 19.454f, 19.035f, 19.499f, 19.036f)
                lineTo(19.499f, 19.028f)
                curveTo(19.514f, 18.843f, 19.472f, 18.559f, 19.385f, 18.184f)
                curveTo(19.346f, 18.019f, 19.303f, 17.853f, 19.258f, 17.683f)
                lineTo(19.247f, 17.638f)
                curveTo(19.2f, 17.458f, 19.152f, 17.27f, 19.114f, 17.096f)
                curveTo(19.078f, 16.928f, 19.044f, 16.739f, 19.038f, 16.561f)
                curveTo(19.034f, 16.42f, 19.036f, 16.109f, 19.244f, 15.849f)
                curveTo(19.559f, 15.455f, 19.801f, 15.018f, 19.958f, 14.554f)
                curveTo(20.092f, 14.156f, 20.164f, 13.735f, 20.164f, 13.299f)
                curveTo(20.164f, 12.227f, 19.726f, 11.225f, 18.972f, 10.437f)
                curveTo(18.991f, 10.631f, 19f, 10.828f, 19f, 11.026f)
                curveTo(19f, 14.157f, 16.667f, 16.81f, 13.445f, 17.717f)
                close()
                moveTo(18.383f, 8.316f)
                curveTo(17.176f, 5.781f, 14.325f, 4f, 11f, 4f)
                curveTo(6.582f, 4f, 3f, 7.145f, 3f, 11.026f)
                curveTo(3f, 11.719f, 3.114f, 12.389f, 3.328f, 13.022f)
                curveTo(3.575f, 13.758f, 3.957f, 14.444f, 4.447f, 15.057f)
                curveTo(4.628f, 15.283f, 4.448f, 15.864f, 4.247f, 16.515f)
                curveTo(3.931f, 17.537f, 3.562f, 18.732f, 4.447f, 19f)
                curveTo(5.529f, 19.327f, 7.158f, 18.395f, 7.917f, 17.9f)
                curveTo(8.183f, 17.725f, 8.513f, 17.667f, 8.822f, 17.743f)
                curveTo(9.252f, 17.848f, 9.908f, 17.986f, 10.557f, 18.034f)
                curveTo(10.562f, 18.038f, 10.567f, 18.042f, 10.572f, 18.046f)
                curveTo(11.814f, 19.018f, 13.401f, 19.191f, 14.938f, 19.191f)
                curveTo(15.562f, 19.191f, 16.226f, 19.059f, 16.637f, 18.962f)
                curveTo(16.684f, 18.951f, 16.737f, 18.964f, 16.774f, 18.997f)
                curveTo(17.073f, 19.265f, 17.554f, 19.664f, 18.082f, 19.968f)
                curveTo(18.346f, 20.121f, 18.644f, 20.261f, 18.953f, 20.345f)
                curveTo(19.26f, 20.427f, 19.623f, 20.466f, 19.987f, 20.355f)
                curveTo(20.283f, 20.266f, 20.517f, 20.083f, 20.67f, 19.834f)
                curveTo(20.813f, 19.603f, 20.866f, 19.351f, 20.882f, 19.136f)
                curveTo(20.915f, 18.718f, 20.825f, 18.251f, 20.736f, 17.868f)
                curveTo(20.693f, 17.686f, 20.645f, 17.503f, 20.602f, 17.336f)
                lineTo(20.602f, 17.336f)
                lineTo(20.59f, 17.289f)
                curveTo(20.542f, 17.105f, 20.501f, 16.945f, 20.471f, 16.804f)
                curveTo(20.45f, 16.708f, 20.437f, 16.636f, 20.431f, 16.583f)
                curveTo(20.793f, 16.103f, 21.08f, 15.57f, 21.272f, 14.997f)
                curveTo(21.454f, 14.459f, 21.551f, 13.889f, 21.551f, 13.299f)
                curveTo(21.551f, 11.231f, 20.363f, 9.445f, 18.624f, 8.405f)
                curveTo(18.548f, 8.359f, 18.466f, 8.33f, 18.383f, 8.316f)
                close()
                moveTo(20.426f, 16.488f)
                curveTo(20.425f, 16.488f, 20.425f, 16.493f, 20.425f, 16.505f)
                curveTo(20.426f, 16.494f, 20.426f, 16.489f, 20.426f, 16.488f)
                close()
            }
        }.build()

        return _All!!
    }

@Suppress("ObjectPropertyName")
private var _All: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AllPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.All, contentDescription = null)
    }
}
