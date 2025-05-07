package cn.spacexc.neogram.utils

import org.drinkless.tdlib.TdApi

fun <T: TdApi.Object> T.deepCopy(): T {
    return this//SerializationUtils.clone<T>(this)
}