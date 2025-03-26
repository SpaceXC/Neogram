package cn.spacexc.neogram.ui.screen.auth

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cn.spacexc.neogram.ui.component.TgTextField
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.TgButton

@Composable
fun AuthCode(viewModel: AuthViewModel) {
    val textFieldState = rememberTextFieldState()
    Text(text = "输入验证码", color = Color.White, fontFamily = miSans)
    TgTextField(state = textFieldState)
    TgButton(text = "继续", icon = Icons.AutoMirrored.Rounded.KeyboardArrowRight) {
        viewModel.sendCode(textFieldState.text.toString())
    }
}