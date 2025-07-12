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

val NeogramIcons.Gram: ImageVector
    get() {
        if (_Gram != null) {
            return _Gram!!
        }
        _Gram = ImageVector.Builder(
            name = "Gram",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(17.504f, 5.381f)
                curveTo(18.715f, 4.86f, 19.923f, 5.925f, 19.729f, 7.153f)
                lineTo(18.115f, 17.368f)
                curveTo(17.938f, 18.49f, 16.688f, 19.129f, 15.684f, 18.513f)
                lineTo(11.007f, 15.64f)
                curveTo(10.102f, 15.084f, 9.964f, 13.818f, 10.704f, 13.07f)
                lineTo(10.765f, 13.008f)
                lineTo(9.815f, 13.651f)
                curveTo(9.199f, 14.068f, 8.43f, 14.184f, 7.72f, 13.963f)
                lineTo(5.146f, 13.16f)
                curveTo(4.007f, 12.805f, 3.941f, 11.215f, 5.022f, 10.749f)
                lineTo(17.504f, 5.381f)
                close()
            }
        }.build()

        return _Gram!!
    }

@Suppress("ObjectPropertyName")
private var _Gram: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun GramPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Gram, contentDescription = null)
    }
}
