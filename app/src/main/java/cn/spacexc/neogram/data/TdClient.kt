package cn.spacexc.neogram.data

import cn.spacexc.neogram.data.auth.AuthRepository.authHandler
import cn.spacexc.neogram.data.call.CallHandler.callHandler
import cn.spacexc.neogram.data.chat.ChatListRepository.chatListHandler
import cn.spacexc.neogram.data.color.AccentColorRepository.accentColorsHandler
import cn.spacexc.neogram.data.connection.ConnectionStateRepository.connectionStateHandler
import cn.spacexc.neogram.data.file.FileRepository.downloadHandler
import cn.spacexc.neogram.data.folders.FoldersRepository.foldersHandler
import cn.spacexc.neogram.data.message.MessageRepository.messageHandler
import cn.spacexc.neogram.data.notification.notificationHandler
import cn.spacexc.neogram.data.user.UserRepository.userHandler
import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.Client.ResultHandler
import org.drinkless.tdlib.TdApi
import kotlin.coroutines.resumeWithException

object TdClient {

    private val updateHandler = ResultHandler {
        MainScope().launch {
            it.apply {
                LogUtils.info("UPDATE", this.toString())
                connectionStateHandler()
                authHandler()
                chatListHandler()
                userHandler()
                messageHandler()
                accentColorsHandler()
                notificationHandler()
                foldersHandler()
                callHandler()
                downloadHandler()
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Throws
    suspend inline fun <reified T : TdApi.Object> sendAsync(
        op: TdApi.Function<*>
    ): T? = suspendCancellableCoroutine { cont ->
        send(
            op,
            callback = { response ->
                if (!cont.isCompleted) {
                    if (response is T) {
                        cont.resume(response) {} // 避免 null，给个默认错误对象
                    } else null
                }
            },
            onError = { throwable ->
                if (!cont.isCompleted) {
                    cont.resumeWithException(throwable ?: RuntimeException("Unknown error"))
                }
            }
        )
    }
}