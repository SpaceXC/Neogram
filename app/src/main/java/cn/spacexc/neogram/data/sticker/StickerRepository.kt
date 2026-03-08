package cn.spacexc.neogram.data.sticker

import cn.spacexc.neogram.data.TdClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi

object StickerRepository {

    val stickerSets = MutableStateFlow<Map<Long, TdApi.StickerSet?>>(emptyMap())
    val stickerSetsInfo = MutableStateFlow<List<TdApi.StickerSetInfo>>(emptyList())

    init {
        TdClient.send(TdApi.GetInstalledStickerSets(), {
            if (it is TdApi.StickerSets) {
                it.sets.forEach { stickerSetInfo ->
                    stickerSetsInfo.value = it.sets.toList()

                }
            }
        })
    }

    fun TdApi.Object.stickersHandler() {
        when (this) {
            is TdApi.UpdateRecentStickers -> {

            }

            is TdApi.UpdateInstalledStickerSets -> {

            }

            is TdApi.UpdateStickerSet -> {
                val temp = stickerSets.value.toMutableMap()
                temp[stickerSet.id] = stickerSet
                stickerSets.value = temp
            }
        }
    }
}