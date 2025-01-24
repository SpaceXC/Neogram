package cn.spacexc.neogram.utils

import android.util.Log

object LogUtils {
    fun info(prompt: String, content: String) {
        Log.d("Neogram", buildString {
            append(prompt)
            if(content.isNotEmpty()) {
                append(": $content")
            }
        })
    }
}