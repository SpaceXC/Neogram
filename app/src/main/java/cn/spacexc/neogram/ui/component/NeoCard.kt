package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.telegram.ui.component.clickVfx

@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    shape: Shape,
    brush: Brush,
    borderAlpha: Float = 0.2f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        Box(modifier = Modifier.background(brush, shape)) {
            Box(
                modifier = Modifier.border(
                    .8.dp,
                    Color.White.copy(alpha = borderAlpha),
                    shape = shape
                )
            ) {
                content()
            }
        }
    }
}

@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    shape: Shape,
    background: Color,
    borderAlpha: Float = 0.2f,
    content: @Composable BoxScope.() -> Unit
) = NeoCard(modifier, shape, SolidColor(background), borderAlpha, content)

@Preview
@Composable
private fun NeoCardPrev() {
    NeoCard(
        modifier = Modifier.clickVfx(),
        shape = RoundedCornerShape(10.dp),
        background = InputBarGray
    ) {
        Text(
            "Hi! Here is a text!!", modifier = Modifier.padding(
                8.dp
            ), color = Color.White
        )
    }
}