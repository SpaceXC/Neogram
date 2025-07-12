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

val NeogramIcons.Study: ImageVector
    get() {
        if (_Study != null) {
            return _Study!!
        }
        _Study = ImageVector.Builder(
            name = "Study",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(11.627f, 5.032f)
                curveTo(11.896f, 4.978f, 12.151f, 4.997f, 12.373f, 5.056f)
                lineTo(12.481f, 5.089f)
                lineTo(19.701f, 7.554f)
                curveTo(21.214f, 8.071f, 21.181f, 10.222f, 19.653f, 10.693f)
                lineTo(18f, 11.202f)
                verticalLineTo(14.986f)
                curveTo(18f, 16.228f, 17.178f, 17.32f, 15.984f, 17.664f)
                curveTo(13.465f, 18.388f, 10.798f, 18.428f, 8.258f, 17.78f)
                lineTo(7.72f, 17.642f)
                curveTo(6.414f, 17.309f, 5.5f, 16.132f, 5.5f, 14.785f)
                verticalLineTo(11.077f)
                lineTo(4.5f, 10.769f)
                verticalLineTo(15.494f)
                curveTo(4.5f, 15.908f, 4.164f, 16.244f, 3.75f, 16.244f)
                curveTo(3.336f, 16.244f, 3f, 15.908f, 3f, 15.494f)
                verticalLineTo(8.994f)
                curveTo(3f, 8.922f, 3.013f, 8.853f, 3.032f, 8.787f)
                curveTo(3.114f, 8.298f, 3.422f, 7.842f, 4f, 7.589f)
                lineTo(4.469f, 7.393f)
                curveTo(6.93f, 6.395f, 11.067f, 5.189f, 11.492f, 5.065f)
                lineTo(11.627f, 5.032f)
                close()
                moveTo(7f, 14.785f)
                curveTo(7f, 15.447f, 7.449f, 16.025f, 8.091f, 16.189f)
                lineTo(8.629f, 16.327f)
                curveTo(10.91f, 16.909f, 13.306f, 16.872f, 15.569f, 16.221f)
                curveTo(16.12f, 16.063f, 16.5f, 15.559f, 16.5f, 14.986f)
                verticalLineTo(11.664f)
                lineTo(12.438f, 12.915f)
                curveTo(12.121f, 13.012f, 11.783f, 13.012f, 11.467f, 12.915f)
                lineTo(7f, 11.539f)
                verticalLineTo(14.785f)
                close()
                moveTo(11.91f, 6.505f)
                curveTo(11.425f, 6.646f, 6.853f, 7.983f, 4.6f, 8.964f)
                curveTo(4.474f, 9.019f, 4.488f, 9.197f, 4.619f, 9.237f)
                lineTo(11.908f, 11.482f)
                curveTo(11.922f, 11.486f, 11.937f, 11.488f, 11.952f, 11.488f)
                lineTo(11.996f, 11.482f)
                lineTo(19.212f, 9.259f)
                curveTo(19.333f, 9.222f, 19.351f, 9.067f, 19.262f, 8.998f)
                lineTo(19.217f, 8.974f)
                lineTo(11.997f, 6.509f)
                curveTo(11.968f, 6.499f, 11.94f, 6.497f, 11.91f, 6.505f)
                close()
            }
        }.build()

        return _Study!!
    }

@Suppress("ObjectPropertyName")
private var _Study: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun StudyPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Study, contentDescription = null)
    }
}
