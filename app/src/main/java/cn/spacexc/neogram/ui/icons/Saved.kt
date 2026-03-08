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

val NeogramIcons.Saved: ImageVector
    get() {
        if (_Saved != null) {
            return _Saved!!
        }
        _Saved = ImageVector.Builder(
            name = "Saved",
            defaultWidth = 22.dp,
            defaultHeight = 23.dp,
            viewportWidth = 22f,
            viewportHeight = 23f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(7.333f, 4.925f)
                curveTo(6.574f, 4.925f, 5.958f, 5.541f, 5.958f, 6.3f)
                verticalLineTo(16.841f)
                lineTo(10.597f, 11.956f)
                curveTo(10.959f, 11.575f, 11.566f, 11.575f, 11.927f, 11.956f)
                lineTo(15.651f, 16.842f)
                verticalLineTo(6.3f)
                curveTo(15.651f, 5.541f, 15.035f, 4.925f, 14.276f, 4.925f)
                horizontalLineTo(7.333f)
                close()
                moveTo(17.025f, 18.208f)
                curveTo(17.025f, 19.09f, 15.902f, 19.463f, 15.374f, 18.757f)
                lineTo(11.262f, 13.252f)
                lineTo(6.165f, 18.62f)
                curveTo(5.594f, 19.221f, 4.583f, 18.817f, 4.583f, 17.989f)
                verticalLineTo(6.3f)
                curveTo(4.583f, 4.781f, 5.815f, 3.55f, 7.333f, 3.55f)
                horizontalLineTo(14.276f)
                curveTo(15.794f, 3.55f, 17.025f, 4.781f, 17.025f, 6.3f)
                verticalLineTo(18.208f)
                close()
            }
        }.build()

        return _Saved!!
    }

@Suppress("ObjectPropertyName")
private var _Saved: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun SavedPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Saved, contentDescription = null)
    }
}
