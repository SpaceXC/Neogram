package cn.spacexc.neogram.ui.screen.messages.send

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
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
import androidx.compose.ui.Alignment.Companion.CenterStart
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
import cn.spacexc.neogram.ui.component.NeoCard
import cn.spacexc.neogram.ui.icons.AddEmoji
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.screen.messages.sticker.StickerMessage
import cn.spacexc.neogram.ui.screen.messages.sticker.StickersScreen
import cn.spacexc.neogram.ui.theme.InputBarGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.telegram.ui.component.clickVfx
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data class SendMessageScreen(
    val chatId: Long,
    val receiverName: String,
    val defaultValue: String,
    val replyToMessageId: Long = 0,
    val replyMessageSenderName: String = "",
    val replyMessageContent: String = "",
    val messageIdToEdit: Long? = null,
    val messageContentToEdit: String? = null
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SendMessageScreen(
    props: SendMessageScreen,
    animatedContentScope: AnimatedContentScope,
    navController: NavController
) {
    val localDensity = LocalDensity.current
    var inputBarHeight by remember { mutableStateOf(0.dp) }
    val viewModel = viewModel { SendMessageViewModel(props.chatId, props.replyToMessageId) }
    var inputValue by remember { mutableStateOf(props.messageContentToEdit ?: props.defaultValue) }
    var isLoading by remember { mutableStateOf(false) }
    val stickerToSend = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<StickerMessage?>(
            "stickerToSend", null
        )?.collectAsState()
    LaunchedEffect(stickerToSend) {
        stickerToSend?.value?.let { sticker ->
            viewModel.sendStickerMessage(
                sticker.fileId,
                sticker.width,
                sticker.height,
                sticker.emoji
            )
            navController.navigateUp()
        }
    }
    TitleFrame("编辑消息", timeText = props.receiverName, onTitleClicked = {}, onActionClicked = {
        //viewModel.updateDraftMessage(inputValue)
        navController.navigateUp()
    }, isLoading = isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it)
                .padding(horizontal = 8.dp)
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
                            color = NeoMain,
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
            Box(
                modifier = Modifier
                    .weight(1f)
                    .sharedElement(
                        rememberSharedContentState("inputBackground"),
                        animatedContentScope
                    )
            ) {
                BasicTextField(
                    value = inputValue,
                    onValueChange = { value ->
                        inputValue = value
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("inputValue", inputValue)
                    },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = TextStyle(fontFamily = miSans, color = Color.White),
                    cursorBrush = SolidColor(NeoMain),
                )
                if (inputValue.isEmpty()) {
                    Text(
                        "键入消息",
                        fontFamily = miSans,
                        color = Color.White.copy(alpha = 0.4f),
                        modifier = Modifier.sharedBounds(
                            rememberSharedContentState(key = "enterMessageHint"),
                            animatedContentScope
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                NeoCard(
                    modifier = Modifier
                        .size(inputBarHeight)
                        .clickVfx {
                            navController.navigate(StickersScreen)
                        },
                    background = InputBarGray,
                    borderAlpha = 0.03f,
                    shape = RoundedCornerShape(40)
                ) {
                    Icon(
                        imageVector = NeogramIcons.AddEmoji,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    )
                }
                NeoCard(
                    modifier = Modifier
                        .clickVfx {
                            if (props.messageIdToEdit == null) {
                                viewModel.sendTextMessage(inputValue)
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("inputValue", "")
                                navController.navigateUp()
                            } else {
                                isLoading = true
                                viewModel.updateTextMessage(
                                    messageId = props.messageIdToEdit,
                                    textContent = inputValue,
                                    {
                                        navController.previousBackStackEntry
                                            ?.savedStateHandle
                                            ?.set("inputValue", "")
                                        navController.navigateUp()
                                    },
                                    {
                                        isLoading = false
                                    })
                            }
                        }
                        .weight(1f)
                        .onSizeChanged { size ->
                            with(localDensity) { inputBarHeight = size.height.toDp() }
                        },
                    //.background(InputBarGray, CircleShape)
                    background = NeoMain,
                    shape = RoundedCornerShape(45)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            //.alpha(if (inputValue?.value.isNullOrEmpty()) 0.6f else 1f)
                            .align(CenterStart)
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        /*.height(inputBarHeight)*/
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            /*if (inputValue?.value.isNullOrEmpty()) "发送消息" else inputValue.value*/
                            "发送",
                            fontFamily = miSans,
                            fontSize = 13.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }
                }
            }
        }
    }
}