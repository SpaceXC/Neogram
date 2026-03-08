package cn.spacexc.neogram.ui.screen.auth

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cn.spacexc.neogram.data.auth.AuthRepository
import cn.spacexc.neogram.ui.component.TgTextField
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.TgButton

@Composable
fun Password(authState: AuthRepository.AuthState.EnterPassword, viewModel: AuthViewModel) {
    val textFieldState = rememberTextFieldState()
    Text(text = "请输入密码", color = Color.White, fontFamily = miSans)
    Text(text = authState.passwordHint, color = Color.White, fontFamily = miSans)
    TgTextField(state = textFieldState)
    TgButton(text = "继续", icon = Icons.AutoMirrored.Rounded.KeyboardArrowRight) {
        viewModel.sendPassword(textFieldState.text.toString())
    }
}