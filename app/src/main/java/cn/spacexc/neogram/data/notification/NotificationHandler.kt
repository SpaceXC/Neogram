package cn.spacexc.neogram.data.notification

import android.util.Log
import org.drinkless.tdlib.TdApi

fun TdApi.Object.notificationHandler() {
    when(this) {
        is TdApi.UpdateActiveNotifications -> {
            Log.d("NOTIFICATION", "notificationHandler: $this")
        }
        is TdApi.UpdateNotificationGroup -> {
            Log.d("NOTIFICATION", "notificationHandler: $this")
        }
        is TdApi.UpdateNotification -> {
            Log.d("NOTIFICATION", "notificationHandler: $this")
        }
        is TdApi.UpdateHavePendingNotifications -> {
            Log.d("NOTIFICATION", "notificationHandler: $this")
        }
    }
}