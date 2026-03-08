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

val NeogramIcons.Unread: ImageVector
    get() {
        if (_Unread != null) {
            return _Unread!!
        }
        _Unread = ImageVector.Builder(
            name = "Unread",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 16.551f)
                curveTo(15.779f, 16.551f, 18.5f, 13.9f, 18.5f, 11.026f)
                curveTo(18.5f, 8.151f, 15.779f, 5.5f, 12f, 5.5f)
                curveTo(8.221f, 5.5f, 5.5f, 8.151f, 5.5f, 11.026f)
                curveTo(5.5f, 11.553f, 5.587f, 12.061f, 5.749f, 12.543f)
                curveTo(5.938f, 13.104f, 6.232f, 13.637f, 6.619f, 14.121f)
                curveTo(7.017f, 14.619f, 7.03f, 15.179f, 7.02f, 15.432f)
                curveTo(7.009f, 15.726f, 6.948f, 16.01f, 6.894f, 16.224f)
                curveTo(6.837f, 16.448f, 6.765f, 16.683f, 6.702f, 16.887f)
                curveTo(6.695f, 16.911f, 6.688f, 16.934f, 6.68f, 16.958f)
                curveTo(6.627f, 17.131f, 6.578f, 17.288f, 6.534f, 17.444f)
                curveTo(6.602f, 17.421f, 6.673f, 17.395f, 6.747f, 17.365f)
                curveTo(7.253f, 17.164f, 7.755f, 16.866f, 8.096f, 16.644f)
                curveTo(8.706f, 16.246f, 9.46f, 16.11f, 10.179f, 16.286f)
                curveTo(10.677f, 16.408f, 11.406f, 16.551f, 12f, 16.551f)
                close()
                moveTo(12f, 18.051f)
                curveTo(16.418f, 18.051f, 20f, 14.906f, 20f, 11.026f)
                curveTo(20f, 7.145f, 16.418f, 4f, 12f, 4f)
                curveTo(7.582f, 4f, 4f, 7.145f, 4f, 11.026f)
                curveTo(4f, 11.719f, 4.114f, 12.389f, 4.328f, 13.022f)
                curveTo(4.575f, 13.758f, 4.957f, 14.444f, 5.447f, 15.057f)
                curveTo(5.628f, 15.283f, 5.448f, 15.864f, 5.247f, 16.515f)
                curveTo(4.931f, 17.537f, 4.562f, 18.732f, 5.447f, 19f)
                curveTo(6.529f, 19.327f, 8.158f, 18.395f, 8.917f, 17.9f)
                curveTo(9.183f, 17.725f, 9.513f, 17.667f, 9.822f, 17.743f)
                curveTo(10.351f, 17.873f, 11.221f, 18.051f, 12f, 18.051f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(17.5f, 6.5f)
                moveToRelative(-2.5f, -0f)
                arcToRelative(2.5f, 2.5f, 0f, isMoreThanHalf = true, isPositiveArc = false, 5f, -0f)
                arcToRelative(2.5f, 2.5f, 0f, isMoreThanHalf = true, isPositiveArc = false, -5f, -0f)
            }
        }.build()

        return _Unread!!
    }

@Suppress("ObjectPropertyName")
private var _Unread: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun UnreadPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Unread, contentDescription = null)
    }
}
