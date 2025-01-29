package cn.spacexc.neogram.utils

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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
                append("照片")
            }
            if (thumbnail != null) {
                inlineTextContent["photoThumbnail"] = InlineTextContent(placeholder) {
                    val thumbnailBitmap =
                        BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.size)
                            .asImageBitmap()
                    Image(
                        thumbnailBitmap,
                        null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
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
                        this@textDescription.memberUserIds.map { "${users[it]?.username}" }
                            .joinToString { ", " }
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

        else -> buildAnnotatedString {
            withStyle(spanStyle) {
                append(this@textDescription?.toString())
            }
        }
    }
    return Pair(inlineTextContent, annotatedString)
}

fun processTextEntities(entities: List<TdApi.TextEntity>, text: String): List<TextNode> {
    // 1. 排序区间
    val maxValue = text.length - 1
    val sortedEntities = entities.sortedBy { it.offset }

    // 2. 初始化结果列表和前一个结束位置
    val result = mutableListOf<TextNode>()
    var previousEnd = -1

    // 3. 遍历每个区间，填充缺失部分
    for (entity in sortedEntities) {
        if (entity.offset > previousEnd + 1) {
            // 如果当前区间起点大于上一个区间的终点 + 1，说明有缺失
            val indexRange = (previousEnd + 1) until entity.offset
            result.add(TextNode(null, text.substring(indexRange)))
        }
        // 添加当前区间
        val textNode = TextNode(
            entity.type,
            text.substring(entity.offset..entity.offset + entity.length - 1)
        )
        result.add(textNode)
        previousEnd = entity.offset + entity.length - 1
    }

    // 4. 检查末尾是否还有缺失部分
    if (previousEnd < maxValue) {
        val indexRange = (previousEnd + 1)..maxValue
        result.add(TextNode(null, text.substring(indexRange)))
    }

    return result
}

data class TextNode(
    val type: TdApi.TextEntityType?,
    val text: String
)