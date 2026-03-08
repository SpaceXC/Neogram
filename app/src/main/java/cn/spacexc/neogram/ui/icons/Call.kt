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

val NeogramIcons.Call: ImageVector
    get() {
        if (_Call != null) {
            return _Call!!
        }
        _Call = ImageVector.Builder(
            name = "Call",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 20f,
            viewportHeight = 20f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.29556f
            ) {
                moveTo(3.711f, 4.406f)
                curveTo(3.975f, 4.033f, 4.707f, 3.561f, 5.238f, 3.249f)
                curveTo(5.583f, 3.046f, 6.018f, 3.13f, 6.28f, 3.433f)
                lineTo(8.084f, 5.519f)
                curveTo(8.313f, 5.783f, 8.36f, 6.158f, 8.204f, 6.47f)
                lineTo(7.429f, 8.019f)
                curveTo(7.27f, 8.337f, 7.322f, 8.72f, 7.56f, 8.984f)
                lineTo(10.464f, 12.21f)
                curveTo(10.787f, 12.569f, 11.343f, 12.593f, 11.72f, 12.291f)
                curveTo(12.045f, 12.029f, 12.439f, 11.741f, 12.78f, 11.569f)
                curveTo(13.52f, 11.197f, 13.891f, 11.137f, 14.631f, 11.569f)
                curveTo(15.371f, 12.001f, 16.667f, 13.296f, 16.667f, 14.413f)
                curveTo(16.667f, 15.53f, 15.425f, 15.887f, 14.066f, 16.141f)
                curveTo(12.707f, 16.394f, 10.881f, 15.411f, 9.524f, 14.413f)
                curveTo(8.167f, 13.415f, 4.282f, 9.171f, 3.711f, 7.742f)
                curveTo(3.14f, 6.312f, 3.279f, 5.017f, 3.711f, 4.406f)
                close()
            }
        }.build()

        return _Call!!
    }

@Suppress("ObjectPropertyName")
private var _Call: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun CallPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Call, contentDescription = null)
    }
}
