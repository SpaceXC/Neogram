package cn.spacexc.neogram.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val NeogramIcons.Delete: ImageVector
    get() {
        if (_Delete != null) {
            return _Delete!!
        }
        _Delete = ImageVector.Builder(
            name = "Delete",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(4.699f, 6.375f)
                curveTo(4.313f, 6.375f, 4f, 6.688f, 4f, 7.074f)
                curveTo(4f, 7.46f, 4.313f, 7.773f, 4.699f, 7.773f)
                horizontalLineTo(5.643f)
                lineTo(5.687f, 8.265f)
                lineTo(6.514f, 17.529f)
                lineTo(6.517f, 17.562f)
                curveTo(6.535f, 17.765f, 6.554f, 17.982f, 6.584f, 18.17f)
                curveTo(6.619f, 18.383f, 6.68f, 18.655f, 6.829f, 18.935f)
                curveTo(7.088f, 19.421f, 7.505f, 19.803f, 8.012f, 20.017f)
                curveTo(8.305f, 20.141f, 8.581f, 20.178f, 8.796f, 20.193f)
                curveTo(8.986f, 20.206f, 9.204f, 20.206f, 9.407f, 20.206f)
                lineTo(9.44f, 20.206f)
                horizontalLineTo(14.24f)
                lineTo(14.273f, 20.206f)
                curveTo(14.476f, 20.206f, 14.695f, 20.206f, 14.885f, 20.193f)
                curveTo(15.1f, 20.178f, 15.376f, 20.141f, 15.668f, 20.017f)
                curveTo(16.175f, 19.803f, 16.593f, 19.421f, 16.851f, 18.935f)
                curveTo(17f, 18.655f, 17.062f, 18.383f, 17.096f, 18.17f)
                curveTo(17.126f, 17.982f, 17.146f, 17.765f, 17.164f, 17.562f)
                lineTo(17.167f, 17.529f)
                lineTo(17.994f, 8.265f)
                lineTo(18.038f, 7.773f)
                horizontalLineTo(18.982f)
                curveTo(19.368f, 7.773f, 19.681f, 7.46f, 19.681f, 7.074f)
                curveTo(19.681f, 6.688f, 19.368f, 6.375f, 18.982f, 6.375f)
                horizontalLineTo(4.699f)
                close()
                moveTo(7.079f, 8.141f)
                horizontalLineTo(8.483f)
                horizontalLineTo(15.197f)
                horizontalLineTo(16.601f)
                lineTo(16.476f, 9.539f)
                lineTo(15.774f, 17.405f)
                curveTo(15.733f, 17.868f, 15.712f, 18.099f, 15.617f, 18.278f)
                curveTo(15.509f, 18.481f, 15.335f, 18.64f, 15.124f, 18.729f)
                curveTo(14.937f, 18.808f, 14.705f, 18.808f, 14.24f, 18.808f)
                horizontalLineTo(9.44f)
                curveTo(8.976f, 18.808f, 8.744f, 18.808f, 8.557f, 18.729f)
                curveTo(8.346f, 18.64f, 8.171f, 18.481f, 8.064f, 18.278f)
                curveTo(7.968f, 18.099f, 7.948f, 17.868f, 7.906f, 17.405f)
                lineTo(7.204f, 9.539f)
                lineTo(7.079f, 8.141f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(9.871f, 4f)
                horizontalLineTo(13.871f)
            }
        }.build()

        return _Delete!!
    }

@Suppress("ObjectPropertyName")
private var _Delete: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun DeletePreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Delete, contentDescription = null)
    }
}
