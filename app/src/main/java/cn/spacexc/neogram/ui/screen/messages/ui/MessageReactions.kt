package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.data.chat.ChatListRepository
import cn.spacexc.neogram.data.user.UserRepository
import cn.spacexc.neogram.ui.component.TgChatAvatar
import cn.spacexc.neogram.ui.component.TgUserAvatar
import cn.spacexc.neogram.ui.theme.NeoMain
import org.drinkless.tdlib.TdApi

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
    val chats by ChatListRepository.chats.collectAsState()
    val localDensity = LocalDensity.current
    val size = remember { with(localDensity) { 18.sp.toDp() } }
    Row(
        modifier = Modifier

            .height(size + 6.dp)
            .background(NeoMain.copy(alpha = 0.5f), CircleShape)
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (reaction.type is TdApi.ReactionTypeEmoji) {
            Text((reaction.type as TdApi.ReactionTypeEmoji).emoji, fontSize = 12.sp)
        }
        if (reaction.type is TdApi.ReactionTypeCustomEmoji) {
            Text(
                (reaction.type as TdApi.ReactionTypeCustomEmoji).customEmojiId.toString(),
                fontSize = 12.sp
            )
        }

        /*if (reaction.type is TdApi.ReactionTypePaid) {
            Text((reaction.type as TdApi.ReactionTypePaid), fontSize = 10.sp)
        }*/

        if (reaction.recentSenderIds.isNotEmpty()) {
            Spacer(Modifier.width(2.dp))

            Box(
                modifier = Modifier.width(
                    size * reaction.recentSenderIds.size.toFloat() - size * 0.4f * (reaction.recentSenderIds.size - 1)
                )//.background(Color.White)
            ) {
                reaction.recentSenderIds.forEachIndexed { index, senderId ->
                    if (senderId is TdApi.MessageSenderUser) {
                        val user = remember {
                            users[senderId.userId]?.tgUser!!
                        }
                        /*Text(
                            user.firstName ?: "",
                            fontFamily = miSans,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 2.dp),
                            color = Color.White
                        )*/
                        TgUserAvatar(
                            modifier = Modifier.offset(x = size * index * 0.6f),
                            avatarSize = size,
                            user = user
                        )
                    }

                    if (senderId is TdApi.MessageSenderChat) {
                        val chat = remember {
                            chats[senderId.chatId]!!
                        }
                        TgChatAvatar(
                            modifier = Modifier.offset(x = size * index * 0.6f),
                            avatarSize = size,
                            chat = chat
                        )
                    }

                }
            }
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