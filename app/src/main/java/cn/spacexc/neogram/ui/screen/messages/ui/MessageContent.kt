package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.component.TgAnimation
import cn.spacexc.neogram.ui.component.TgImage
import cn.spacexc.neogram.ui.component.TgRichText
import cn.spacexc.neogram.ui.component.TgSticker
import cn.spacexc.neogram.ui.component.TgVideo
import cn.spacexc.neogram.ui.component.TgVoiceNote
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.utils.textDescription
import org.drinkless.tdlib.TdApi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MessageContent(
    animatedContentScope: AnimatedContentScope,
    content: TdApi.MessageContent,
    messageId: Long,
    users: Map<Long, TdApi.User>,
    navController: NavController,
    senderColor: Color,
    fontSize: TextUnit = 14.sp
) {
    /**
     * MessageText.CONSTRUCTOR, //WIP
     * MessageAnimation.CONSTRUCTOR,
     * MessageAudio.CONSTRUCTOR,
     * MessageDocument.CONSTRUCTOR,
     * MessagePaidMedia.CONSTRUCTOR,
     * MessagePhoto.CONSTRUCTOR,
     * MessageSticker.CONSTRUCTOR,
     * MessageVideo.CONSTRUCTOR,
     * MessageVideoNote.CONSTRUCTOR,
     * MessageVoiceNote.CONSTRUCTOR,
     * MessageExpiredPhoto.CONSTRUCTOR,
     * MessageExpiredVideo.CONSTRUCTOR,
     * MessageExpiredVideoNote.CONSTRUCTOR,
     * MessageExpiredVoiceNote.CONSTRUCTOR,
     * MessageLocation.CONSTRUCTOR,
     * MessageVenue.CONSTRUCTOR,
     * MessageContact.CONSTRUCTOR,
     * MessageAnimatedEmoji.CONSTRUCTOR,
     * MessageDice.CONSTRUCTOR,
     * MessageGame.CONSTRUCTOR,
     * MessagePoll.CONSTRUCTOR,
     * MessageStory.CONSTRUCTOR,
     * MessageInvoice.CONSTRUCTOR,
     * MessageCall.CONSTRUCTOR,
     * MessageVideoChatScheduled.CONSTRUCTOR,
     * MessageVideoChatStarted.CONSTRUCTOR,
     * MessageVideoChatEnded.CONSTRUCTOR,
     * MessageInviteVideoChatParticipants.CONSTRUCTOR,
     * MessageBasicGroupChatCreate.CONSTRUCTOR,
     * MessageSupergroupChatCreate.CONSTRUCTOR,
     * MessageChatChangeTitle.CONSTRUCTOR,
     * MessageChatChangePhoto.CONSTRUCTOR,
     * MessageChatDeletePhoto.CONSTRUCTOR,
     * MessageChatAddMembers.CONSTRUCTOR,
     * MessageChatJoinByLink.CONSTRUCTOR,
     * MessageChatJoinByRequest.CONSTRUCTOR,
     * MessageChatDeleteMember.CONSTRUCTOR,
     * MessageChatUpgradeTo.CONSTRUCTOR,
     * MessageChatUpgradeFrom.CONSTRUCTOR,
     * MessagePinMessage.CONSTRUCTOR,
     * MessageScreenshotTaken.CONSTRUCTOR,
     * MessageChatSetBackground.CONSTRUCTOR,
     * MessageChatSetTheme.CONSTRUCTOR,
     * MessageChatSetMessageAutoDeleteTime.CONSTRUCTOR,
     * MessageChatBoost.CONSTRUCTOR,
     * MessageForumTopicCreated.CONSTRUCTOR,
     * MessageForumTopicEdited.CONSTRUCTOR,
     * MessageForumTopicIsClosedToggled.CONSTRUCTOR,
     * MessageForumTopicIsHiddenToggled.CONSTRUCTOR,
     * MessageSuggestProfilePhoto.CONSTRUCTOR,
     * MessageCustomServiceAction.CONSTRUCTOR,
     * MessageGameScore.CONSTRUCTOR,
     * MessagePaymentSuccessful.CONSTRUCTOR,
     * MessagePaymentSuccessfulBot.CONSTRUCTOR,
     * MessagePaymentRefunded.CONSTRUCTOR,
     * MessageGiftedPremium.CONSTRUCTOR,
     * MessagePremiumGiftCode.CONSTRUCTOR,
     * MessageGiveawayCreated.CONSTRUCTOR,
     * MessageGiveaway.CONSTRUCTOR,
     * MessageGiveawayCompleted.CONSTRUCTOR,
     * MessageGiveawayWinners.CONSTRUCTOR,
     * MessageGiftedStars.CONSTRUCTOR,
     * MessageGiveawayPrizeStars.CONSTRUCTOR,
     * MessageGift.CONSTRUCTOR,
     * MessageUpgradedGift.CONSTRUCTOR,
     * MessageRefundedUpgradedGift.CONSTRUCTOR,
     * MessageContactRegistered.CONSTRUCTOR,
     * MessageUsersShared.CONSTRUCTOR,
     * MessageChatShared.CONSTRUCTOR,
     * MessageBotWriteAccessAllowed.CONSTRUCTOR,
     * MessageWebAppDataSent.CONSTRUCTOR,
     * MessageWebAppDataReceived.CONSTRUCTOR,
     * MessagePassportDataSent.CONSTRUCTOR,
     * MessagePassportDataReceived.CONSTRUCTOR,
     * MessageProximityAlertTriggered.CONSTRUCTOR,
     * MessageUnsupported.CONSTRUCTOR
     */
    when (content) {
        is TdApi.MessageText -> {
            TgRichText(
                content.text.entities.toList(),
                content.text.text,
                fontSize = fontSize
            )
            content.linkPreview?.let {
                LinkPreviewCard(it, animatedContentScope, navController, senderColor = senderColor, messageId = messageId)
            }
        }

        is TdApi.MessagePhoto -> {
            val thumbnail = content.photo.minithumbnail?.data
            val file = content.photo.sizes.last().photo
            //TODO caption & secret photo
            val aspectRatio =
                content.photo.sizes.last().width.toFloat() / content.photo.sizes.last().height.toFloat()
            TgImage(
                animatedContentScope, file, thumbnail, modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio),
                navController = navController,
                id = messageId.toString()
            )
            if (content.caption.text.isNotEmpty()) {
                TgRichText(
                    content.caption.entities.toList(),
                    content.caption.text,
                    fontSize = fontSize,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }
        }

        is TdApi.MessageSticker -> {
            val aspectRatio = content.sticker.width.toFloat() / content.sticker.height.toFloat()
            TgSticker(
                animatedContentScope,
                sticker = content.sticker,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio),
                navController = navController,
                messageId.toString()
            )
        }

        is TdApi.MessageAnimation -> {
            val aspectRatio = content.animation.width.toFloat() / content.animation.height.toFloat()
            TgAnimation(
                animatedContentScope,
                content.animation, modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio),
                navController,
                messageId.toString()
            )
        }

        is TdApi.MessageAnimatedEmoji -> {
            val aspectRatio =
                content.animatedEmoji.stickerWidth.toFloat() / content.animatedEmoji.stickerHeight.toFloat()
            content.animatedEmoji.sticker?.let {
                TgSticker(
                    animatedContentScope,
                    it, modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(aspectRatio),
                    navController,
                    messageId.toString()
                )
            }
        }

        is TdApi.MessageVideo -> {
            val aspectRatio = content.video.width.toFloat() / content.video.height.toFloat()
            TgVideo(
                content.video.video, modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(aspectRatio)
            )
            if (content.caption.text.isNotEmpty()) {
                TgRichText(
                    content.caption.entities.toList(),
                    content.caption.text,
                    fontSize = fontSize,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                )
            }
        }

        is TdApi.MessageVoiceNote -> {
            TgVoiceNote(content.voiceNote.voice, modifier = Modifier)
            if (content.caption.text.isNotEmpty()) {
                TgRichText(
                    content.caption.entities.toList(),
                    content.caption.text,
                    fontSize = fontSize,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                )
            }
        }

        else -> {
            Text(
                "Unsupported message type ${content.javaClass.name} but here is an description: ${
                    content.textDescription(
                        users, 14.sp
                    )
                }", color = NeoBlue, modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}