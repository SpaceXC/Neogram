package cn.spacexc.neogram.utils

import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

fun ExoPlayer.currentProgressFlow() = flow {
    while (true) {
        emit(this@currentProgressFlow.currentPosition)
        delay(100)
    }
}