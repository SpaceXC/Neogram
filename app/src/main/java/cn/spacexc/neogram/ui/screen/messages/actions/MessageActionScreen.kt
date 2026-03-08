package cn.spacexc.neogram.ui.screen.messages.actions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.settings.NeogramSettings
import cn.spacexc.neogram.ui.component.NeoIconButton
import cn.spacexc.neogram.ui.icons.Back
import cn.spacexc.neogram.ui.icons.Delete
import cn.spacexc.neogram.ui.icons.Edit
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.screen.forward.ForwardMessageScreen
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.screen.messages.ui.MessageCard
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data class MessageActionScreen(
    val chatId: Long,
    val messageId: Long,
    val isGroupChat: Boolean,
    val isRead: Boolean
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MessageActionsScreen(
    chatId: Long, messageId: Long,
    navController: NavController,
    animatedContentScope: AnimatedContentScope,
    isGroupChat: Boolean,
    isRead: Boolean,
) {
    val viewModel = viewModel { MessageActionsViewModel(chatId, messageId) }
    val users by UserRepository.users.collectAsState()
    val chats by ChatListRepository.chats.collectAsState()
    val currentChat = chats[chatId]
    val settings by NeogramSettings.neogramSettings()

    TitleFrame(title = "", onActionClicked = navController::navigateUp, onTitleClicked = {}) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
                .padding(top = it)
        ) {
            viewModel.currentMessage?.let { message ->
                MessageCard(
                    animatedContentScope = animatedContentScope,
                    isGroupChat = isGroupChat,
                    users = users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                    chats = chats,
                    message = message,
                    isPreviousOneContinuous = false,
                    isNextOneContinuous = false,
                    messages = viewModel.messagesNeeded,
                    isRead = isRead,
                    senderIsMe = message.isOutgoing,
                    settings = settings,
                    onVibrate = {},
                    navController = navController,
                    onLocateToMessage = { },
                    onCheckboxClicked = { },
                    onReplyMessage = { _, _ -> }
                )
                Column(modifier = Modifier.padding(bottom = 8.dp)) {
                    if (message.isOutgoing) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            NeoIconButton(
                                icon = NeogramIcons.Edit,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickVfx {
                                        navController.navigateUp()
                                        navController.navigate(
                                            SendMessageScreen(
                                                chatId,
                                                currentChat?.title ?: "",
                                                "",
                                                0,
                                                "",
                                                "",
                                                message.id,
                                                (message.content as? TdApi.MessageText)?.text?.text
                                                    ?: ""
                                            )
                                        )
                                    }
                            )
                            NeoIconButton(
                                icon = NeogramIcons.Delete,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickVfx {
                                        viewModel.deleteMessage(messageId)
                                        navController.navigateUp()
                                    }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        NeoIconButton(
                            icon = Icons.Outlined.Bookmarks,
                            iconModifier = Modifier.scale(0.7f),
                            modifier = Modifier
                                .weight(1f)
                                .clickVfx {
                                    viewModel.saveMessage(message)
                                    navController.navigateUp()
                                }
                        )
                        NeoIconButton(
                            icon = NeogramIcons.Back,
                            modifier = Modifier
                                .weight(1f)
                                .clickVfx {
                                    navController.navigateUp()
                                    navController.navigate(
                                        ForwardMessageScreen(
                                            message.messageThreadId,
                                            message.chatId,
                                            message.id
                                        )
                                    )
                                }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        NeoIconButton(
                            icon = Icons.Outlined.Checklist,
                            iconModifier = Modifier.scale(0.7f),
                            modifier = Modifier
                                .weight(1f)
                                .clickVfx {
                                    // Source - https://stackoverflow.com/a/66837741
                                    // Posted by nglauber
                                    // Retrieved 2026-02-23, License - CC BY-SA 4.0
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("selectedMessage", messageId)
                                    navController.navigateUp()
                                }
                        )
                    }
                }
            }
        }
    }
}