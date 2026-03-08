package cn.spacexc.neogram.ui.screen.messages.link

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.icons.ExternalLink
import cn.spacexc.neogram.ui.icons.NeogramIcons
import cn.spacexc.neogram.ui.theme.TitleFrame
import cn.spacexc.neogram.ui.theme.miSans
import kotlinx.serialization.Serializable

@Serializable
data class LinkPreviewScreen(
    val title: String,
    val url: String,
    val description: String,
    val colorRed: Float,
    val colorGreen: Float,
    val colorBlue: Float,
    val messageId: Long
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LinkPreviewScreen(
    title: String,
    url: String,
    description: String,
    color: Color,
    messageId: Long,
    animatedContentScope: AnimatedContentScope,
    navController: NavController
) {
    val localDensity = LocalDensity.current
    var textHeight by remember { mutableStateOf(0.dp) }
    TitleFrame(title = "", timeText = "", onTitleClicked = {}, onActionClicked = navController::navigateUp) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = {
                    navController.navigateUp()
                })
                .verticalScroll(rememberScrollState())
                .padding(top = it, bottom = 10.dp)
                .padding(horizontal = 8.dp),
        ) {
            Column(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState("$messageId linkBox"),
                        animatedContentScope
                    )
                    .background(
                        color.copy(alpha = 0.3f),
                        RoundedCornerShape(
                            topStart = 6.dp,
                            topEnd = 13.dp,
                            bottomStart = 13.dp,
                            bottomEnd = 13.dp
                        )
                    )
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .width(2.5.dp)
                            .height(textHeight)
                            .background(
                                color,
                                CircleShape
                            )
                            .sharedBounds(
                                rememberSharedContentState("$messageId linkLeading"),
                                animatedContentScope
                            )
                    )
                    Column(modifier = Modifier.onSizeChanged {
                        textHeight = with(localDensity) { it.height.toDp() }
                    }) {
                        Text(
                            title,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontFamily = miSans,
                            modifier = Modifier.sharedBounds(
                                rememberSharedContentState("$messageId linkTitle"),
                                animatedContentScope
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.offset(x = (-2).dp)
                        ) {
                            Icon(
                                NeogramIcons.ExternalLink,
                                tint = color,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(with(localDensity) { 14.sp.toDp() })
                                    .sharedBounds(
                                        rememberSharedContentState("$messageId linkIcon"),
                                        animatedContentScope
                                    )
                            )
                            Text(
                                url,
                                color = color,
                                fontSize = 12.sp,
                                fontFamily = miSans,
                                maxLines = 1,
                                modifier = Modifier.sharedBounds(
                                    rememberSharedContentState("$messageId linkUrl"),
                                    animatedContentScope
                                ),
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                Text(description, fontFamily = miSans, fontSize = 13.sp, color = Color.White)
            }
        }
    }
}