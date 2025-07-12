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

val NeogramIcons.Party: ImageVector
    get() {
        if (_Party != null) {
            return _Party!!
        }
        _Party = ImageVector.Builder(
            name = "Party",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(6.316f, 18.865f)
                curveTo(5.323f, 19.282f, 4.294f, 18.302f, 4.662f, 17.29f)
                lineTo(4.704f, 17.188f)
                lineTo(8.501f, 8.88f)
                curveTo(8.975f, 7.844f, 10.4f, 7.955f, 10.819f, 8.914f)
                curveTo(11.279f, 9.966f, 11.723f, 10.693f, 12.222f, 11.218f)
                curveTo(12.709f, 11.732f, 13.278f, 12.083f, 14.04f, 12.337f)
                curveTo(14.58f, 12.518f, 14.88f, 12.993f, 14.938f, 13.445f)
                curveTo(14.998f, 13.905f, 14.818f, 14.467f, 14.271f, 14.75f)
                lineTo(6.415f, 18.818f)
                lineTo(6.316f, 18.865f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12.463f, 7f)
                curveTo(12.498f, 5.629f, 12.324f, 4.953f, 11.569f, 4f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(19.569f, 10.449f)
                curveTo(18.497f, 9.854f, 17.654f, 9.847f, 16.569f, 10.449f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(14.569f, 8.5f)
                curveTo(15.635f, 6.606f, 16.324f, 6.255f, 18.569f, 6f)
            }
        }.build()

        return _Party!!
    }

@Suppress("ObjectPropertyName")
private var _Party: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun PartyPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Party, contentDescription = null)
    }
}
