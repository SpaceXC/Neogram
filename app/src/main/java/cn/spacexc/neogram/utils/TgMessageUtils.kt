package cn.spacexc.neogram.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import cn.spacexc.neogram.ui.theme.NeoBlue
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.MessageAnimatedEmoji
import org.drinkless.tdlib.TdApi.MessageAnimation
import org.drinkless.tdlib.TdApi.MessageAudio
import org.drinkless.tdlib.TdApi.MessageChatAddMembers
import org.drinkless.tdlib.TdApi.MessageChatJoinByLink
import org.drinkless.tdlib.TdApi.MessageChatJoinByRequest
import org.drinkless.tdlib.TdApi.MessageContactRegistered
import org.drinkless.tdlib.TdApi.MessageDocument
import org.drinkless.tdlib.TdApi.MessagePhoto
import org.drinkless.tdlib.TdApi.MessageSticker
import org.drinkless.tdlib.TdApi.MessageSupergroupChatCreate
import org.drinkless.tdlib.TdApi.MessageText
import org.drinkless.tdlib.TdApi.MessageVideo

private val spanStyle = SpanStyle(color = NeoBlue)

val TdApi.MessageContent?.textDescription: AnnotatedString
    get() {
        return when (this) {
            is MessageText -> buildAnnotatedString {
                append(text.text)
            }

            is MessageAnimation -> buildAnnotatedString {
                append("动画 ${caption.text}")
            }

            is MessageAudio -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("音频 ${audio.duration}\" ${caption.text}")
                }
            }

            is MessageDocument -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("文档 ${caption.text}")
                }
            }

            is MessagePhoto -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("照片")
                }
            }

            is MessageSticker -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("贴纸 ${sticker.emoji}")
                }
            }

            is MessageVideo -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("视频 ${video.duration}\" ${caption.text}")
                }
            }

            is MessageChatAddMembers -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("${this@textDescription.memberUserIds.joinToString(", ")}加入了群聊")
                }
            }

            is MessageAnimatedEmoji -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append(this@textDescription.emoji)
                }
            }

            is MessageChatJoinByLink -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("有人通过邀请链接加入了群聊")
                }
            }

            is MessageChatJoinByRequest -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("有人通过通过申请加入了群聊")
                }
            }

            is MessageSupergroupChatCreate -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("${this@textDescription.title}创建了")
                }
            }

            is MessageContactRegistered -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append("加入了Telegram")
                }
            }

            else -> buildAnnotatedString {
                withStyle(spanStyle) {
                    append(this@textDescription?.toString())
                }
            }
        }
    }