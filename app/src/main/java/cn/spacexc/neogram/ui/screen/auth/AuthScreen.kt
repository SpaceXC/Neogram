package cn.spacexc.neogram.ui.screen.auth

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.auth.AuthRepository
import cn.spacexc.neogram.ui.screen.chats.ChatListScreen
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.serialization.Serializable

@Serializable
data object AuthScreen

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.requestQrCode()
    }
    val qrCodeScrollState = rememberScrollState()
    TitleFrame(
        "登录",
        onActionClicked = navController::navigateUp,
        onTitleClicked = {}) { padding ->
        val authState by AuthRepository.authState.collectAsState()

        LaunchedEffect(authState) {
            if (authState is AuthRepository.AuthState.LoggedIn) {
                navController.navigate(ChatListScreen)
            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(qrCodeScrollState)
                .padding(top = padding + 8.dp, bottom = 8.dp)
                .padding(horizontal = 11.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val text = authState.dialogHint
            Box(
                Modifier
                    .fillMaxWidth(0.65f)
                    .aspectRatio(1f)
                    .background(Color.White, RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = rememberDrawablePainter(viewModel.getQrCodeDrawable(text)),
                    contentDescription = "Qr Code",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(0.82f).align(Alignment.Center)
                )
            }
            Text(
                "$authState",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = miSans,
                color = Color.White
            )
        }
    }
}