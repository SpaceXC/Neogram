package cn.spacexc.neogram.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cn.spacexc.neogram.Application
import cn.spacexc.neogram.data.TdClient
import cn.spacexc.neogram.data.call.CallHandler
import cn.spacexc.neogram.ui.component.DraggableBox
import cn.spacexc.neogram.ui.screen.auth.AuthScreen
import cn.spacexc.neogram.ui.screen.call.VoiceCallScreen
import cn.spacexc.neogram.ui.screen.chats.ChatListScreen
import cn.spacexc.neogram.ui.screen.forward.ForwardMessageScreen
import cn.spacexc.neogram.ui.screen.image.ImageViewerScreen
import cn.spacexc.neogram.ui.screen.link.LinkProcessScreen
import cn.spacexc.neogram.ui.screen.lock.LockScreen
import cn.spacexc.neogram.ui.screen.messages.MessagesScreen
import cn.spacexc.neogram.ui.screen.messages.actions.MessageActionScreen
import cn.spacexc.neogram.ui.screen.messages.actions.MessageActionsScreen
import cn.spacexc.neogram.ui.screen.messages.sticker.StickersScreen
import cn.spacexc.neogram.ui.screen.messages.link.LinkPreviewScreen
import cn.spacexc.neogram.ui.screen.messages.send.SendMessageScreen
import cn.spacexc.neogram.ui.screen.settings.main.SettingsScreen
import cn.spacexc.neogram.ui.screen.settings.sessions.SessionsScreen
import cn.spacexc.neogram.ui.screen.splash.SplashScreen
import cn.spacexc.neogram.ui.screen.test.UITestScreen
import cn.spacexc.neogram.ui.screen.video.VideoPlayerScreen
import cn.spacexc.neogram.ui.theme.CardGray
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.miSans
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
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
                                try {
                                    targetState.toRoute<SendMessageScreen>()
                                    fadeOut(tween(300))
                                } catch (_: Exception) {
                                    slideOutHorizontally(
                                        tween(
                                            300,
                                            0
                                        )
                                    ) { -it } + fadeOut(tween(300))
                                }
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
                            SendMessageScreen(it.toRoute<SendMessageScreen>(), this, navController)
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
                            val (chatId, messageId, isGroupChat, isRead) = it.toRoute<MessageActionScreen>()
                            MessageActionsScreen(
                                chatId = chatId,
                                messageId = messageId,
                                navController = navController,
                                animatedContentScope = this,
                                isGroupChat = isGroupChat,
                                isRead = isRead,
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

                        composable<SessionsScreen> {
                            SessionsScreen(navController)
                        }

                        composable<LockScreen> {
                            LockScreen(navController)
                        }

                        composable<SettingsScreen> {
                            SettingsScreen(navController)
                        }

                        composable<StickersScreen> {
                            StickersScreen(navController)
                        }

                        composable<LinkProcessScreen> {
                            val (link) = it.toRoute<LinkProcessScreen>()
                            LinkProcessScreen(navController, link)
                        }
                    }
                }
            }

            /*Box(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(
                    !Application.toastContent.isEmpty(), modifier = Modifier.align(Alignment.BottomCenter),
                    enter = fadeIn() + slideInVertically { it / 2 },
                    exit = fadeOut() + slideOutVertically { it / 2 }
                ) {
                    LaunchedEffect(Unit) {
                        delay(1500)
                        Application.toastContent = ""
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(
                                CardGray, RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 0.1.dp, brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        NeoMain, Color.Transparent, Color.Transparent
                                    )
                                ), shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Text(
                            Application.toastContent,
                            color = Color.White,
                            fontFamily = miSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                        )
                    }
                }
            }*/

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