package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.ExternalLink: ImageVector
    get() {
        if (_ExternalLink != null) {
            return _ExternalLink!!
        }
        _ExternalLink = ImageVector.Builder(
            name = "ExternalLink",
            defaultWidth = 13.dp,
            defaultHeight = 12.dp,
            viewportWidth = 13f,
            viewportHeight = 12f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0.5f, 0f)
                    horizontalLineToRelative(12f)
                    verticalLineToRelative(12f)
                    horizontalLineToRelative(-12f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color(0xFFED7267))) {
                    moveTo(9.25f, 9.285f)
                    verticalLineTo(10.2f)
                    curveTo(9.25f, 10.306f, 9.214f, 10.396f, 9.142f, 10.467f)
                    curveTo(9.07f, 10.539f, 8.981f, 10.575f, 8.875f, 10.575f)
                    curveTo(8.769f, 10.575f, 8.679f, 10.539f, 8.608f, 10.467f)
                    curveTo(8.536f, 10.396f, 8.5f, 10.306f, 8.5f, 10.2f)
                    verticalLineTo(8.606f)
                    curveTo(8.5f, 8.436f, 8.558f, 8.292f, 8.675f, 8.175f)
                    curveTo(8.792f, 8.059f, 8.936f, 8f, 9.106f, 8f)
                    horizontalLineTo(10.7f)
                    curveTo(10.806f, 8f, 10.895f, 8.036f, 10.967f, 8.108f)
                    curveTo(11.039f, 8.179f, 11.075f, 8.269f, 11.075f, 8.375f)
                    curveTo(11.075f, 8.481f, 11.039f, 8.571f, 10.967f, 8.642f)
                    curveTo(10.895f, 8.714f, 10.806f, 8.75f, 10.7f, 8.75f)
                    horizontalLineTo(9.772f)
                    lineTo(11.113f, 10.091f)
                    curveTo(11.183f, 10.16f, 11.217f, 10.246f, 11.217f, 10.349f)
                    curveTo(11.217f, 10.451f, 11.183f, 10.539f, 11.113f, 10.613f)
                    curveTo(11.039f, 10.691f, 10.95f, 10.729f, 10.846f, 10.727f)
                    curveTo(10.742f, 10.726f, 10.653f, 10.688f, 10.579f, 10.613f)
                    lineTo(9.25f, 9.285f)
                    close()
                    moveTo(6.5f, 10.75f)
                    curveTo(5.844f, 10.75f, 5.226f, 10.625f, 4.649f, 10.375f)
                    curveTo(4.071f, 10.126f, 3.568f, 9.787f, 3.14f, 9.36f)
                    curveTo(2.713f, 8.932f, 2.374f, 8.429f, 2.124f, 7.851f)
                    curveTo(1.875f, 7.274f, 1.75f, 6.656f, 1.75f, 6f)
                    curveTo(1.75f, 5.344f, 1.875f, 4.726f, 2.124f, 4.149f)
                    curveTo(2.374f, 3.571f, 2.713f, 3.068f, 3.14f, 2.64f)
                    curveTo(3.568f, 2.213f, 4.071f, 1.874f, 4.649f, 1.625f)
                    curveTo(5.226f, 1.375f, 5.844f, 1.25f, 6.5f, 1.25f)
                    curveTo(7.156f, 1.25f, 7.773f, 1.375f, 8.351f, 1.625f)
                    curveTo(8.929f, 1.874f, 9.432f, 2.213f, 9.86f, 2.64f)
                    curveTo(10.287f, 3.068f, 10.626f, 3.571f, 10.875f, 4.149f)
                    curveTo(11.125f, 4.726f, 11.25f, 5.344f, 11.25f, 6f)
                    curveTo(11.25f, 6.087f, 11.248f, 6.18f, 11.244f, 6.28f)
                    curveTo(11.24f, 6.38f, 11.232f, 6.473f, 11.22f, 6.56f)
                    curveTo(11.207f, 6.666f, 11.162f, 6.75f, 11.086f, 6.812f)
                    curveTo(11.01f, 6.873f, 10.916f, 6.904f, 10.805f, 6.904f)
                    curveTo(10.707f, 6.904f, 10.623f, 6.862f, 10.554f, 6.777f)
                    curveTo(10.485f, 6.692f, 10.457f, 6.599f, 10.47f, 6.498f)
                    curveTo(10.487f, 6.412f, 10.496f, 6.328f, 10.498f, 6.249f)
                    curveTo(10.499f, 6.169f, 10.5f, 6.087f, 10.5f, 6f)
                    curveTo(10.5f, 5.817f, 10.488f, 5.635f, 10.464f, 5.454f)
                    curveTo(10.44f, 5.273f, 10.402f, 5.093f, 10.351f, 4.913f)
                    horizontalLineTo(8.492f)
                    curveTo(8.524f, 5.093f, 8.546f, 5.273f, 8.561f, 5.454f)
                    curveTo(8.575f, 5.635f, 8.582f, 5.817f, 8.582f, 6f)
                    curveTo(8.582f, 6.087f, 8.581f, 6.179f, 8.579f, 6.276f)
                    curveTo(8.578f, 6.373f, 8.573f, 6.465f, 8.564f, 6.552f)
                    curveTo(8.551f, 6.658f, 8.507f, 6.744f, 8.433f, 6.808f)
                    curveTo(8.358f, 6.872f, 8.268f, 6.904f, 8.161f, 6.904f)
                    curveTo(8.063f, 6.904f, 7.978f, 6.864f, 7.904f, 6.785f)
                    curveTo(7.831f, 6.705f, 7.801f, 6.615f, 7.814f, 6.514f)
                    curveTo(7.823f, 6.427f, 7.828f, 6.341f, 7.829f, 6.257f)
                    curveTo(7.831f, 6.172f, 7.832f, 6.087f, 7.832f, 6f)
                    curveTo(7.832f, 5.817f, 7.825f, 5.635f, 7.811f, 5.454f)
                    curveTo(7.796f, 5.273f, 7.774f, 5.093f, 7.742f, 4.913f)
                    horizontalLineTo(5.258f)
                    curveTo(5.226f, 5.093f, 5.203f, 5.273f, 5.189f, 5.454f)
                    curveTo(5.175f, 5.635f, 5.168f, 5.817f, 5.168f, 6f)
                    curveTo(5.168f, 6.183f, 5.175f, 6.365f, 5.189f, 6.546f)
                    curveTo(5.203f, 6.727f, 5.226f, 6.907f, 5.258f, 7.087f)
                    horizontalLineTo(6.721f)
                    curveTo(6.827f, 7.087f, 6.917f, 7.122f, 6.988f, 7.194f)
                    curveTo(7.06f, 7.266f, 7.096f, 7.355f, 7.096f, 7.462f)
                    curveTo(7.096f, 7.568f, 7.06f, 7.657f, 6.988f, 7.729f)
                    curveTo(6.917f, 7.801f, 6.827f, 7.837f, 6.721f, 7.837f)
                    horizontalLineTo(5.43f)
                    curveTo(5.539f, 8.23f, 5.682f, 8.608f, 5.858f, 8.969f)
                    curveTo(6.034f, 9.33f, 6.248f, 9.67f, 6.5f, 9.989f)
                    curveTo(6.59f, 9.989f, 6.679f, 9.99f, 6.769f, 9.991f)
                    curveTo(6.859f, 9.992f, 6.947f, 9.986f, 7.034f, 9.975f)
                    curveTo(7.137f, 9.962f, 7.223f, 9.988f, 7.292f, 10.055f)
                    curveTo(7.361f, 10.121f, 7.396f, 10.206f, 7.396f, 10.31f)
                    curveTo(7.396f, 10.421f, 7.367f, 10.514f, 7.31f, 10.589f)
                    curveTo(7.253f, 10.663f, 7.171f, 10.707f, 7.064f, 10.72f)
                    curveTo(6.978f, 10.732f, 6.885f, 10.74f, 6.785f, 10.744f)
                    curveTo(6.685f, 10.748f, 6.59f, 10.75f, 6.5f, 10.75f)
                    close()
                    moveTo(2.649f, 7.087f)
                    horizontalLineTo(4.508f)
                    curveTo(4.476f, 6.907f, 4.453f, 6.727f, 4.439f, 6.546f)
                    curveTo(4.425f, 6.365f, 4.418f, 6.183f, 4.418f, 6f)
                    curveTo(4.418f, 5.817f, 4.425f, 5.635f, 4.439f, 5.454f)
                    curveTo(4.453f, 5.273f, 4.476f, 5.093f, 4.508f, 4.913f)
                    horizontalLineTo(2.649f)
                    curveTo(2.598f, 5.093f, 2.56f, 5.273f, 2.536f, 5.454f)
                    curveTo(2.512f, 5.635f, 2.5f, 5.817f, 2.5f, 6f)
                    curveTo(2.5f, 6.183f, 2.512f, 6.365f, 2.536f, 6.546f)
                    curveTo(2.56f, 6.727f, 2.598f, 6.907f, 2.649f, 7.087f)
                    close()
                    moveTo(5.532f, 9.861f)
                    curveTo(5.33f, 9.549f, 5.158f, 9.224f, 5.013f, 8.885f)
                    curveTo(4.868f, 8.546f, 4.751f, 8.196f, 4.66f, 7.837f)
                    horizontalLineTo(2.963f)
                    curveTo(3.224f, 8.351f, 3.578f, 8.786f, 4.025f, 9.139f)
                    curveTo(4.472f, 9.493f, 4.974f, 9.734f, 5.532f, 9.861f)
                    close()
                    moveTo(2.963f, 4.163f)
                    horizontalLineTo(4.66f)
                    curveTo(4.744f, 3.801f, 4.858f, 3.45f, 5.001f, 3.111f)
                    curveTo(5.144f, 2.771f, 5.321f, 2.447f, 5.532f, 2.138f)
                    curveTo(4.971f, 2.263f, 4.468f, 2.502f, 4.023f, 2.856f)
                    curveTo(3.577f, 3.21f, 3.224f, 3.645f, 2.963f, 4.163f)
                    close()
                    moveTo(5.43f, 4.163f)
                    horizontalLineTo(7.57f)
                    curveTo(7.464f, 3.77f, 7.32f, 3.394f, 7.139f, 3.036f)
                    curveTo(6.959f, 2.678f, 6.745f, 2.336f, 6.5f, 2.011f)
                    curveTo(6.251f, 2.33f, 6.037f, 2.67f, 5.858f, 3.031f)
                    curveTo(5.679f, 3.392f, 5.536f, 3.77f, 5.43f, 4.163f)
                    close()
                    moveTo(8.34f, 4.163f)
                    horizontalLineTo(10.036f)
                    curveTo(9.776f, 3.645f, 9.423f, 3.209f, 8.977f, 2.853f)
                    curveTo(8.532f, 2.498f, 8.029f, 2.26f, 7.468f, 2.138f)
                    curveTo(7.669f, 2.451f, 7.84f, 2.777f, 7.98f, 3.118f)
                    curveTo(8.119f, 3.458f, 8.24f, 3.807f, 8.34f, 4.163f)
                    close()
                }
            }
        }.build()

        return _ExternalLink!!
    }

@Suppress("ObjectPropertyName")
private var _ExternalLink: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ExternalLinkPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ExternalLink, contentDescription = null)
    }
}
