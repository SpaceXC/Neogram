package cn.spacexc.neogram.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.auth.AuthRepository
import cn.spacexc.neogram.ui.screen.chats.ChatListScreen
import cn.spacexc.neogram.ui.theme.TitleFrame
import kotlinx.serialization.Serializable

@Serializable
data object AuthScreen

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.usePhoneNumber()
    }
    val scrollState = rememberScrollState()
    TitleFrame(
        "登录",
        onActionClicked = navController::navigateUp,
        onTitleClicked = {},
        isLoading = viewModel.isLoading
    ) { padding ->
        val authState by AuthRepository.authState.collectAsState()

        LaunchedEffect(authState) {
            if (authState is AuthRepository.AuthState.LoggedIn) {
                navController.navigate(ChatListScreen)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                //.verticalScroll(scrollState)
                .padding(top = padding + 8.dp, bottom = 8.dp)
                .padding(horizontal = 11.dp),
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when(authState) {
                AuthRepository.AuthState.EnterPhone -> PhoneNumber(viewModel)
                AuthRepository.AuthState.EnterCode -> AuthCode(viewModel)
                is AuthRepository.AuthState.EnterPassword -> Password(authState as AuthRepository.AuthState.EnterPassword, viewModel)
                is AuthRepository.AuthState.WaitingQrCodeScan -> QrCode(authState as AuthRepository.AuthState.WaitingQrCodeScan, viewModel)
                else -> {}
            }
        }
    }
}