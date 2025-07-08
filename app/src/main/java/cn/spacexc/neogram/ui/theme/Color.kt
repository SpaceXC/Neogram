package cn.spacexc.neogram.ui.theme

import androidx.compose.ui.graphics.Color

val NeoBlue = Color(255, 102, 44) //Color(51, 144, 236, 255)    //主题色
val NeoRed = Color(221, 46, 68, 255)
val BubbleGray = Color(29, 29, 29, 255)
val CardGray = Color(18, 18, 18, 255)
val BadgeGray = Color(53, 53, 53, 212)
val InputBarGray = Color(18, 18, 18, 255)

fun parseColor(hex: String) = Color(android.graphics.Color.parseColor(hex))