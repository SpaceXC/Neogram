package cn.spacexc.neogram.data.call

import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.TdApi

object CallHandler {
    val currentCallId = MutableStateFlow(0)

    fun TdApi.Object.callHandler() {
        when (this) {
            is TdApi.UpdateCall -> {
                LogUtils.info("HIHIHIHI", this.toString())
                when (call.state) {
                    is TdApi.CallStatePending -> {
                        currentCallId.value = call.id
                    }

                    else -> {
                        currentCallId.value = 0
                    }
                }
            }
        }
    }
}