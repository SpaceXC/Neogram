package cn.spacexc.neogram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.ui.screen.auth.AuthScreen
import cn.spacexc.neogram.ui.screen.chats.ChatListScreen
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.OptionValueBoolean

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController,
                startDestination = AuthScreen,
                enterTransition = {
                    slideInHorizontally(tween(400, 0)) { it } + fadeIn(tween(400))
                },
                exitTransition = {
                    slideOutHorizontally(tween(400, 150)) { -it } + fadeOut(tween(400))
                },
                popEnterTransition = {
                    slideInHorizontally(tween(400, 50)) { -it } + fadeIn(tween(400))
                },
                popExitTransition = {
                    slideOutHorizontally(tween(300, 0)) { it } + fadeOut(tween(300))
                }
            ) {
                composable<AuthScreen> {
                    AuthScreen(navController)
                }
                composable<ChatListScreen> {
                    ChatListScreen(navController)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        TdClient.send(TdApi.SetOption("online", OptionValueBoolean(true)))
    }

    override fun onDestroy() {
        super.onDestroy()
        TdClient.send(TdApi.SetOption("online", OptionValueBoolean(false)))
    }

    override fun onPause() {
        super.onPause()
        TdClient.send(TdApi.SetOption("online", OptionValueBoolean(false)))
    }
}