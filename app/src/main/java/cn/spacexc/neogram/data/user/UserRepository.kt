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
    val currentUserId = MutableStateFlow(0L)
    val mutex = Mutex()

    fun TdApi.Object.userHandler() {
        when (this) {
            is UpdateUser -> {
                LogUtils.info("UpdateUser", "${user.id}")
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

            is TdApi.UpdateOption -> {
                if (this.name == "my_id") {
                    currentUserId.value = (value as TdApi.OptionValueInteger).value
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