package cn.spacexc.neogram.data.call

import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.connection.ConnectionStateRepository
import cn.spacexc.neogram.utils.LogUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import org.drinkless.tdlib.TdApi
import org.thunderdog.challegram.voip.ConnectionStateListener
import org.thunderdog.challegram.voip.VoIP
import org.thunderdog.challegram.voip.VoIPInstance
import org.thunderdog.challegram.voip.annotation.CallState

object CallHandler {
    val currentCallId = MutableStateFlow(0)
    val currentCall = MutableStateFlow<TdApi.Call?>(null)

    var currentInstance = MutableStateFlow<VoIPInstance?>(null)

    fun TdApi.Object.callHandler() {
        when (this) {
            is TdApi.UpdateCall -> {
                LogUtils.info("HIHIHIHI", this.toString())
                currentCall.value = call
                when (call.state) {
                    is TdApi.CallStatePending -> {
                        currentCallId.value = call.id
                        currentCall.value = call
                    }

                    is TdApi.CallStateReady -> {
                        val stateListener: ConnectionStateListener =
                            object : ConnectionStateListener {
                                override fun onConnectionStateChanged(
                                    context: VoIPInstance,
                                    @CallState newState: Int
                                ) {
                                    LogUtils.info("onConnectionStateChanged", "$newState")
                                    /*if (newState == CallState.ESTABLISHED) {
                                        tdlib.dispatchCallStateChanged(call.id, newState)
                                    } else if (newState == CallState.FAILED) {
                                        val connectionId = context.getConnectionId()
                                        tdlib.context().calls().hangUp(tdlib, call.id, true, connectionId)
                                    }*/
                                }

                                override fun onSignalBarCountChanged(newCount: Int) {
                                    //tdlib.dispatchCallBarsCount(call.id, newCount)
                                }

                                override fun onSignallingDataEmitted(data: ByteArray?) {
                                    TdClient
                                        .send(TdApi.SendCallSignalingData(call.id, data), {
                                            LogUtils.info("SendCallSignalingData", "$it")
                                        })
                                }
                            }

                        val temp = VoIP.instantiateAndConnect(
                            call,
                            call.state as TdApi.CallStateReady?,
                            stateListener,
                            false,
                            null,
                            ConnectionStateRepository.connectionState.value.ordinal,
                            true,
                            1,
                            false
                        )
                        currentInstance.value = temp
                    }

                    /**
                     * CallStatePending.CONSTRUCTOR,
                     * CallStateExchangingKeys.CONSTRUCTOR,
                     * CallStateReady.CONSTRUCTOR,
                     * CallStateHangingUp.CONSTRUCTOR,
                     * CallStateDiscarded.CONSTRUCTOR,
                     * CallStateError.CONSTRUCTOR
                     */

                    is TdApi.CallStateExchangingKeys -> {
                        LogUtils.info("STATE", "$this")

                        //currentInstance?.initializeAndConnect()
                        //TdClient.send()
                    }

                    is TdApi.CallStateHangingUp -> {
                        LogUtils.info("STATE", "$this")
                        currentCallId.value = 0
                        currentCall.value = null
                        currentInstance.value = null
                    }

                    is TdApi.CallStateDiscarded -> {
                        LogUtils.info("STATE", "$this")
                        currentCallId.value = 0
                        currentCall.value = null
                        currentInstance.value = null
                    }

                    is TdApi.CallStateError -> {
                        LogUtils.info("STATE", "$this")
                        currentCallId.value = 0
                        currentCall.value = null
                        currentInstance.value = null
                    }
                }
            }

            is TdApi.UpdateNewCallSignalingData -> {
                currentInstance.value?.handleIncomingSignalingData(data)
                TdClient
                    .send(TdApi.SendCallSignalingData(currentCallId.value, data), {
                        LogUtils.info("SendCallSignalingData", "$it")
                    })
            }
        }
    }
}

fun VoIPInstance.durationFlow() = flow {
    while (true) {
        emit(callDuration)
        delay(100)
    }
}