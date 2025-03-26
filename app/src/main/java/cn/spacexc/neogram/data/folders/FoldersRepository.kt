package cn.spacexc.neogram.data.folders

import cn.spacexc.neogram.data.TdClient
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi

object FoldersRepository {
    val chatFoldersInfo = MutableStateFlow(emptyList<TdApi.ChatFolderInfo>())

    val chatFolderLists = MutableStateFlow(mapOf<Int, TdApi.ChatFolder>())

    fun TdApi.Object.foldersHandler() {
        when(this) {
            is TdApi.UpdateChatFolders -> {
                chatFoldersInfo.value = chatFolders.toList()

                val newMap = mutableMapOf<Int, TdApi.ChatFolder>()
                chatFolders.forEach { folderInfo ->
                    TdClient.send(TdApi.GetChatFolder(folderInfo.id), {
                        if (it is TdApi.ChatFolder) {
                            newMap[folderInfo.id] = it
                            chatFolderLists.value = newMap
                        }
                    }, {

                    })
                }
            }
        }
    }
}