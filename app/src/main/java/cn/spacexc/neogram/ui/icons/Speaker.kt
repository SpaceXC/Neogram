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

val NeogramIcons.Speaker: ImageVector
    get() {
        if (_Speaker != null) {
            return _Speaker!!
        }
        _Speaker = ImageVector.Builder(
            name = "Speaker",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 22f,
            viewportHeight = 22f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(11f, 15.72f)
                verticalLineTo(6.417f)
                curveTo(11f, 5.536f, 9.879f, 5.162f, 9.35f, 5.867f)
                lineTo(7.59f, 8.214f)
                curveTo(7.427f, 8.431f, 7.177f, 8.564f, 6.907f, 8.579f)
                lineTo(5.449f, 8.66f)
                curveTo(4.963f, 8.687f, 4.583f, 9.089f, 4.583f, 9.575f)
                verticalLineTo(12.833f)
                curveTo(4.583f, 13.34f, 4.994f, 13.75f, 5.5f, 13.75f)
                horizontalLineTo(6.893f)
                curveTo(7.171f, 13.75f, 7.435f, 13.877f, 7.609f, 14.094f)
                lineTo(9.368f, 16.293f)
                curveTo(9.909f, 16.969f, 11f, 16.587f, 11f, 15.72f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(15.583f, 6.417f)
                curveTo(16.135f, 6.951f, 16.588f, 7.661f, 16.906f, 8.489f)
                curveTo(17.225f, 9.317f, 17.399f, 10.239f, 17.415f, 11.179f)
                curveTo(17.432f, 12.12f, 17.289f, 13.053f, 17f, 13.901f)
                curveTo(16.71f, 14.75f, 16.115f, 15.929f, 15.583f, 16.5f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.375f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(13.75f, 9.167f)
                curveTo(14.322f, 9.868f, 14.635f, 10.648f, 14.664f, 11.445f)
                curveTo(14.694f, 12.242f, 14.438f, 13.031f, 13.918f, 13.75f)
            }
        }.build()

        return _Speaker!!
    }

@Suppress("ObjectPropertyName")
private var _Speaker: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun SpeakerPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Speaker, contentDescription = null)
    }
}
