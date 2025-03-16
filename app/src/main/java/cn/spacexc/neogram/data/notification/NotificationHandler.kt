package cn.spacexc.neogram.data.notification

import android.util.Log
import cn.spacexc.neogram.utils.LogUtils
import org.drinkless.tdlib.TdApi

fun TdApi.Object.notificationHandler() {
    when(this) {
        is TdApi.UpdateActiveNotifications -> {
            LogUtils.info("NOTIFICATION", "$this")
        }
        is TdApi.UpdateNotificationGroup -> {
            LogUtils.info("NOTIFICATION", "$this")
        }
        is TdApi.UpdateNotification -> {
            LogUtils.info("NOTIFICATION", "$this")
        }
        is TdApi.UpdateHavePendingNotifications -> {
            LogUtils.info("NOTIFICATION", "$this")
        }
    }
}