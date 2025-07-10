package cn.spacexc.neogram.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.data.auth.AuthRepository
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.clickVfx
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun QrCode(
    authState: AuthRepository.AuthState.WaitingQrCodeScan,
    viewModel: AuthViewModel
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(
            Modifier
                .fillMaxWidth(0.65f)
                .aspectRatio(1f)
                .background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = rememberDrawablePainter(viewModel.getQrCodeDrawable(authState.qrCodeLink)),
                contentDescription = "Qr Code",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize(0.82f)
                    .align(Alignment.Center)
            )
        }
        Text("扫描二维码以登录", fontFamily = miSans, color = Color.White)
        Text("PHONE", fontFamily = miSans, color = Color.White, modifier = Modifier.clickVfx {
            viewModel.usePhoneNumber()
        })
    }
}