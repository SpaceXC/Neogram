package cn.spacexc.neogram.data.chat

import androidx.compose.ui.text.AnnotatedString
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.deepCopy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.Chat
import org.drinkless.tdlib.TdApi.ChatAction
import org.drinkless.tdlib.TdApi.ChatActionCancel
import org.drinkless.tdlib.TdApi.ChatListMain
import org.drinkless.tdlib.TdApi.ChatPosition
import org.drinkless.tdlib.TdApi.Message
import org.drinkless.tdlib.TdApi.MessageSender
import org.drinkless.tdlib.TdApi.UserStatus

object ChatListRepository {
    val chats = MutableStateFlow(mapOf<Long, Chat>())
    val chatActions = MutableStateFlow(mapOf<Long, ChatAction>())
    private val chatPositions =
        MutableStateFlow(emptyMap<Long, ChatPosition>())
    private var haveFullyLoaded = false
    val mutex = Mutex()

    val chatList = chats.combine(chatPositions) { list, positions ->
        positions.entries.toList().sortedBy { it.value.order }.asReversed().map { list[it.key] }
            .mapNotNull { it }
            .map { chat ->
                ChatItem(
                    id = chat.id,
                    title = chat.title,
                    lastMessage = chat.lastMessage,
                    photo = chat.photo,
                    type = chat.type,
                    userStatus = null,
                    unreadCount = chat.unreadCount,
                    isMuted = chat.notificationSettings.muteFor != 0,
                    chatAction = null
                )
            }
    }

    fun getMainChatList(limit: Int = 20) {
        runBlocking {
            mutex.withLock {
                if (!haveFullyLoaded && limit > chats.value.size) {
                    TdClient.send(
                        TdApi.LoadChats(ChatListMain(), limit - chats.value.size),
                        {
                            if (it is TdApi.Ok) {
                                getMainChatList(limit)
                            }
                            if (it is TdApi.Error && it.code == 404) {
                                LogUtils.info("getMainChatList", "Load Completed")
                                haveFullyLoaded = true
                            }
                        },
                        {
                            it?.printStackTrace()
                        }
                    )
                }
            }
        }
    }

