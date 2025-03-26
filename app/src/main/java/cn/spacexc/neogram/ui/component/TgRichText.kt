package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.theme.NeoBlue
import cn.spacexc.neogram.ui.theme.jetbrainsMono
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.processTextEntities
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.TextEntityTypeBold
import org.drinkless.tdlib.TdApi.TextEntityTypeItalic
import org.drinkless.tdlib.TdApi.TextEntityTypeUnderline

@Composable
fun TgRichText(entities: List<TdApi.TextEntity>, text: String, modifier: Modifier = Modifier, textStyle: TextStyle = TextStyle()) {
    val inlineTextContent = mutableMapOf<String, InlineTextContent>()
    val annotatedString = buildAnnotatedString {
        /**
         * TextEntityTypeMention.CONSTRUCTOR,
         * TextEntityTypeHashtag.CONSTRUCTOR,
         * TextEntityTypeCashtag.CONSTRUCTOR,
         * TextEntityTypeBotCommand.CONSTRUCTOR,
         * TextEntityTypeUrl.CONSTRUCTOR,
         * TextEntityTypeEmailAddress.CONSTRUCTOR,
         * TextEntityTypePhoneNumber.CONSTRUCTOR,
         * TextEntityTypeBankCardNumber.CONSTRUCTOR,
         * TextEntityTypeBold.CONSTRUCTOR,
         * TextEntityTypeItalic.CONSTRUCTOR,
         * TextEntityTypeUnderline.CONSTRUCTOR,
         * TextEntityTypeStrikethrough.CONSTRUCTOR,
         * TextEntityTypeSpoiler.CONSTRUCTOR,
         * TextEntityTypeCode.CONSTRUCTOR,
         * TextEntityTypePre.CONSTRUCTOR,
         * TextEntityTypePreCode.CONSTRUCTOR,
         * TextEntityTypeBlockQuote.CONSTRUCTOR,
         * TextEntityTypeExpandableBlockQuote.CONSTRUCTOR,
         * TextEntityTypeTextUrl.CONSTRUCTOR,
         * TextEntityTypeMentionName.CONSTRUCTOR,
         * TextEntityTypeCustomEmoji.CONSTRUCTOR,
         * TextEntityTypeMediaTimestamp.CONSTRUCTOR
         */
        val textNodes = processTextEntities(
            entities = entities.toList(),
            text = text
        )
        textNodes.forEach { node ->
            when (node.type) {
                null -> {
                    append(node.text)
                }
                is TextEntityTypeBold -> {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(node.text)
                    }
                }
                is TextEntityTypeItalic -> {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(node.text)
                    }
                }
                is TextEntityTypeUnderline -> {
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append(node.text)
                    }
                }
                is TdApi.TextEntityTypeStrikethrough -> {
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append(node.text)
                    }
                }
                is TdApi.TextEntityTypeSpoiler -> {
                    withStyle(SpanStyle(color = Color.White.copy(alpha = 0.5f))) {
                        append(node.text)
                    }
                }
                is TdApi.TextEntityTypeCode -> {
                    withStyle(SpanStyle(fontFamily = jetbrainsMono)) {
                        append(node.text)
                    }
                }
                else -> {
                    withStyle(SpanStyle(fontWeight = FontWeight.Medium, color = NeoBlue)) {
                        append(node.text)
                    }
                }
            }
        }
    }
    Text(
        annotatedString,
        color = Color.White,
        fontFamily = miSans,
        style = textStyle,
        //fontWeight = FontWeight(450),
        inlineContent = inlineTextContent,
        modifier = modifier
    )
}

@Composable
fun TgRichText(formattedText: TdApi.FormattedText, modifier: Modifier = Modifier, textStyle: TextStyle = TextStyle()) {
    TgRichText(formattedText.entities.toList(), formattedText.text, modifier, textStyle)
}