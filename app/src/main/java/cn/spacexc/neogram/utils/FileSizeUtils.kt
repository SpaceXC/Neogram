package cn.spacexc.neogram.utils

import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow

fun Long.formatFileSize(): String {
    if (this <= 0L) return "0 B"

    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(this.toDouble()) / log10(1024.0)).toInt()
    return DecimalFormat("#,##0.#").format(this / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
}