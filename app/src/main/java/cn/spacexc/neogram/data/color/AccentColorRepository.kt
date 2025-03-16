package cn.spacexc.neogram.data.color

import androidx.compose.ui.graphics.Color
import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi

object AccentColorRepository {
    val colors = MutableStateFlow(mapOf<Int, AccentColor>())
    val availableIds = MutableStateFlow(listOf<Int>())

    fun TdApi.Object.accentColorsHandler() {
        when (this) {
            is TdApi.UpdateAccentColors -> {
                AccentColorRepository.colors.value = colors.map {
                    Pair(
                        it.id,
                        AccentColor(
                            Color(it.darkThemeColors[0]).copy(alpha = 1f),
                            Color(it.darkThemeColors[0]).copy(alpha = 1f),
                            Color(it.darkThemeColors[0]).copy(alpha = 1f)
                        )
                    )
                }.toMap()
                LogUtils.info("AccentColors", "${AccentColorRepository.colors.value}")
                availableIds.value = availableAccentColorIds.toList()
            }
        }
    }

    data class AccentColor(
        val nameColor: Color,   // 名称颜色
        val backgroundColor: Color, // 背景颜色
        val background2Color: Color // 第二背景颜色（渐变色）
    )

    val defaultColor = listOf(
        // Red colors
        AccentColor(
            nameColor = Color(0xFFCC5049),
            backgroundColor = Color(0xFFFF845E),
            background2Color = Color(0xFFD45246)
        ),
        // Orange colors
        AccentColor(
            nameColor = Color(0xFFD67722),
            backgroundColor = Color(0xFFFEBB5B),
            background2Color = Color(0xFFF68136)
        ),
        // Violet colors
        AccentColor(
            nameColor = Color(0xFF955CDB),
            backgroundColor = Color(0xFFB694F9),
            background2Color = Color(0xFF6C61DF)
        ),
        // Green colors
        AccentColor(
            nameColor = Color(0xFF40A920),
            backgroundColor = Color(0xFF9AD164),
            background2Color = Color(0xFF46BA43)
        ),
        // Cyan colors
        AccentColor(
            nameColor = Color(0xFF309EBA),
            backgroundColor = Color(0xFF5BCBE3),
            background2Color = Color(0xFF359AD4)
        ),
        // Blue colors
        AccentColor(
            nameColor = Color(0xFF368AD1),
            backgroundColor = Color(0xFF5CAFFA),
            background2Color = Color(0xFF408ACF)
        ),
        // Pink colors
        AccentColor(
            nameColor = Color(0xFFC7508B),
            backgroundColor = Color(0xFFFF8AAC),
            background2Color = Color(0xFFD95574)
        )
    )

    fun getAccentColor(index: Int): AccentColor? {
        return if (index in 0..6) defaultColor[index] else colors.value[index]
    }
}