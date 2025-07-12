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

val NeogramIcons.Laptop: ImageVector
    get() {
        if (_Laptop != null) {
            return _Laptop!!
        }
        _Laptop = ImageVector.Builder(
            name = "Laptop",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(15.8f, 4f)
                horizontalLineTo(8.2f)
                lineTo(8.148f, 4f)
                curveTo(7.633f, 4f, 7.16f, 4f, 6.764f, 4.032f)
                curveTo(6.338f, 4.067f, 5.872f, 4.147f, 5.411f, 4.381f)
                curveTo(4.752f, 4.717f, 4.217f, 5.252f, 3.881f, 5.911f)
                curveTo(3.647f, 6.372f, 3.567f, 6.838f, 3.532f, 7.264f)
                curveTo(3.5f, 7.66f, 3.5f, 8.133f, 3.5f, 8.648f)
                lineTo(3.5f, 8.7f)
                verticalLineTo(12.3f)
                lineTo(3.5f, 12.352f)
                curveTo(3.5f, 12.867f, 3.5f, 13.34f, 3.532f, 13.736f)
                curveTo(3.567f, 14.162f, 3.647f, 14.628f, 3.881f, 15.089f)
                curveTo(4.217f, 15.748f, 4.752f, 16.283f, 5.411f, 16.618f)
                curveTo(5.872f, 16.853f, 6.338f, 16.933f, 6.764f, 16.968f)
                curveTo(7.16f, 17f, 7.633f, 17f, 8.148f, 17f)
                horizontalLineTo(8.148f)
                lineTo(8.2f, 17f)
                horizontalLineTo(15.8f)
                lineTo(15.852f, 17f)
                horizontalLineTo(15.852f)
                curveTo(16.367f, 17f, 16.84f, 17f, 17.236f, 16.968f)
                curveTo(17.662f, 16.933f, 18.128f, 16.853f, 18.589f, 16.618f)
                curveTo(19.247f, 16.283f, 19.783f, 15.748f, 20.118f, 15.089f)
                curveTo(20.353f, 14.628f, 20.433f, 14.162f, 20.468f, 13.736f)
                curveTo(20.5f, 13.34f, 20.5f, 12.867f, 20.5f, 12.352f)
                verticalLineTo(12.352f)
                lineTo(20.5f, 12.3f)
                verticalLineTo(8.7f)
                lineTo(20.5f, 8.648f)
                verticalLineTo(8.648f)
                curveTo(20.5f, 8.133f, 20.5f, 7.66f, 20.468f, 7.264f)
                curveTo(20.433f, 6.838f, 20.353f, 6.372f, 20.118f, 5.911f)
                curveTo(19.783f, 5.252f, 19.247f, 4.717f, 18.589f, 4.381f)
                curveTo(18.128f, 4.147f, 17.662f, 4.067f, 17.236f, 4.032f)
                curveTo(16.84f, 4f, 16.367f, 4f, 15.852f, 4f)
                lineTo(15.8f, 4f)
                close()
                moveTo(5f, 8.7f)
                curveTo(5f, 7.58f, 5f, 7.02f, 5.218f, 6.592f)
                curveTo(5.41f, 6.216f, 5.716f, 5.91f, 6.092f, 5.718f)
                curveTo(6.52f, 5.5f, 7.08f, 5.5f, 8.2f, 5.5f)
                horizontalLineTo(15.8f)
                curveTo(16.92f, 5.5f, 17.48f, 5.5f, 17.908f, 5.718f)
                curveTo(18.284f, 5.91f, 18.59f, 6.216f, 18.782f, 6.592f)
                curveTo(19f, 7.02f, 19f, 7.58f, 19f, 8.7f)
                verticalLineTo(12.3f)
                curveTo(19f, 13.42f, 19f, 13.98f, 18.782f, 14.408f)
                curveTo(18.59f, 14.784f, 18.284f, 15.09f, 17.908f, 15.282f)
                curveTo(17.48f, 15.5f, 16.92f, 15.5f, 15.8f, 15.5f)
                horizontalLineTo(8.2f)
                curveTo(7.08f, 15.5f, 6.52f, 15.5f, 6.092f, 15.282f)
                curveTo(5.716f, 15.09f, 5.41f, 14.784f, 5.218f, 14.408f)
                curveTo(5f, 13.98f, 5f, 13.42f, 5f, 12.3f)
                verticalLineTo(8.7f)
                close()
                moveTo(2.75f, 18f)
                curveTo(2.336f, 18f, 2f, 18.336f, 2f, 18.75f)
                curveTo(2f, 19.164f, 2.336f, 19.5f, 2.75f, 19.5f)
                horizontalLineTo(21.25f)
                curveTo(21.664f, 19.5f, 22f, 19.164f, 22f, 18.75f)
                curveTo(22f, 18.336f, 21.664f, 18f, 21.25f, 18f)
                horizontalLineTo(2.75f)
                close()
            }
        }.build()

        return _Laptop!!
    }

@Suppress("ObjectPropertyName")
private var _Laptop: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun LaptopPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Laptop, contentDescription = null)
    }
}
