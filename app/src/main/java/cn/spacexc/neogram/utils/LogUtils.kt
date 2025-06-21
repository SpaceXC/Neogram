package cn.spacexc.neogram.utils

import android.util.Log
import cn.spacexc.neogram.settings.NeogramSettings

const val TAG_VOIP = "VOIP"

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