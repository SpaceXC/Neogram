package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import cn.spacexc.neogram.ui.screen.link.LinkProcessScreen
import cn.spacexc.neogram.ui.screen.messages.link.LinkPreviewScreen
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.ui.component.modifier.clickVfx
import org.drinkless.tdlib.TdApi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LinkPreviewCard(
    preview: TdApi.LinkPreview,
    animatedContentScope: AnimatedContentScope,
    navController: NavController,
    modifier: Modifier = Modifier,
    senderColor: Color,
    messageId: Long,
    isMinimalist: Boolean = true
) {
    val localDensity = LocalDensity.current
    var textHeight by remember { mutableStateOf(0.dp) }
    Box(
        modifier = modifier
            .sharedElement(rememberSharedContentState("$messageId linkBox"), animatedContentScope)
            .clickVfx(onClick = {
                navController.navigate(LinkProcessScreen(preview.url))
            }, onLongClick = {
                navController.navigate(LinkPreviewScreen(
                    preview.title, preview.displayUrl, preview.description.text, senderColor.red, senderColor.green, senderColor.blue, messageId
                ))
            })
            .background(senderColor.copy(alpha = 0.3f), RoundedCornerShape(topStart = 6.dp, topEnd = 13.dp, bottomStart = 13.dp, bottomEnd = 13.dp))
            .padding(horizontal = 10.dp, vertical = 12.dp)
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
                        (if (isMinimalist) senderColor else Color.White),
                        CircleShape
                    )
                    .sharedBounds(rememberSharedContentState("$messageId linkLeading"), animatedContentScope)
            )
            Column(modifier = Modifier.onSizeChanged {
                textHeight = with(localDensity) { it.height.toDp() }
            }) {
                Text(
                    preview.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontFamily = miSans,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.sharedBounds(rememberSharedContentState("$messageId linkTitle"), animatedContentScope)
                )
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.offset(x = (-2).dp)) {
                    Icon(
                        NeogramIcons.ExternalLink,
                        tint = senderColor,
                        contentDescription = null,
                        modifier = Modifier.size(with(localDensity) { 14.sp.toDp() }).sharedBounds(rememberSharedContentState("$messageId linkIcon"), animatedContentScope)
                    )
                    Text(
                        preview.displayUrl,
                        color = senderColor,
                        fontSize = 12.sp,
                        fontFamily = miSans,
                        maxLines = 1,
                        modifier = Modifier.sharedBounds(rememberSharedContentState("$messageId linkUrl"), animatedContentScope),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}