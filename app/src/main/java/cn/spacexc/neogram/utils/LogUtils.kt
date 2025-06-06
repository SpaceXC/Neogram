package cn.spacexc.neogram.utils

import android.util.Log

const val TAG_VOIP = "VOIP"

object LogUtils {
    fun info(prompt: String, content: String) {
        //return
        Log.d("Neogram", buildString {
            append(prompt)
            if(content.isNotEmpty()) {
                append(": $content")
            }
        })
    }

    fun error(prompt: String, content: String) {
        //return
        Log.e("Neogram", buildString {
            append(prompt)
            if (content.isNotEmpty()) {
                append(": $content")
            }
        })
    }
}