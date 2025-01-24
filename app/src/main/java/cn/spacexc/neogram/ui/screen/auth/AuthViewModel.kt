package cn.spacexc.neogram.ui.screen.auth

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.TdClient
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.createQrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorBallShape
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorColor
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorFrameShape
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorPixelShape
import org.drinkless.tdlib.TdApi.RequestQrCodeAuthentication

class AuthViewModel : ViewModel() {
    fun requestQrCode() {
        TdClient.send(RequestQrCodeAuthentication(), {}, {})
    }

    fun getQrCodeDrawable(url: String): Drawable {
        val data = QrData.Url(url)
        val options = createQrVectorOptions {
            shapes {
                darkPixel = QrVectorPixelShape
                    .RoundCorners(.5f)
                ball = QrVectorBallShape
                    .RoundCorners(.25f)
                frame = QrVectorFrameShape
                    .RoundCorners(.25f)
            }
        }
        return QrCodeDrawable(data, options)
    }
}