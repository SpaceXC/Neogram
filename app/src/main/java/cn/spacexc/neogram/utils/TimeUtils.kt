package cn.spacexc.neogram.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatTimestamp(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000 // 将时间戳转换为毫秒

    val now = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    // 获取时间差（单位：分钟）
    val minutesAgo = (now.timeInMillis - calendar.timeInMillis) / (1000 * 60)

    return when {
        // 判断是否在半小时以内
        minutesAgo < 1 -> "刚刚"
        minutesAgo < 30 -> "$minutesAgo 分钟之前"

        // 判断是否是今天
        isToday(calendar, now) -> dateFormatter.format(calendar.time)

        // 判断是否是昨天
        isYesterday(calendar, now) -> "昨天 ${dateFormatter.format(calendar.time)}"

        // 判断是否是前天
        isBeforeYesterday(calendar, now) -> "前天 ${dateFormatter.format(calendar.time)}"

        // 判断是否是一周之内
        isWithinOneWeek(calendar, now) -> {
            val daysAgo = (now.timeInMillis - calendar.timeInMillis) / (1000 * 60 * 60 * 24)
            "$daysAgo 天前 ${dateFormatter.format(calendar.time)}"
        }

        else -> SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(calendar.time)
    }
}

fun isToday(calendar: Calendar, now: Calendar): Boolean {
    return calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR) &&
            calendar.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)
}

fun isYesterday(calendar: Calendar, now: Calendar): Boolean {
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_YEAR, -1)
    return calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
            calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)
}

fun isBeforeYesterday(calendar: Calendar, now: Calendar): Boolean {
    val beforeYesterday = Calendar.getInstance()
    beforeYesterday.add(Calendar.DAY_OF_YEAR, -2)
    return calendar.get(Calendar.YEAR) == beforeYesterday.get(Calendar.YEAR) &&
            calendar.get(Calendar.DAY_OF_YEAR) == beforeYesterday.get(Calendar.DAY_OF_YEAR)
}

fun isWithinOneWeek(calendar: Calendar, now: Calendar): Boolean {
    val oneWeekAgo = Calendar.getInstance()
    oneWeekAgo.add(Calendar.WEEK_OF_YEAR, -1)
    return calendar.after(oneWeekAgo) && calendar.before(now)
}