    suspend fun TdApi.Object.chatListHandler() {
        when (this) {
            is TdApi.UpdateNewChat -> {
                val updatedChats = chats.value.toMutableMap()
                updatedChats[chat.id] = chat
                setChatPositions(chat, chat.positions)
                chats.value = updatedChats
            }

            is TdApi.UpdateChatTitle -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.title = title
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatPhoto -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.photo = photo
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatLastMessage -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.lastMessage = lastMessage
                        updatedChats[chatId] = chatToUpdate
                        setChatPositions(chatToUpdate, positions)
                    }
                }
            }

            is TdApi.UpdateChatReadInbox -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.unreadCount = unreadCount
                        LogUtils.info("UpdateChatUnreadCount", "$unreadCount")
                        chatToUpdate.lastReadInboxMessageId = lastReadInboxMessageId
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatReadOutbox -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.lastReadOutboxMessageId = lastReadOutboxMessageId
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatUnreadMentionCount -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.unreadMentionCount = unreadMentionCount
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateMessageMentionRead -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.unreadMentionCount = unreadMentionCount
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatReplyMarkup -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.replyMarkupMessageId = replyMarkupMessageId
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatDraftMessage -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.draftMessage = draftMessage
                        updatedChats[chatId] = chatToUpdate
                        setChatPositions(chatToUpdate, positions)
                    }
                }
            }

            is TdApi.UpdateChatPosition -> {
                val updatedChats = chats.replayCache.firstOrNull()?.toMutableMap() ?: mutableMapOf()
                val chat = updatedChats[chatId]?.deepCopy()
                if (chat != null) {
                    var i = 0
                    while (i < chat.positions.size) {
                        if (chat.positions[i].list.constructor == ChatListMain.CONSTRUCTOR) {
                            break
                        }
                        i++
                    }

                    val newPositions =
                        Array(chat.positions.size + (if (position.order == 0L) 0 else 1) - (if (i < chat.positions.size) 1 else 0)) { index ->
                            chat.positions.getOrNull(index) ?: position
                        }

                    var pos = 0
                    if (position.order != 0L) {
                        newPositions[pos++] = position
                    }

                    for (j in chat.positions.indices) {
                        if (j != i) {
                            newPositions[pos++] = chat.positions[j]
                        }
                    }

                    setChatPositions(chat, newPositions)
                    updatedChats[chatId] = chat
                    chats.value = updatedChats
                }
            }

            is TdApi.UpdateChatPermissions -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.permissions = permissions
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatNotificationSettings -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.notificationSettings = notificationSettings
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatDefaultDisableNotification -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.defaultDisableNotification = defaultDisableNotification
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatIsMarkedAsUnread -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.isMarkedAsUnread = isMarkedAsUnread
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatHasScheduledMessages -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.hasScheduledMessages = hasScheduledMessages
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }

            is TdApi.UpdateChatAction -> {
                val updateChatActions = chatActions.value.toMutableMap()
                updateChatActions[chatId] = ChatAction(action, senderId, messageThreadId)
                chatActions.value = updateChatActions
            }

            is TdApi.UpdateChatAccentColors -> {
                updateChatMap { updatedChats ->
                    val chatToUpdate = updatedChats[chatId]?.deepCopy()
                    if (chatToUpdate != null) {
                        chatToUpdate.accentColorId = accentColorId
                        updatedChats[chatId] = chatToUpdate
                    }
                }
            }
            //endregion
        }
    }

    private suspend fun updateChatMap(updateBlock: (MutableMap<Long, Chat>) -> Unit) {
        mutex.withLock {
            val updatedChats = chats.value.toMutableMap()

            // 执行更新操作
            updateBlock(updatedChats)

            // 插入新的值
            chats.value = updatedChats
        }
    }

    private fun setChatPositions(chat: Chat, positions: Array<ChatPosition>) {
        // 获取当前的 chatList 状态
        val currentChatList = chatPositions.value.toMutableMap()
        LogUtils.info("setChatPositions", "${chat.positions.firstOrNull()}")

        // 删除当前 chat 在 ChatListMain 中的记录
        for (position in chat.positions) {
            if (position.list.constructor == ChatListMain.CONSTRUCTOR) {
                //currentChatList.remove(chat.id)   //FIXME 这个很有问题！！！
            }
        }

        // 更新 chat 的 positions
        chat.positions = positions

        // 添加新的 positions 到 chatList
        for (position in chat.positions) {
            if (position.list.constructor == ChatListMain.CONSTRUCTOR) {
                currentChatList[chat.id] = position
            }
        }

        // 更新 MutableStateFlow 的值
        chatPositions.value = currentChatList
        /*// 获取当前的 chatList 状态并创建一个新的可变副本
        val currentChatList = chatPositions.value.toMutableMap()

        // 过滤出不属于 ChatListMain 的位置
        val nonMainPositions = chat.positions.filterNot { it.list.constructor == ChatListMain.CONSTRUCTOR }

        // 更新 chat 的 positions，合并新的 positions
        chat.positions = (nonMainPositions + positions).toTypedArray()

        // 更新 currentChatList 中 chat 的位置
        currentChatList[chat.id] = chat.positions.firstOrNull { it.list.constructor == ChatListMain.CONSTRUCTOR } ?: currentChatList[chat.id] ?: return

        // 更新 MutableStateFlow 的值
        chatPositions.value = currentChatList*/
    }

    data class ChatItem(
        val id: Long,
        var title: String,
        var lastMessage: Message?,
        val photo: TdApi.ChatPhotoInfo?,
        val type: TdApi.ChatType,
        var userStatus: UserStatus?,
        var unreadCount: Int,
        var isMuted: Boolean,
        var chatAction: ChatAction?
    )

    data class ChatAction(
        val action: TdApi.ChatAction,
        val senderId: MessageSender,
        val messageThreadId: Long
    )
}