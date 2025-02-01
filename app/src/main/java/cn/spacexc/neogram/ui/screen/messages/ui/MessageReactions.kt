package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.runtime.Composable
import org.drinkless.tdlib.TdApi

@Composable
fun MessageReactions(interactionInfo: TdApi.MessageInteractionInfo) {
    interactionInfo.reactions?.reactions?.forEach { reaction ->
        //if (reaction.usedSenderId)
    }
}

@Composable
fun ReactionBadge() {

}