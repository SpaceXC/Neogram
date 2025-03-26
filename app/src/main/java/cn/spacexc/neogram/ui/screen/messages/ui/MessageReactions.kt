package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.theme.NeoBlue
import org.drinkless.tdlib.TdApi
import androidx.compose.runtime.getValue
import cn.spacexc.neogram.ui.component.TgImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MessageReactions(interactionInfo: TdApi.MessageInteractionInfo) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.offset(x = (-0.5).dp)
    ) {
        interactionInfo.reactions?.reactions?.forEach { reaction ->
            ReactionBadge(reaction)
        }
    }
}

@Composable
fun ReactionBadge(reaction: TdApi.MessageReaction) {
    val users by UserRepository.users.collectAsState()
    Box(
        modifier = Modifier
            .background(NeoBlue.copy(alpha = 0.5f), CircleShape)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        if (reaction.type is TdApi.ReactionTypeEmoji) {
            Text((reaction.type as TdApi.ReactionTypeEmoji).emoji, fontSize = 10.sp)
        }
        if (reaction.type is TdApi.ReactionTypeCustomEmoji) {
            Text((reaction.type as TdApi.ReactionTypeEmoji).emoji, fontSize = 10.sp)
        }

        if (reaction.type is TdApi.ReactionTypePaid) {
            Text((reaction.type as TdApi.ReactionTypeEmoji).emoji, fontSize = 10.sp)
        }
        /*Box(modifier = Modifier) {
            reaction.recentSenderIds.filter { it is TdApi.MessageSenderUser }.forEach {
                val user = users[(it as TdApi.MessageSenderUser).userId]?.tgUser
                user?.let {
                    if (user.profilePhoto != null) {
                        TgImage(user.profilePhoto!!.small, user.profilePhoto!!.minithumbnail!!.data) {}
                    }
                }
            }
        }*/
    }
}