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

val NeogramIcons.Chat: ImageVector
    get() {
        if (_Chat != null) {
            return _Chat!!
        }
        _Chat = ImageVector.Builder(
            name = "Chat",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 22f,
            viewportHeight = 22f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(11f, 3.667f)
                curveTo(15.05f, 3.667f, 18.334f, 6.55f, 18.334f, 10.107f)
                lineTo(18.324f, 10.438f)
                curveTo(18.128f, 13.841f, 14.923f, 16.548f, 11f, 16.548f)
                lineTo(10.729f, 16.54f)
                curveTo(10.091f, 16.507f, 9.427f, 16.368f, 9.004f, 16.264f)
                curveTo(8.72f, 16.195f, 8.418f, 16.248f, 8.174f, 16.408f)
                curveTo(7.521f, 16.834f, 6.168f, 17.611f, 5.185f, 17.46f)
                lineTo(4.994f, 17.417f)
                curveTo(3.706f, 17.027f, 5.311f, 14.501f, 5.027f, 13.859f)
                lineTo(4.994f, 13.802f)
                curveTo(4.544f, 13.24f, 4.194f, 12.611f, 3.966f, 11.936f)
                curveTo(3.796f, 11.429f, 3.694f, 10.895f, 3.672f, 10.344f)
                lineTo(3.667f, 10.107f)
                curveTo(3.667f, 6.55f, 6.95f, 3.667f, 11f, 3.667f)
                close()
                moveTo(11f, 5.042f)
                curveTo(7.536f, 5.042f, 5.042f, 7.472f, 5.042f, 10.107f)
                curveTo(5.042f, 10.59f, 5.121f, 11.056f, 5.27f, 11.498f)
                curveTo(5.444f, 12.012f, 5.713f, 12.5f, 6.068f, 12.944f)
                curveTo(6.433f, 13.4f, 6.444f, 13.913f, 6.435f, 14.145f)
                curveTo(6.425f, 14.415f, 6.368f, 14.676f, 6.319f, 14.872f)
                curveTo(6.267f, 15.077f, 6.201f, 15.292f, 6.143f, 15.479f)
                curveTo(6.086f, 15.663f, 6.036f, 15.828f, 5.99f, 15.989f)
                curveTo(6.052f, 15.968f, 6.117f, 15.945f, 6.185f, 15.918f)
                curveTo(6.648f, 15.733f, 7.109f, 15.461f, 7.422f, 15.257f)
                curveTo(7.91f, 14.938f, 8.501f, 14.802f, 9.083f, 14.882f)
                lineTo(9.331f, 14.929f)
                lineTo(9.708f, 15.014f)
                curveTo(10.114f, 15.098f, 10.591f, 15.172f, 11f, 15.172f)
                curveTo(14.464f, 15.172f, 16.958f, 12.742f, 16.959f, 10.107f)
                curveTo(16.959f, 7.472f, 14.464f, 5.042f, 11f, 5.042f)
                close()
            }
        }.build()

        return _Chat!!
    }

@Suppress("ObjectPropertyName")
private var _Chat: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ChatPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Chat, contentDescription = null)
    }
}
