package cn.spacexc.neogram.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.data.auth.AuthRepository
import cn.spacexc.neogram.ui.icons.AppIcon
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.theme.miSans
import kotlinx.serialization.Serializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.screen.auth.AuthScreen
import cn.spacexc.neogram.ui.screen.chats.ChatListScreen
import kotlinx.coroutines.delay

@Serializable
object SplashScreen

@Composable
fun SplashScreen(navController: NavController) {
    val authState by AuthRepository.authState.collectAsState()
    LaunchedEffect(authState) {
        if (authState is AuthRepository.AuthState.LoggedIn) {
            navController.navigate(ChatListScreen) {
                popUpTo(SplashScreen) {
                    inclusive = true
                }
            }
        } else {
            delay(1000)
            navController.navigate(AuthScreen) {
                popUpTo(SplashScreen) {
                    inclusive = true
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            NeogramIcons.AppIcon,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.Center)
        )
    }
}