package cn.spacexc.neogram.utils

import org.apache.commons.lang3.SerializationUtils
import org.drinkless.tdlib.TdApi

fun <T: TdApi.Object> T.deepCopy(): T {
    return SerializationUtils.clone<T>(this)
}