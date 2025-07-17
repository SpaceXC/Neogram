package cn.spacexc.neogram.ui.screen.settings.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.Application
import cn.spacexc.neogram.R
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.device.SessionsRepository
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.icons.AppIcon
import cn.spacexc.neogram.ui.icons.Appearance
import cn.spacexc.neogram.ui.icons.Device
import cn.spacexc.neogram.ui.icons.Folder
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.icons.Private
import cn.spacexc.neogram.ui.icons.Storage
import cn.spacexc.neogram.ui.icons.Text
import cn.spacexc.neogram.ui.icons.Unmuted
import cn.spacexc.neogram.ui.screen.settings.sessions.SessionsScreen
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.rgba
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data object SettingsScreen

@Composable
fun SettingsScreen(navController: NavController) {
    val sessions by SessionsRepository.sessions.collectAsState()
    LaunchedEffect(Unit) {
        SessionsRepository.getSessions()
    }
    TitleFrame("设置", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = it)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AboutCard()
            Spacer(Modifier.height(2.dp))
            navController.apply {
                SettingsItem(
                    leadingIcon = NeogramIcons.Device,
                    itemName = "设备",
                    trailingText = sessions?.sessions?.size?.toString() ?: "",
                    navigateTo = SessionsScreen
                )
                SettingsItem(
                    leadingIcon = NeogramIcons.Folder,
                    itemName = "聊天文件夹"
                )
                Text("基本", fontFamily = miSans, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color.White.copy(alpha = 0.8f), modifier = Modifier.padding(vertical = 2.dp).offset(x = 4.dp))
                SettingsItem(
                    leadingIcon = NeogramIcons.Unmuted,
                    itemName = "通知与铃声"
                )
                SettingsItem(
                    leadingIcon = NeogramIcons.Private,
                    itemName = "隐私与安全"
                )
                SettingsItem(
                    leadingIcon = NeogramIcons.Storage,
                    itemName = "储存空间"
                )
                SettingsItem(
                    leadingIcon = NeogramIcons.Appearance,
                    itemName = "外观"
                )
                SettingsItem(
                    leadingIcon = NeogramIcons.Text,
                    itemName = "语言"
                )
            }
        }
    }
}

@Composable
fun AboutCard() {
    var tdlibVersion by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.GetOption("version"), {
            tdlibVersion = (it as TdApi.OptionValueString).value
        }, {
            tdlibVersion = it.toString()
        })
    }

    NeoCard(
        shape = RoundedCornerShape(14.dp), brush = Brush.horizontalGradient(
            listOf(
                rgba(14, 14, 14),
                rgba(57, 34, 24)
            )
        ), borderAlpha = 0.03f, modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.img_about_arrow_pointing_to_the_left),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(y = (-14).dp)
                    .fillMaxWidth(0.63f),
                contentScale = ContentScale.FillWidth
            )
            Image(
                painter = painterResource(R.drawable.img_about_arrow_pointing_to_the_right),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth(0.5f),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "关于",
                        fontFamily = miSans,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.weight(1f))
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            buildAnnotatedString {
                                append("Release ")
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = NeoMain
                                    )
                                ) {
                                    append("No.${Application.getReleaseNumber()}")
                                }
                            },
                            color = Color.White,
                            fontFamily = miSans,
                            fontSize = 11.sp
                        )
                        Text(
                            buildAnnotatedString {
                                append("TDLib")
                                withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                                    append(" ")
                                    append(tdlibVersion)
                                }
                            },
                            color = Color.White.copy(alpha = 0.7f),
                            fontFamily = miSans,
                            fontSize = 9.sp
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    Image(
                        NeogramIcons.AppIcon, contentDescription = null, modifier = Modifier
                            .fillMaxWidth(0.33f)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        Application.getVersionName(),
                        color = Color.White,
                        fontFamily = miSans,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun NavController.SettingsItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    itemName: String,
    navigateTo: Any,
    trailingText: String = ""
) {
    SettingsItem(
        modifier = modifier.clickVfx { navigate(navigateTo) },
        leadingIcon = leadingIcon,
        itemName = itemName,
        trailingText = trailingText
    )
}

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    itemName: String,
    trailingText: String = "",
    shape: Shape = RoundedCornerShape(16.dp),
    background: Color = InputBarGray,
    borderAlpha: Float = 0.03f
) {
    NeoCard(
        shape = shape,
        background = background,
        borderAlpha = borderAlpha,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = leadingIcon,
                null,
                tint = Color.White,
                modifier = Modifier.size(24.dp),
            )
            Spacer(Modifier.width(6.dp))
            Text(
                itemName,
                color = Color.White,
                fontSize = 13.sp,
                fontFamily = miSans,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Text(
                trailingText,
                color = Color.White,
                fontSize = 13.sp,
                fontFamily = miSans,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .alpha(0.7f)
                    .padding(end = 2.dp)
            )
        }
    }
}