package cn.spacexc.neogram.ui.screen.settings.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.data.device.SessionsRepository
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.icons.Android
import cn.spacexc.neogram.ui.icons.ApplePhone
import cn.spacexc.neogram.ui.icons.AppleTablet
import cn.spacexc.neogram.ui.icons.Laptop
import cn.spacexc.neogram.ui.icons.NeoDevice
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Terminate
import cn.spacexc.neogram.ui.icons.Windows
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoRed
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.formatTimestamp
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

data class DeviceIcon(
    val icon: ImageVector,
    val color1: Color,
    val color2: Color,   //for gradient
    val verticalOffset: Dp = 0.dp,  //some icons, such as the android one, may not be strictly visually center-aligned. this is mainly for designing process
)

val neoDevice = DeviceIcon(NeogramIcons.NeoDevice, Color(255, 102, 44), Color(255, 126, 57))
val windowsDevice = DeviceIcon(NeogramIcons.Windows, Color(44, 142, 255), Color(57, 113, 255))
val androidDevice = DeviceIcon(
    NeogramIcons.Android,
    Color(6, 148, 56),
    Color(57, 255, 209),
    verticalOffset = (-1).dp
)
val iphoneDevice = DeviceIcon(NeogramIcons.ApplePhone, Color(36, 36, 36), Color(61, 61, 61))
val ipadDevice = DeviceIcon(NeogramIcons.AppleTablet, Color(36, 36, 36), Color(61, 61, 61))
val laptopDevice = DeviceIcon(NeogramIcons.Laptop, Color(36, 36, 36), Color(61, 61, 61))

fun getDeviceIcon(session: TdApi.Session): DeviceIcon {
    return when (session.platform.lowercase()) {
        "android" -> androidDevice
        "macos" -> laptopDevice
        "ios" -> {
            if (session.deviceModel.lowercase().contains("phone")) iphoneDevice else ipadDevice
        }
        "windows" -> windowsDevice
        else -> neoDevice
    }
}

@Serializable
data object SessionsScreen

@Composable
fun SessionsScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        SessionsRepository.getSessions()
    }
    val sessions by SessionsRepository.sessions.collectAsState()
    TitleFrame("设备", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = it, bottom = 8.dp)
                .padding(horizontal = 8.dp),
        ) {
            sessions?.let { sessions ->
                Text("此会话", fontFamily = miSans, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color.White, modifier = Modifier.padding(bottom = 8.dp))
                NeoCard(
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 6.dp,
                        bottomEnd = 6.dp
                    ),
                    background = InputBarGray,
                    borderAlpha = 0.03f
                ) {
                    val currentSession =
                        remember { sessions.sessions.find { session -> session.isCurrent } }
                    currentSession?.let {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(
                                                neoDevice.color1,
                                                neoDevice.color2
                                            )
                                        ),
                                        CircleShape
                                    )
                                    .rotate(-45f),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = neoDevice.icon,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .fillMaxSize(0.6f)
                                        .rotate(45f)
                                        .offset(y = neoDevice.verticalOffset)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(4f)
                                    .padding(start = 4.dp),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    currentSession.deviceModel,
                                    fontFamily = miSans,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White,
                                    //maxLines = 1
                                )
                                Text(
                                    "${currentSession.applicationName} ${currentSession.applicationVersion}",
                                    fontFamily = miSans,
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    //maxLines = 1
                                )
                                Text(
                                    "${currentSession.location} · 在线",
                                    fontFamily = miSans,
                                    fontSize = 12.sp,
                                    color = Color.White.copy(alpha = 0.5f),
                                    //maxLines = 1,
                                    modifier = Modifier.padding(top = 1.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
                NeoCard(
                    shape = RoundedCornerShape(
                        topStart = 6.dp,
                        topEnd = 6.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    ),
                    background = InputBarGray,
                    borderAlpha = 0.03f,
                    modifier = Modifier.clickVfx {
                        //TODO Open dialog and terminate all other sessions
                    }
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = NeogramIcons.Terminate, tint = NeoRed, contentDescription = null)
                        Text("终止所有其他会话", fontFamily = miSans, fontWeight = FontWeight.Medium, color = NeoRed)
                    }
                }

                Spacer(Modifier.height(18.dp))
                Text(
                    "活动会话",
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                sessions.sessions.filter { session -> !session.isCurrent }.forEach { session ->
                    SessionItem(session)
                    Spacer(Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
fun SessionItem(session: TdApi.Session) {
    val deviceIcon = remember { getDeviceIcon(session) }
    NeoCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        background = InputBarGray,
        borderAlpha = 0.03f
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                deviceIcon.color1,
                                deviceIcon.color2
                            )
                        ),
                        CircleShape
                    )
                    .rotate(-45f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = deviceIcon.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .rotate(45f)
                        .offset(y = deviceIcon.verticalOffset)
                )
            }
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 4.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    session.deviceModel,
                    fontFamily = miSans,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    //maxLines = 1
                )
                Text(
                    "${session.applicationName} ${session.applicationVersion}",
                    fontFamily = miSans,
                    fontSize = 12.sp,
                    color = Color.White,
                    //maxLines = 1
                )
                Text(
                    "${session.location} · ${formatTimestamp(session.lastActiveDate.toLong())}",
                    fontFamily = miSans,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.5f),
                    //maxLines = 1,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}