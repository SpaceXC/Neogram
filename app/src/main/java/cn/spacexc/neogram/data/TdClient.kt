package cn.spacexc.neogram.data

import cn.spacexc.neogram.data.auth.AuthRepository.authHandler
import cn.spacexc.neogram.data.chat.ChatListRepository.chatListHandler
import cn.spacexc.neogram.data.color.AccentColorRepository.accentColorsHandler
import cn.spacexc.neogram.data.connection.ConnectionStateRepository.connectionStateHandler
import cn.spacexc.neogram.data.folders.FoldersRepository.foldersHandler
import cn.spacexc.neogram.data.message.MessageRepository.messageHandler
import cn.spacexc.neogram.data.notification.notificationHandler
import cn.spacexc.neogram.data.user.UserRepository.userHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.Client.ResultHandler
import org.drinkless.tdlib.TdApi

object TdClient {
    private val updateHandler = ResultHandler {
        MainScope().launch {
            it.apply {
                connectionStateHandler()
                authHandler()
                chatListHandler()
                userHandler()
                messageHandler()
                accentColorsHandler()
                notificationHandler()
                foldersHandler()
            }
        }
    }

    private val client = Client.create(updateHandler, { exception ->
        exception.printStackTrace()
    }, { exception ->
        exception.printStackTrace()
    })

    fun send(
        op: TdApi.Function<*>,
        callback: (TdApi.Object?) -> Unit = {},
        onError: (Throwable?) -> Unit = {}
    ) {
        client.send(
            op,
            { `object` -> callback(`object`) },
            { e -> onError(e) }
        )
    }
}