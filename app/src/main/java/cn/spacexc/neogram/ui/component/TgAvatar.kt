package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.data.color.AccentColorRepository
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.username
import org.drinkless.tdlib.TdApi

@Composable
fun TgUserAvatar(
    modifier: Modifier = Modifier,
    avatarSize: Dp,
    user: TdApi.User
) {
    val thumbnailBytes = user.profilePhoto?.minithumbnail?.data
    val localDensity = LocalDensity.current
    Box(
        modifier = modifier
            .size(avatarSize)
    ) {
        if (thumbnailBytes != null) {
            TgImage(
                file = user.profilePhoto!!.small, //都有缩略图了岂不是包有图的
                thumbnail = thumbnailBytes,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            val accentColor = AccentColorRepository.getAccentColor(user.accentColorId)
            val brush =
                if (accentColor == null) SolidColor(NeoBlue) else Brush.verticalGradient(
                    listOf(accentColor.backgroundColor, accentColor.background2Color)
                )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush, CircleShape)
            ) {
                Text(
                    user.username.first().uppercase(),
                    color = Color.White,
                    fontFamily = miSans,
                    fontSize = with(localDensity) { (avatarSize * 0.7f).toSp() },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

@Composable
fun TgUserAvatar(
    modifier: Modifier = Modifier,
    user: TdApi.User
) {
    var avatarSize by remember { mutableStateOf(0.dp) }
    val thumbnailBytes = user.profilePhoto?.minithumbnail?.data
    val localDensity = LocalDensity.current
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .onSizeChanged { avatarSize = with(localDensity) { it.width.toDp() } }
    ) {
        if (thumbnailBytes != null) {
            TgImage(
                file = user.profilePhoto!!.small, //都有缩略图了岂不是包有图的
                thumbnail = thumbnailBytes,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            val accentColor = AccentColorRepository.getAccentColor(user.accentColorId)
            val brush =
                if (accentColor == null) SolidColor(NeoBlue) else Brush.verticalGradient(
                    listOf(accentColor.backgroundColor, accentColor.background2Color)
                )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush, CircleShape)
            ) {
                Text(
                    user.username.first().uppercase(),
                    color = Color.White,
                    fontFamily = miSans,
                    fontSize = with(localDensity) { (avatarSize * 0.5f).toSp() },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

@Composable
fun TgChatAvatar(
    modifier: Modifier = Modifier,
    avatarSize: Dp,
    chat: TdApi.Chat
) {
    val thumbnailBytes = chat.photo?.minithumbnail?.data
    val localDensity = LocalDensity.current
    Box(
        modifier = modifier
            .size(avatarSize)
    ) {
        if (thumbnailBytes != null) {
            TgImage(
                file = chat.photo!!.small, //都有缩略图了岂不是包有图的
                thumbnail = thumbnailBytes,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            val accentColor = AccentColorRepository.getAccentColor(chat.accentColorId)
            val brush =
                if (accentColor == null) SolidColor(NeoBlue) else Brush.verticalGradient(
                    listOf(accentColor.backgroundColor, accentColor.background2Color)
                )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush, CircleShape)
            ) {
                Text(
                    chat.title.first().uppercase(),
                    color = Color.White,
                    fontFamily = miSans,
                    fontSize = with(localDensity) { (avatarSize * 0.7f).toSp() },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}