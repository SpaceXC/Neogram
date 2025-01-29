package cn.spacexc.neogram.ui.screen.chats

import androidx.lifecycle.ViewModel
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.drinkless.tdlib.TdApi.ChatTypePrivate


class ChatListViewModel : ViewModel() {
    val chatList = combineChatListAndUsers(ChatListRepository.chatList, UserRepository.users, ChatListRepository.chatActions)

    private fun combineChatListAndUsers(
        chatListFlow: Flow<List<ChatListRepository.ChatItem>>,
        usersFlow: Flow<Map<Long, UserRepository.NeoUser>>,
        actions: Flow<Map<Long, ChatListRepository.ChatAction>>
    ): Flow<List<ChatListRepository.ChatItem>> {
        return combine(chatListFlow, usersFlow, actions) { chatList, usersMap, actionsMap ->
            chatList.map { chat ->
                val newChat = chat.copy()
                if(chat.type is ChatTypePrivate) {
                    val userId = chat.type.userId
                    val user = usersMap[userId]
                    if (user != null) {
                        newChat.userStatus = user.status
                    }
                }
                newChat.chatAction = actionsMap[chat.id]
                newChat
            }
        }
    }
}
