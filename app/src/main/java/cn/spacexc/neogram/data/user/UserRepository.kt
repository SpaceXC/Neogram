package cn.spacexc.neogram.data.user

import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.UpdateUser
import org.drinkless.tdlib.TdApi.UpdateUserFullInfo
import org.drinkless.tdlib.TdApi.User
import org.drinkless.tdlib.TdApi.UserStatus

object UserRepository {
    val users = MutableStateFlow(mapOf<Long, NeoUser>())
    val currentUserId = MutableStateFlow(0L)

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