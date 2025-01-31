package cn.spacexc.neogram.ui.screen.messages.send

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.theme.TitleFrame
import kotlinx.serialization.Serializable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.TgButton

@Serializable
data class SendMessageScreen(val chatId: Long, val defaultValue: String, val replyToMessageId: Long)

@Composable
fun SendMessageScreen(
    props: SendMessageScreen,
    navController: NavController
) {
    val viewModel = viewModel { SendMessageViewModel(props.chatId, props.replyToMessageId) }
    var inputValue by remember { mutableStateOf(props.defaultValue) }
    TitleFrame("编辑消息", onTitleClicked = {}, onActionClicked = {
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set("inputValue", inputValue)
        viewModel.updateDraftMessage(inputValue)
        navController.popBackStack()
    }) {
        Column {
            BasicTextField(
                value = inputValue, onValueChange = {
                    inputValue = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = it)
                    .padding(8.dp),
                textStyle = TextStyle(fontFamily = miSans, color = Color.White),
                cursorBrush = SolidColor(NeoBlue)
            )
            TgButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Send!",
                icon = Icons.AutoMirrored.Rounded.Send
            ) {
                viewModel.sendTextMessage(inputValue)
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("inputValue", "")
                navController.navigateUp()
            }
        }
    }
}