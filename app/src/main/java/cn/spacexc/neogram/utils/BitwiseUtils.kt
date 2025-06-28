/*
 * This file is a part of X-Core
 * Copyright © Vyacheslav Krylov 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.spacexc.neogram.utils

fun hasFlag(flags: Int, flag: Int): Boolean {
    return (flags and flag) != 0
}

fun hasFlag(flags: Long, flag: Long): Boolean {
    return (flags and flag) != 0L
}

fun flagChanged(flags: Long, oldFlags: Long, flag: Long): Boolean {
    return (flags and flag) != (oldFlags and flag)
}

fun hasAllFlags(flags: Int, flag: Int): Boolean {
    return (flags and flag) == flag
}

fun hasAllFlags(flags: Long, flag: Long): Boolean {
    return (flags and flag) == flag
}

fun setFlag(flags: Int, flag: Int, enabled: Boolean): Int {
    var flags = flags
    if (enabled) {
        flags = flags or flag
    } else {
        flags = flags and flag.inv()
    }
    return flags
}

fun setFlag(flags: Long, flag: Long, enabled: Boolean): Long {
    var flags = flags
    if (enabled) {
        flags = flags or flag
    } else {
        flags = flags and flag.inv()
    }
    return flags
}

fun optional(flag: Int, condition: Boolean): Int {
    return if (condition) flag else 0
}

fun hashCode(x: Long): Int {
    return (x xor (x ushr 32)).toInt()
}

fun splitLongToFirstInt(x: Long): Int {
    return (x shr 32).toInt()
}

fun splitLongToSecondInt(x: Long): Int {
    return x.toInt()
}

fun mergeLong(a: Int, b: Int): Long {
    return (a.toLong() shl 32) or (b.toLong() and 0xFFFFFFFFL)
}

fun mergeTimeToInt(hour: Int, minute: Int, second: Int): Int {
    return ((0xff and hour) shl 16) or ((0xff and minute) shl 8) or (0xff and second)
}

fun splitIntToSecond(time: Int): Int {
    return (time and 0xff)
}

fun splitIntToMinute(time: Int): Int {
    return ((time shr 8) and 0xff)
}

fun splitIntToHour(time: Int): Int {
    return ((time shr 16) and 0xff)
}

private fun isAfter(time: Int, afterTime: Int): Boolean {
    return time > afterTime
}

fun belongsToSchedule(time: Int, startTime: Int, endTime: Int): Boolean {
    if (startTime == endTime) {
        return false
    }

    val startHour = splitIntToHour(startTime)
    val startMinute = splitIntToMinute(startTime)
    val startSecond = splitIntToSecond(startTime)

    val endHour = splitIntToHour(endTime)
    val endMinute = splitIntToMinute(endTime)
    val endSecond = splitIntToSecond(endTime)

    val hour = splitIntToHour(time)
    val minute = splitIntToMinute(time)
    val second = splitIntToSecond(time)

    if (hour == startHour && minute == startMinute && second == startSecond) {
        return true
    }
    if (hour == endHour && minute == endMinute && second == endSecond) {
        return false
    }

    //  isAfter(startHour, startMinute, startSecond, endHour, endMinute, endSecond)
    if (isAfter(startHour, endHour)) {
        // 22:00-end || start-7:00
        // return isAfter(hour, minute, second, startHour, startMinute, startSecond) || isAfter(endHour, endMinute, endSecond, hour, minute, second);
        return isAfter(time, startTime) || isAfter(endTime, time)
    } else {
        // 7:00-22:00
        // return isAfter(hour, minute, second, startHour, startMinute, startSecond) && isAfter(endHour, endMinute, endSecond, hour, minute, second);
        return isAfter(time, startTime) && isAfter(endTime, time)
    }
}
