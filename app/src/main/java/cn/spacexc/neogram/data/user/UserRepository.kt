package cn.spacexc.neogram.data.user

import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.chat.ChatListRepository.chats
import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Chat
import org.drinkless.tdlib.TdApi.UpdateUser
import org.drinkless.tdlib.TdApi.UpdateUserFullInfo
import org.drinkless.tdlib.TdApi.User
import org.drinkless.tdlib.TdApi.UserStatus

object UserRepository {
    val users = MutableStateFlow(mapOf<Long, NeoUser>())

    fun TdApi.Object.userHandler() {
        when (this) {
            is UpdateUser -> {
                val updatedMap = users.value.toMutableMap()
                updatedMap[user.id] = NeoUser(user, user.status)
                users.value = updatedMap
            }

            is TdApi.UpdateUserStatus -> {
                val temp = users.value.toMutableMap()
                temp[userId]?.let { user ->
                    val tgUser = user.tgUser
                    tgUser.status = status
                    val newUser = user.copy(tgUser = tgUser, status = status)
                    temp[userId] = newUser
                    users.value = temp
                }
            }

            is UpdateUserFullInfo -> {
                //TODO
            }
        }
    }

    data class NeoUser(
        val tgUser: User,
        val status: UserStatus
    )
}