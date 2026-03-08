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

val NeogramIcons.ChatBubble: ImageVector
    get() {
        if (_ChatBubble != null) {
            return _ChatBubble!!
        }
        _ChatBubble = ImageVector.Builder(
            name = "ChatBubble",
            defaultWidth = 14.dp,
            defaultHeight = 14.dp,
            viewportWidth = 14f,
            viewportHeight = 14f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(14f)
                    verticalLineToRelative(14f)
                    horizontalLineToRelative(-14f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color(0xFFFFFFFF))) {
                    moveTo(2.035f, 9.486f)
                    curveTo(1.85f, 9.091f, 1.708f, 8.686f, 1.608f, 8.272f)
                    curveTo(1.508f, 7.858f, 1.458f, 7.434f, 1.458f, 7f)
                    curveTo(1.458f, 6.233f, 1.604f, 5.513f, 1.895f, 4.839f)
                    curveTo(2.186f, 4.165f, 2.58f, 3.578f, 3.079f, 3.079f)
                    curveTo(3.578f, 2.581f, 4.164f, 2.186f, 4.838f, 1.895f)
                    curveTo(5.512f, 1.604f, 6.233f, 1.458f, 6.999f, 1.458f)
                    curveTo(7.765f, 1.458f, 8.486f, 1.604f, 9.16f, 1.895f)
                    curveTo(9.835f, 2.186f, 10.421f, 2.58f, 10.92f, 3.079f)
                    curveTo(11.419f, 3.578f, 11.814f, 4.164f, 12.105f, 4.838f)
                    curveTo(12.396f, 5.512f, 12.542f, 6.233f, 12.542f, 6.999f)
                    curveTo(12.542f, 7.765f, 12.396f, 8.486f, 12.105f, 9.16f)
                    curveTo(11.814f, 9.835f, 11.42f, 10.421f, 10.921f, 10.92f)
                    curveTo(10.422f, 11.419f, 9.835f, 11.814f, 9.161f, 12.105f)
                    curveTo(8.487f, 12.396f, 7.767f, 12.542f, 7f, 12.542f)
                    curveTo(6.566f, 12.542f, 6.142f, 12.492f, 5.728f, 12.392f)
                    curveTo(5.314f, 12.292f, 4.909f, 12.15f, 4.514f, 11.965f)
                    lineTo(1.933f, 12.721f)
                    curveTo(1.733f, 12.782f, 1.559f, 12.738f, 1.411f, 12.589f)
                    curveTo(1.262f, 12.441f, 1.218f, 12.267f, 1.279f, 12.067f)
                    lineTo(2.035f, 9.486f)
                    close()
                    moveTo(2.304f, 11.696f)
                    lineTo(4.171f, 11.142f)
                    curveTo(4.314f, 11.103f, 4.453f, 11.086f, 4.586f, 11.092f)
                    curveTo(4.72f, 11.098f, 4.854f, 11.134f, 4.987f, 11.2f)
                    curveTo(5.299f, 11.356f, 5.624f, 11.472f, 5.965f, 11.55f)
                    curveTo(6.305f, 11.628f, 6.65f, 11.667f, 7f, 11.667f)
                    curveTo(8.303f, 11.667f, 9.406f, 11.215f, 10.31f, 10.31f)
                    curveTo(11.215f, 9.406f, 11.667f, 8.303f, 11.667f, 7f)
                    curveTo(11.667f, 5.697f, 11.215f, 4.594f, 10.31f, 3.69f)
                    curveTo(9.406f, 2.785f, 8.303f, 2.333f, 7f, 2.333f)
                    curveTo(5.697f, 2.333f, 4.594f, 2.785f, 3.69f, 3.69f)
                    curveTo(2.785f, 4.594f, 2.333f, 5.697f, 2.333f, 7f)
                    curveTo(2.333f, 7.35f, 2.372f, 7.695f, 2.45f, 8.035f)
                    curveTo(2.528f, 8.376f, 2.644f, 8.701f, 2.8f, 9.012f)
                    curveTo(2.868f, 9.139f, 2.905f, 9.273f, 2.912f, 9.414f)
                    curveTo(2.919f, 9.555f, 2.901f, 9.693f, 2.858f, 9.829f)
                    lineTo(2.304f, 11.696f)
                    close()
                    moveTo(6.562f, 7.438f)
                    verticalLineTo(8.75f)
                    curveTo(6.562f, 8.874f, 6.604f, 8.978f, 6.688f, 9.062f)
                    curveTo(6.772f, 9.146f, 6.876f, 9.188f, 7f, 9.188f)
                    curveTo(7.124f, 9.188f, 7.228f, 9.146f, 7.312f, 9.062f)
                    curveTo(7.396f, 8.978f, 7.437f, 8.874f, 7.437f, 8.75f)
                    verticalLineTo(7.438f)
                    horizontalLineTo(8.75f)
                    curveTo(8.874f, 7.438f, 8.978f, 7.396f, 9.062f, 7.312f)
                    curveTo(9.146f, 7.228f, 9.187f, 7.124f, 9.187f, 7f)
                    curveTo(9.187f, 6.876f, 9.146f, 6.772f, 9.062f, 6.688f)
                    curveTo(8.978f, 6.604f, 8.874f, 6.563f, 8.75f, 6.563f)
                    horizontalLineTo(7.437f)
                    verticalLineTo(5.25f)
                    curveTo(7.437f, 5.126f, 7.396f, 5.022f, 7.312f, 4.938f)
                    curveTo(7.228f, 4.854f, 7.124f, 4.813f, 7f, 4.813f)
                    curveTo(6.876f, 4.813f, 6.772f, 4.854f, 6.688f, 4.938f)
                    curveTo(6.604f, 5.022f, 6.562f, 5.126f, 6.562f, 5.25f)
                    verticalLineTo(6.563f)
                    horizontalLineTo(5.25f)
                    curveTo(5.126f, 6.563f, 5.022f, 6.604f, 4.938f, 6.688f)
                    curveTo(4.854f, 6.772f, 4.812f, 6.876f, 4.812f, 7f)
                    curveTo(4.812f, 7.124f, 4.854f, 7.228f, 4.938f, 7.312f)
                    curveTo(5.022f, 7.396f, 5.126f, 7.438f, 5.25f, 7.438f)
                    horizontalLineTo(6.562f)
                    close()
                }
            }
        }.build()

        return _ChatBubble!!
    }

@Suppress("ObjectPropertyName")
private var _ChatBubble: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun ChatBubblePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.ChatBubble, contentDescription = null)
    }
}
