package cn.spacexc.neogram.utils

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
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
import org.drinkless.tdlib.TdApi.MessageLocation
import org.drinkless.tdlib.TdApi.MessagePhoto
import org.drinkless.tdlib.TdApi.MessageSticker
import org.drinkless.tdlib.TdApi.MessageSupergroupChatCreate
import org.drinkless.tdlib.TdApi.MessageText
import org.drinkless.tdlib.TdApi.MessageVideo
import org.drinkless.tdlib.TdApi.User

private val spanStyle = SpanStyle(color = NeoBlue)

fun TdApi.MessageContent?.textDescription(
    users: Map<Long, User>,
    textSize: TextUnit
): Pair<Map<String, InlineTextContent>, AnnotatedString> {
    val inlineTextContent = mutableMapOf<String, InlineTextContent>()
    val placeholder = Placeholder(textSize * 1.1f, textSize * 1.1f, PlaceholderVerticalAlign.Center)
    val annotatedString = when (this) {
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
            val thumbnail = photo.minithumbnail?.data
            withStyle(spanStyle) {
                if (thumbnail != null) {
                    //appendInlineContent("photoThumbnail")
                    //这个有点太糊了吧
                }
                append("照片 ")
            }
            append(caption.text)
            if (thumbnail != null) {
                inlineTextContent["photoThumbnail"] = InlineTextContent(placeholder) {
                    /*val thumbnailBitmap =
                        BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.size)
                            .asImageBitmap()
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(thumbnail)
                            .size(Size.ORIGINAL)
                            .crossfade(true)
                            .build(), contentDescription = null, contentScale = ContentScale.Crop
                    )*/
                    /*Image(
                        thumbnailBitmap,
                        null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop

                    )*/
                }
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
                append(
                    "${
                        this@textDescription.memberUserIds.joinToString(",") { "${users[it]?.firstName}" }
                    }加入了群聊"
                )
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
                append("通过通过申请加入了群聊")
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

        is MessageLocation -> buildAnnotatedString {
            withStyle(spanStyle) {
                append("位置")
            }
        }

        is TdApi.MessageVoiceNote -> buildAnnotatedString {
            withStyle(spanStyle) {
                append("语音 ${this@textDescription.voiceNote.duration}\"")
            }
        }

        is TdApi.MessageChatChangePhoto -> buildAnnotatedString {
            withStyle(spanStyle) {
                append("头像更改")
            }
        }

        is TdApi.MessageChatChangeTitle -> buildAnnotatedString {
            withStyle(spanStyle) {
                append("名称更改")
            }
        }

        else -> buildAnnotatedString {
            withStyle(spanStyle) {
                append(this@textDescription?.toString())
            }
        }
    }
    return Pair(inlineTextContent, annotatedString)
}

/**
 * https://chatgpt.com/share/68752886-3298-800d-ab3a-0234b8f55491
 */
fun processTextEntities(entities: List<TdApi.TextEntity>, text: String): List<TextNode> {
    val result = mutableListOf<TextNode>()
    val length = text.length

    // 每个位置都保存它包含的type列表
    val typeMap = Array(length) { mutableListOf<TdApi.TextEntityType>() }

    for (entity in entities) {
        val start = entity.offset
        val end = (entity.offset + entity.length).coerceAtMost(length)
        for (i in start until end) {
            typeMap[i].add(entity.type)
        }
    }

    // 按 typeList 分段切割字符串
    var currentTypes: List<TdApi.TextEntityType>? = null
    var segmentStart = 0

    for (i in 0..length) {
        val types = if (i < length) typeMap[i] else null
        if (types != currentTypes) {
            if (i > segmentStart) {
                val segmentText = text.substring(segmentStart, i)
                result.add(TextNode(currentTypes ?: emptyList(), segmentText))
            }
            segmentStart = i
            currentTypes = types
        }
    }

    return result
}

data class TextNode(
    val type: List<TdApi.TextEntityType>,
    val text: String
)