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

val NeogramIcons.Question: ImageVector
    get() {
        if (_Question != null) {
            return _Question!!
        }
        _Question = ImageVector.Builder(
            name = "Question",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f
            ) {
                moveTo(12f, 4.75f)
                lineTo(12f, 4.75f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 19.25f, 12f)
                lineTo(19.25f, 12f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 19.25f)
                lineTo(12f, 19.25f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 4.75f, 12f)
                lineTo(4.75f, 12f)
                arcTo(7.25f, 7.25f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 4.75f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(10.02f, 9.631f)
                curveTo(9.976f, 8.862f, 10.853f, 8.055f, 11.73f, 8.014f)
                curveTo(12.968f, 7.955f, 13.773f, 8.626f, 13.767f, 9.648f)
                curveTo(13.762f, 10.67f, 12.595f, 10.85f, 12.236f, 11.4f)
                curveTo(11.945f, 11.844f, 12.013f, 13.021f, 12.013f, 13.021f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(12f, 15f)
                verticalLineTo(15.5f)
            }
        }.build()

        return _Question!!
    }

@Suppress("ObjectPropertyName")
private var _Question: ImageVector? = null

@Preview(showBackground = true)
@Composable
private fun QuestionPreview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = NeogramIcons.Question, contentDescription = null)
    }
}
