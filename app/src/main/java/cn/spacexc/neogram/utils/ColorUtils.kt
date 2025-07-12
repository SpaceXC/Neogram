package cn.spacexc.neogram.utils

import androidx.compose.ui.graphics.Color


fun rgba(r: Int, g: Int, b: Int, a: Float = 1f): Color =
    Color(r, g, b, (a * 255).toInt())