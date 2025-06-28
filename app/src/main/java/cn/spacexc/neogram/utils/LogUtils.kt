package cn.spacexc.neogram.utils

import android.util.Log
import cn.spacexc.neogram.Application.Companion.getApplication
import cn.spacexc.neogram.settings.NeogramSettings
import java.io.File

const val TAG_VOIP = "VOIP"
var logDir = File(getApplication().filesDir, "tglogs")

object LogUtils {
    fun info(prompt: String, content: String) {
        if (NeogramSettings.data.debug) {
            Log.d("Neogram", buildString {
                append(prompt)
                if (content.isNotEmpty()) {
                    append(": $content")
                }
            })
        }
    }

    fun error(prompt: String, content: String) {
        if (NeogramSettings.data.debug) {
            Log.e("Neogram", buildString {
                append(prompt)
                if (content.isNotEmpty()) {
                    append(": $content")
                }
            })
        }
    }
}