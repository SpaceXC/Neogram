package cn.spacexc.neogram.ui.screen.chats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.ChatTypePrivate
import org.drinkless.tdlib.TdApi.User


class ChatListViewModel : ViewModel() {
    val chatList = combineChatListAndUsers(ChatListRepository.chatList, UserRepository.users)

    private fun combineChatListAndUsers(
        chatListFlow: Flow<List<ChatListRepository.ChatItem>>,
        usersFlow: Flow<Map<Long, UserRepository.NeoUser>>
    ): Flow<List<ChatListRepository.ChatItem>> {
        return combine(chatListFlow, usersFlow) { chatList, usersMap ->
            chatList.map { chat ->
                if(chat.type is ChatTypePrivate) {
                    val userId = chat.type.userId
                    val user = usersMap[userId]
                    if (user != null) {
                        chat.copy(userStatus = user.tgUser.status)
                    } else {
                        chat
                    }
                }
                else chat
            }
        }
    }
}