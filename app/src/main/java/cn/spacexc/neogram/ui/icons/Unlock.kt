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

val NeogramIcons.Unlock: ImageVector
    get() {
        if (_Unlock != null) {
            return _Unlock!!
        }
        _Unlock = ImageVector.Builder(
            name = "Unlock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(16.349f, 4f)
                curveTo(13.938f, 4f, 11.983f, 5.955f, 11.983f, 8.366f)
                verticalLineTo(9.579f)
                horizontalLineTo(9.196f)
                lineTo(9.147f, 9.579f)
                curveTo(8.585f, 9.579f, 8.084f, 9.579f, 7.667f, 9.609f)
                curveTo(7.224f, 9.642f, 6.758f, 9.715f, 6.295f, 9.924f)
                curveTo(5.43f, 10.315f, 4.736f, 11.008f, 4.346f, 11.874f)
                curveTo(4.137f, 12.337f, 4.063f, 12.802f, 4.031f, 13.246f)
                curveTo(4f, 13.663f, 4f, 14.163f, 4f, 14.725f)
                verticalLineTo(14.725f)
                lineTo(4f, 14.775f)
                verticalLineTo(14.977f)
                lineTo(4f, 15.027f)
                verticalLineTo(15.027f)
                curveTo(4f, 15.589f, 4f, 16.089f, 4.031f, 16.506f)
                curveTo(4.063f, 16.95f, 4.137f, 17.416f, 4.346f, 17.878f)
                curveTo(4.736f, 18.744f, 5.43f, 19.437f, 6.295f, 19.828f)
                curveTo(6.758f, 20.037f, 7.224f, 20.11f, 7.667f, 20.143f)
                curveTo(8.084f, 20.173f, 8.585f, 20.173f, 9.147f, 20.173f)
                lineTo(9.196f, 20.173f)
                horizontalLineTo(12.653f)
                lineTo(12.703f, 20.173f)
                curveTo(13.265f, 20.173f, 13.765f, 20.173f, 14.182f, 20.143f)
                curveTo(14.626f, 20.11f, 15.092f, 20.037f, 15.554f, 19.828f)
                curveTo(16.42f, 19.437f, 17.113f, 18.744f, 17.504f, 17.878f)
                curveTo(17.713f, 17.416f, 17.786f, 16.95f, 17.819f, 16.506f)
                curveTo(17.849f, 16.089f, 17.849f, 15.589f, 17.849f, 15.027f)
                lineTo(17.849f, 14.977f)
                verticalLineTo(14.775f)
                lineTo(17.849f, 14.725f)
                curveTo(17.849f, 14.163f, 17.849f, 13.663f, 17.819f, 13.246f)
                curveTo(17.786f, 12.802f, 17.713f, 12.337f, 17.504f, 11.874f)
                curveTo(17.113f, 11.008f, 16.42f, 10.315f, 15.554f, 9.924f)
                curveTo(15.092f, 9.715f, 14.626f, 9.642f, 14.182f, 9.609f)
                curveTo(13.97f, 9.594f, 13.736f, 9.586f, 13.483f, 9.582f)
                verticalLineTo(8.366f)
                curveTo(13.483f, 6.783f, 14.766f, 5.5f, 16.349f, 5.5f)
                curveTo(17.932f, 5.5f, 19.216f, 6.783f, 19.216f, 8.366f)
                verticalLineTo(10.175f)
                curveTo(19.216f, 10.589f, 19.552f, 10.925f, 19.966f, 10.925f)
                curveTo(20.38f, 10.925f, 20.716f, 10.589f, 20.716f, 10.175f)
                verticalLineTo(8.366f)
                curveTo(20.716f, 5.955f, 18.761f, 4f, 16.349f, 4f)
                close()
                moveTo(5.5f, 14.775f)
                curveTo(5.5f, 13.566f, 5.5f, 12.962f, 5.713f, 12.491f)
                curveTo(5.953f, 11.958f, 6.38f, 11.532f, 6.912f, 11.291f)
                curveTo(7.383f, 11.079f, 7.988f, 11.079f, 9.196f, 11.079f)
                horizontalLineTo(12.653f)
                curveTo(13.862f, 11.079f, 14.466f, 11.079f, 14.937f, 11.291f)
                curveTo(15.47f, 11.532f, 15.896f, 11.958f, 16.137f, 12.491f)
                curveTo(16.349f, 12.962f, 16.349f, 13.566f, 16.349f, 14.775f)
                verticalLineTo(14.977f)
                curveTo(16.349f, 16.186f, 16.349f, 16.79f, 16.137f, 17.261f)
                curveTo(15.896f, 17.794f, 15.47f, 18.22f, 14.937f, 18.461f)
                curveTo(14.466f, 18.673f, 13.862f, 18.673f, 12.653f, 18.673f)
                horizontalLineTo(9.196f)
                curveTo(7.988f, 18.673f, 7.383f, 18.673f, 6.912f, 18.461f)
                curveTo(6.38f, 18.22f, 5.953f, 17.794f, 5.713f, 17.261f)
                curveTo(5.5f, 16.79f, 5.5f, 16.186f, 5.5f, 14.977f)
                verticalLineTo(14.775f)
                close()
            }
        }.build()

        return _Unlock!!
    }

@Suppress("ObjectPropertyName")
private var _Unlock: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun UnlockPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Unlock, contentDescription = null)
    }
}
