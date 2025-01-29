package cn.spacexc.neogram.utils

import org.drinkless.tdlib.TdApi

val TdApi.User.username: String
    get() = "$firstName $lastName"