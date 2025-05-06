package cn.spacexc.neogram.ui.screen.chats.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.proto.settings.ChatItemStyle
import cn.spacexc.neogram.proto.settings.copy
import cn.spacexc.neogram.settings.NeogramSettings.neogramSettings
import cn.spacexc.neogram.settings.updateConfiguration
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.coroutines.launch
import org.drinkless.tdlib.TdApi

@Composable
fun MenuScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val settings by neogramSettings()
    var tdlibVersion by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        TdClient.send(TdApi.GetOption("version"), {
            tdlibVersion = (it as TdApi.OptionValueString).value
        }, {
            tdlibVersion = it.toString()
        })
    }
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            if (settings.chatItemStyle == ChatItemStyle.Minimalist) "Minimalist" else "Bubble",
            modifier = Modifier
                .fillMaxWidth()
                .clickVfx(onClick = {
                    scope.launch {
                        context.updateConfiguration {
                            copy {
                                chatItemStyle =
                                    if (settings.chatItemStyle == ChatItemStyle.Minimalist) ChatItemStyle.Bubble else ChatItemStyle.Minimalist
                            }
                        }
                    }
                }),
            color = Color.White,
            fontFamily = miSans,
            textAlign = TextAlign.Center
        )
        Text(
            "tdlib version $tdlibVersion",
            modifier = Modifier
                .fillMaxWidth()
                .clickVfx(onClick = {
                    scope.launch {
                        context.updateConfiguration {
                            copy {
                                chatItemStyle =
                                    if (settings.chatItemStyle == ChatItemStyle.Minimalist) ChatItemStyle.Bubble else ChatItemStyle.Minimalist
                            }
                        }
                    }
                }),
            color = Color.White,
            fontFamily = miSans,
            textAlign = TextAlign.Center
        )
    }
}