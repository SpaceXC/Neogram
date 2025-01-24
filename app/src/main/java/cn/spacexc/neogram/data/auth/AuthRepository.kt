package cn.spacexc.neogram.data.auth

import android.util.Log
import cn.spacexc.neogram.Application
import cn.spacexc.neogram.data.TdClient
import kotlinx.coroutines.flow.MutableStateFlow
import org.drinkless.tdlib.Client
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.UpdateAuthorizationState

object AuthRepository {
    val authState = MutableStateFlow<AuthState>(AuthState.WaitingParams)

    sealed class AuthState(val dialogHint: String) {
        data object LoggedIn : AuthState("")
        data object EnterPhone : AuthState("Enter phone")
        data object EnterCode : AuthState("Enter code")
        data object WaitingParams : AuthState("App Loading")
        data class WaitingQrCodeScan(val qrCodeLink: String) : AuthState(qrCodeLink)
        data class EnterPassword(val passwordHint: String) :
            AuthState("Enter password. Hint: $passwordHint")

        data class Unknown(val name: String) : AuthState("Unknown State: $name")
    }

    fun TdApi.Object.authHandler() {
        if (this is UpdateAuthorizationState) {
            Log.d("Neogram", "authHandler: $this")
            authState.value = when (authorizationState) {
                is TdApi.AuthorizationStateReady -> AuthState.LoggedIn
                is TdApi.AuthorizationStateWaitCode -> AuthState.EnterCode
                is TdApi.AuthorizationStateWaitPassword -> AuthState.EnterPassword((authorizationState as TdApi.AuthorizationStateWaitPassword).passwordHint)
                is TdApi.AuthorizationStateWaitPhoneNumber -> AuthState.EnterPhone
                is TdApi.AuthorizationStateWaitOtherDeviceConfirmation -> AuthState.WaitingQrCodeScan(
                    (authorizationState as TdApi.AuthorizationStateWaitOtherDeviceConfirmation).link
                )

                is TdApi.AuthorizationStateWaitTdlibParameters -> AuthState.WaitingParams


                else -> AuthState.Unknown(authorizationState.toString())
            }
        }
    }

    init {
        authApp()
    }

    fun authApp() {
        with(Application.getApplication().tdLibParams) {
            TdClient.send(
                TdApi.SetTdlibParameters(
                    false,
                    databaseDirectory,
                    databaseDirectory,
                    null,
                    useFileDatabase,
                    useMessageDatabase,
                    useMessageDatabase,
                    useSecretChats,
                    apiId,
                    apiHash,
                    systemLanguageCode,
                    deviceModel,
                    systemVersion,
                    applicationVersion,

                ),
                { tdObject ->
                    Log.d("NeoGram", ": ${tdObject.toString()}")
                },
                { exception ->
                    exception?.printStackTrace()
                }
            )
        }
    }
}