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

val NeogramIcons.VoiceDisable: ImageVector
    get() {
        if (_VoiceDisable != null) {
            return _VoiceDisable!!
        }
        _VoiceDisable = ImageVector.Builder(
            name = "VoiceDisable",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(5.704f, 12.311f)
                curveTo(6.084f, 12.148f, 6.526f, 12.323f, 6.689f, 12.703f)
                lineTo(6.69f, 12.706f)
                curveTo(6.692f, 12.71f, 6.695f, 12.719f, 6.7f, 12.729f)
                curveTo(6.71f, 12.752f, 6.728f, 12.787f, 6.752f, 12.834f)
                curveTo(6.799f, 12.928f, 6.873f, 13.067f, 6.976f, 13.235f)
                curveTo(7.182f, 13.575f, 7.498f, 14.028f, 7.933f, 14.479f)
                curveTo(8.799f, 15.378f, 10.117f, 16.25f, 12f, 16.25f)
                curveTo(12.681f, 16.25f, 13.287f, 16.134f, 13.825f, 15.946f)
                lineTo(14.972f, 17.093f)
                curveTo(14.325f, 17.396f, 13.587f, 17.624f, 12.75f, 17.709f)
                verticalLineTo(20f)
                curveTo(12.75f, 20.414f, 12.414f, 20.75f, 12f, 20.75f)
                curveTo(11.586f, 20.75f, 11.25f, 20.414f, 11.25f, 20f)
                verticalLineTo(17.709f)
                curveTo(9.243f, 17.504f, 7.8f, 16.503f, 6.852f, 15.521f)
                curveTo(6.324f, 14.972f, 5.943f, 14.425f, 5.693f, 14.015f)
                curveTo(5.568f, 13.809f, 5.475f, 13.634f, 5.412f, 13.51f)
                curveTo(5.381f, 13.448f, 5.357f, 13.397f, 5.34f, 13.36f)
                curveTo(5.331f, 13.342f, 5.325f, 13.327f, 5.32f, 13.316f)
                curveTo(5.318f, 13.311f, 5.316f, 13.307f, 5.314f, 13.304f)
                lineTo(5.311f, 13.299f)
                verticalLineTo(13.297f)
                lineTo(5.31f, 13.296f)
                lineTo(5.284f, 13.224f)
                curveTo(5.17f, 12.861f, 5.347f, 12.464f, 5.704f, 12.311f)
                close()
                moveTo(17.312f, 12.703f)
                curveTo(17.475f, 12.323f, 17.916f, 12.148f, 18.296f, 12.311f)
                curveTo(18.653f, 12.464f, 18.83f, 12.861f, 18.716f, 13.224f)
                lineTo(18.689f, 13.296f)
                lineTo(18.688f, 13.299f)
                lineTo(18.685f, 13.304f)
                curveTo(18.684f, 13.307f, 18.682f, 13.311f, 18.68f, 13.316f)
                curveTo(18.675f, 13.327f, 18.669f, 13.342f, 18.66f, 13.36f)
                curveTo(18.643f, 13.397f, 18.619f, 13.448f, 18.588f, 13.51f)
                curveTo(18.525f, 13.635f, 18.432f, 13.809f, 18.307f, 14.015f)
                curveTo(18.096f, 14.361f, 17.791f, 14.803f, 17.384f, 15.263f)
                lineTo(16.319f, 14.198f)
                curveTo(16.626f, 13.843f, 16.861f, 13.505f, 17.024f, 13.235f)
                curveTo(17.127f, 13.067f, 17.201f, 12.928f, 17.248f, 12.834f)
                curveTo(17.272f, 12.787f, 17.289f, 12.752f, 17.3f, 12.729f)
                curveTo(17.305f, 12.719f, 17.308f, 12.71f, 17.309f, 12.706f)
                lineTo(17.312f, 12.703f)
                close()
                moveTo(12.359f, 14.481f)
                curveTo(12.241f, 14.493f, 12.121f, 14.5f, 12f, 14.5f)
                curveTo(10.067f, 14.5f, 8.5f, 12.933f, 8.5f, 11f)
                verticalLineTo(10.621f)
                lineTo(12.359f, 14.481f)
                close()
                moveTo(12f, 3.5f)
                curveTo(13.933f, 3.5f, 15.5f, 5.067f, 15.5f, 7f)
                verticalLineTo(11f)
                curveTo(15.5f, 11.679f, 15.306f, 12.313f, 14.971f, 12.85f)
                lineTo(13.858f, 11.737f)
                curveTo(13.925f, 11.569f, 13.97f, 11.391f, 13.989f, 11.204f)
                lineTo(14f, 11f)
                verticalLineTo(7f)
                curveTo(14f, 5.895f, 13.104f, 5f, 12f, 5f)
                curveTo(10.895f, 5f, 10f, 5.895f, 10f, 7f)
                verticalLineTo(7.879f)
                lineTo(8.547f, 6.426f)
                curveTo(8.821f, 4.766f, 10.262f, 3.5f, 12f, 3.5f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(5f, 5f)
                lineTo(19f, 19f)
            }
        }.build()

        return _VoiceDisable!!
    }

@Suppress("ObjectPropertyName")
private var _VoiceDisable: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun VoiceDisablePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.VoiceDisable, contentDescription = null)
    }
}
