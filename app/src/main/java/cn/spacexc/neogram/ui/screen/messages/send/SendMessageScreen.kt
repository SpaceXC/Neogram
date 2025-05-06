package cn.spacexc.neogram.ui.screen.messages.send

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.TgButton
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageScreen(
    val chatId: Long,
    val defaultValue: String,
    val replyToMessageId: Long = 0,
    val replyMessageSenderName: String = "",
    val replyMessageContent: String = "",
    val messageIdToEdit: Long? = null,
    val messageChatId: Long = 0,
    val messageContentToEdit: String? = null
)

@Composable
fun SendMessageScreen(
    props: SendMessageScreen,
    navController: NavController
) {
    val viewModel = viewModel { SendMessageViewModel(props.chatId, props.replyToMessageId) }
    var inputValue by remember { mutableStateOf(props.messageContentToEdit ?: props.defaultValue) }
    var isLoading by remember { mutableStateOf(false) }
    TitleFrame("编辑消息", onTitleClicked = {}, onActionClicked = {
        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set("inputValue", inputValue)
        viewModel.updateDraftMessage(inputValue)
        navController.popBackStack()
    }, isLoading = isLoading) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = it)
            .padding(8.dp)
        ) {
            if (props.replyToMessageId != 0L) {
                val localDensity = LocalDensity.current
                var textHeight by remember { mutableStateOf(0.dp) }
                Row(
                    modifier = Modifier
                        .padding(bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .width(3.dp)
                            .height(textHeight)
                            .background(Color.White.copy(alpha = 0.5f), CircleShape)
                    )
                    Column(modifier = Modifier.onSizeChanged {
                        textHeight = with(localDensity) { it.height.toDp() }
                    }) {
                        Text(
                            props.replyMessageSenderName,
                            color = NeoBlue,
                            fontSize = 12.sp,
                            fontFamily = miSans,
                            maxLines = 1,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            props.replyMessageContent,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = miSans,
                            maxLines = 1,
                            modifier = Modifier.alpha(0.5f)
                        )
                    }
                }
            }
            BasicTextField(
                value = inputValue, onValueChange = {
                    inputValue = it
                }, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textStyle = TextStyle(fontFamily = miSans, color = Color.White),
                cursorBrush = SolidColor(NeoBlue)
            )
            TgButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Send!",
                icon = Icons.AutoMirrored.Rounded.Send
            ) {
                if (props.messageIdToEdit != null) {
                    isLoading = true
                    viewModel.updateTextMessage(
                        props.messageChatId,
                        props.messageIdToEdit,
                        inputValue,
                        {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("inputValue", "")
                            navController.navigateUp()
                        },
                        {
                            isLoading = false
                        }
                    )
                } else {
                    viewModel.sendTextMessage(inputValue)
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("inputValue", "")
                    navController.navigateUp()
                }
            }
        }
    }
}