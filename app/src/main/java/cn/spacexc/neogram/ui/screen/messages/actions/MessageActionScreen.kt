package cn.spacexc.neogram.ui.screen.messages.actions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.settings.NeogramSettings
import cn.spacexc.neogram.ui.screen.messages.ui.MessageCard
import cn.spacexc.neogram.ui.theme.TitleFrame
import kotlinx.serialization.Serializable
import org.drinkless.tdlib.TdApi

@Serializable
data class MessageActionScreen(
    val chatId: Long,
    val messageId: Long
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MessageActionsScreen(
    chatId: Long, messageId: Long,
    navController: NavController,
    animatedContentScope: AnimatedContentScope
) {
    val viewModel = viewModel { MessageActionsViewModel(chatId, messageId) }
    val users by UserRepository.users.collectAsState()
    val chats by ChatListRepository.chats.collectAsState()
    val currentUserId by UserRepository.currentUserId.collectAsState()
    val settings by NeogramSettings.neogramSettings()

    TitleFrame(title = "", onActionClicked = navController::navigateUp, onTitleClicked = {}) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
                .padding(top = it)
        ) {
            viewModel.currentMessage?.let { message ->
                val senderIsMe =
                    message.senderId is TdApi.MessageSenderUser && (message.senderId as TdApi.MessageSenderUser).userId == currentUserId
                val isRead = message.sendingState
                MessageCard(
                    animatedContentScope = animatedContentScope,
                    isGroupChat = false,//currentChat?.type != null && (currentChat.type is TdApi.ChatTypeBasicGroup || currentChat.type is TdApi.ChatTypeSupergroup),
                    users = users.map { Pair(it.key, it.value.tgUser) }.toMap(),
                    chats = chats,
                    modifier = Modifier,
                    message = message,
                    isPreviousOneContinuous = false,
                    isNextOneContinuous = false,
                    messages = emptyMap(),
                    isRead = null,
                    senderIsMe = senderIsMe,
                    settings = settings,
                    onVibrate = {

                    },
                    navController = navController
                ) { senderName, replyContent ->

                }
                Row {

                }
            }
        }
    }
}