package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.ui.theme.parseColor

@Composable
fun TgTextField(modifier: Modifier = Modifier, state: TextFieldState) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(parseColor("#111111"), RoundedCornerShape(30))
            .border(0.1.dp, color = NeoBlue, RoundedCornerShape(30))
            .padding(vertical = 12.dp, horizontal = 10.dp)
    ) {
        BasicTextField(
            state = state,
            textStyle = TextStyle(fontFamily = miSans, color = Color.White),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(
                NeoBlue
            )
        )
    }
}

@Preview(device = Devices.WEAR_OS_SQUARE)
@Composable
private fun TgTextFieldPrev() {
    TgTextField(state = rememberTextFieldState())
}