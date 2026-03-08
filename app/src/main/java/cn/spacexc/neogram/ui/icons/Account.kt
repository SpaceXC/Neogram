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

val NeogramIcons.Account: ImageVector
    get() {
        if (_Account != null) {
            return _Account!!
        }
        _Account = ImageVector.Builder(
            name = "Account",
            defaultWidth = 24.dp,
            defaultHeight = 25.dp,
            viewportWidth = 24f,
            viewportHeight = 25f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(12.2f, 4.4f)
                lineTo(12.2f, 4.4f)
                arcTo(3.2f, 3.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 15.4f, 7.6f)
                lineTo(15.4f, 7.6f)
                arcTo(3.2f, 3.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12.2f, 10.8f)
                lineTo(12.2f, 10.8f)
                arcTo(3.2f, 3.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 7.6f)
                lineTo(9f, 7.6f)
                arcTo(3.2f, 3.2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12.2f, 4.4f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(14.833f, 13.65f)
                curveTo(17.548f, 13.65f, 19.75f, 15.852f, 19.75f, 18.567f)
                curveTo(19.75f, 19.441f, 19.041f, 20.15f, 18.167f, 20.15f)
                horizontalLineTo(5.833f)
                curveTo(4.959f, 20.15f, 4.25f, 19.441f, 4.25f, 18.567f)
                curveTo(4.25f, 15.852f, 6.452f, 13.65f, 9.167f, 13.65f)
                horizontalLineTo(14.833f)
                close()
            }
        }.build()

        return _Account!!
    }

@Suppress("ObjectPropertyName")
private var _Account: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun AccountPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Account, contentDescription = null)
    }
}
