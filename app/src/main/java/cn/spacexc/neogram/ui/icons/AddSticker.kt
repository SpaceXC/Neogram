package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.AddSticker: ImageVector
    get() {
        if (_AddSticker != null) {
            return _AddSticker!!
        }
        _AddSticker = ImageVector.Builder(
            name = "AddSticker",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(10f, 9f)
                verticalLineTo(10f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9f, 13f)
                curveTo(9f, 13f, 10.2f, 14f, 12.2f, 14f)
                curveTo(14.2f, 14f, 15f, 13f, 15f, 13f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(14f, 9f)
                verticalLineTo(10f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(14.2f, 4.25f)
                curveTo(15.028f, 4.25f, 15.694f, 4.249f, 16.231f, 4.293f)
                curveTo(16.778f, 4.338f, 17.258f, 4.433f, 17.702f, 4.659f)
                curveTo(18.408f, 5.019f, 18.981f, 5.592f, 19.341f, 6.298f)
                curveTo(19.567f, 6.742f, 19.662f, 7.222f, 19.707f, 7.769f)
                curveTo(19.751f, 8.306f, 19.75f, 8.972f, 19.75f, 9.8f)
                verticalLineTo(13.5f)
                curveTo(19.75f, 15.295f, 18.295f, 16.75f, 16.5f, 16.75f)
                horizontalLineTo(15.5f)
                curveTo(15.086f, 16.75f, 14.75f, 17.086f, 14.75f, 17.5f)
                curveTo(14.75f, 18.743f, 13.743f, 19.75f, 12.5f, 19.75f)
                horizontalLineTo(9.5f)
                curveTo(8.811f, 19.75f, 8.257f, 19.75f, 7.808f, 19.72f)
                curveTo(7.351f, 19.689f, 6.947f, 19.623f, 6.564f, 19.465f)
                curveTo(5.646f, 19.084f, 4.916f, 18.354f, 4.535f, 17.435f)
                curveTo(4.377f, 17.053f, 4.311f, 16.649f, 4.28f, 16.192f)
                curveTo(4.25f, 15.743f, 4.25f, 15.189f, 4.25f, 14.5f)
                verticalLineTo(9.8f)
                curveTo(4.25f, 8.972f, 4.249f, 8.306f, 4.293f, 7.769f)
                curveTo(4.338f, 7.222f, 4.433f, 6.742f, 4.659f, 6.298f)
                curveTo(5.019f, 5.592f, 5.592f, 5.019f, 6.298f, 4.659f)
                curveTo(6.742f, 4.433f, 7.222f, 4.338f, 7.769f, 4.293f)
                curveTo(8.306f, 4.249f, 8.972f, 4.25f, 9.8f, 4.25f)
                horizontalLineTo(14.2f)
                close()
            }
        }.build()

        return _AddSticker!!
    }

@Suppress("ObjectPropertyName")
private var _AddSticker: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AddStickerPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.AddSticker, contentDescription = null)
    }
}
