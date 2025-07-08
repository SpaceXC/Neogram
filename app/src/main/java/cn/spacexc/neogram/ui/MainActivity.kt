package cn.spacexc.neogram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.ui.component.DraggableBox
import cn.spacexc.neogram.ui.screen.auth.AuthScreen
import cn.spacexc.neogram.ui.screen.call.VoiceCallScreen
import cn.spacexc.neogram.ui.screen.chats.ChatListScreen
import cn.spacexc.neogram.ui.screen.forward.ForwardMessageScreen
import cn.spacexc.neogram.ui.screen.image.ImageViewerScreen
import cn.spacexc.neogram.ui.screen.messages.MessagesScreen
import cn.spacexc.neogram.ui.screen.messages.actions.MessageActionScreen
import cn.spacexc.neogram.ui.screen.messages.actions.MessageActionsScreen
import cn.spacexc.neogram.ui.screen.messages.link.LinkPreviewScreen
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.screen.splash.SplashScreen
import cn.spacexc.neogram.ui.screen.test.UITestScreen
import cn.spacexc.neogram.ui.screen.video.VideoPlayerScreen
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.OptionValueBoolean

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val systemUiController: SystemUiController = rememberSystemUiController()
            LaunchedEffect(Unit) {
                systemUiController.isStatusBarVisible = false // Status bar
                systemUiController.isNavigationBarVisible = false // Navigation bar
                systemUiController.isSystemBarsVisible = false // Status & Navigation bars
            }

            DraggableBox(
                modifier = Modifier.fillMaxWidth(),
                threshold = 50f,
                onProgressChange = {

                },
                triggerThreshold = 50f * 1.1f,
                onTriggered = {
                    navController.navigateUp()
                }
            ) {
                SharedTransitionLayout {
                    NavHost(
                        navController,
                        startDestination = SplashScreen,
                        enterTransition = {
                            slideInHorizontally(tween(400, 0)) { it } + fadeIn(tween(400))
                        },
                        exitTransition = {
                            try {
                                targetState.toRoute<LinkPreviewScreen>()
                                fadeOut(tween(300))
                            } catch (_: Exception) {
                                slideOutHorizontally(tween(300, 0)) { -it } + fadeOut(tween(300))
                            }
                        },
                        popEnterTransition = {
                            try {
                                initialState.toRoute<LinkPreviewScreen>()
                                fadeIn(tween(300))
                            } catch (_: Exception) {
                                slideInHorizontally(tween(400, 0)) { -it } + fadeIn(tween(400))
                            }
                        },
                        popExitTransition = {
                            slideOutHorizontally(tween(300, 0)) { it } + fadeOut(tween(300))
                        }
                    ) {
                        composable<SplashScreen> {
                            SplashScreen(navController)
                        }
                        composable<AuthScreen> {
                            AuthScreen(navController)
                        }
                        composable<ChatListScreen> {
                            ChatListScreen(this, navController)
                        }
                        composable<MessagesScreen> {
                            val (chatId, title, haveUnreadMessages) = it.toRoute<MessagesScreen>()
                            MessagesScreen(
                                navController = navController,
                                chatId = chatId,
                                title = title,
                                haveUnreadMessages = haveUnreadMessages,
                                animatedContentScope = this
                            )
                        }
                        composable<SendMessageScreen> {
                            SendMessageScreen(it.toRoute<SendMessageScreen>(), navController)
                        }
                        composable<ImageViewerScreen> {
                            val (imagePath, id) = it.toRoute<ImageViewerScreen>()
                            ImageViewerScreen(
                                this,
                                navController,
                                imagePath,
                                id
                            )
                        }
                        composable<LinkPreviewScreen> {
                            val (title, url, description, red, green, blue, messageId) = it.toRoute<LinkPreviewScreen>()
                            LinkPreviewScreen(
                                title,
                                url,
                                description,
                                Color(red, green, blue),
                                messageId,
                                this,
                                navController
                            )
                        }

                        composable<MessageActionScreen> {
                            val (chatId, messageId) = it.toRoute<MessageActionScreen>()
                            MessageActionsScreen(
                                chatId,
                                messageId,
                                navController,
                                this
                            )
                        }

                        composable<UITestScreen> {
                            UITestScreen()
                        }

                        composable<VoiceCallScreen> {
                            VoiceCallScreen(navController)
                        }

                        composable<VideoPlayerScreen> {
                            VideoPlayerScreen(
                                navController,
                                it.toRoute<VideoPlayerScreen>().videoPath
                            )
                        }

                        composable<ForwardMessageScreen> {
                            val (threadId, chatId, messageId) = it.toRoute<ForwardMessageScreen>()
                            ForwardMessageScreen(
                                navController,
                                this,
                                messageThreadId = threadId,
                                messageChatId = chatId,
                                messageId = messageId
                            )
                        }
                    }
                }
            }

            LaunchedEffect(Unit) {
                CallHandler.currentCallId.collect {
                    if (it == 0) navController.navigateUp() else navController.navigate(
                        VoiceCallScreen
                    )
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