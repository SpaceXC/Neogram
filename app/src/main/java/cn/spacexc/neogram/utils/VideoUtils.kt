package cn.spacexc.neogram.utils

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever

fun getVideoFirstFrame(videoPath: String): Bitmap? {
    val retriever = MediaMetadataRetriever()
    return try {
        retriever.setDataSource(videoPath)
        // 获取第一帧（时间戳为0微秒）
        retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        retriever.release()
    }
}