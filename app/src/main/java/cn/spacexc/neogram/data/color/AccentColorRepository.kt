package cn.spacexc.neogram.data.color

import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi

object AccentColorRepository {
    val colors = MutableStateFlow(mapOf<Int, TdApi.AccentColor>())
    val availableIds = MutableStateFlow(listOf<Int>())

    fun TdApi.Object.accentColorsHandler() {
        when (this) {
            is TdApi.UpdateAccentColors -> {
                AccentColorRepository.colors.value = colors.map { Pair(it.id, it) }.toMap()
                LogUtils.info("AccentColors", "${AccentColorRepository.colors.value}")
                availableIds.value = availableAccentColorIds.toList()
            }
            /*is TdApi.UpdateProfileAccentColors -> {
                LogUtils.info("AccentColors", "${this.colors.toList()}")
            }*/
        }
    }
}