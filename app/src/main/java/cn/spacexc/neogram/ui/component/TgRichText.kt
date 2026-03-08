package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.screen.link.LinkProcessScreen
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.ui.theme.jetbrainsMono
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.ui.theme.parseColor
import cn.spacexc.neogram.utils.LogUtils
import cn.spacexc.neogram.utils.processTextEntities
import org.drinkless.tdlib.TdApi
import org.drinkless.tdlib.TdApi.TextEntityTypeBold
import org.drinkless.tdlib.TdApi.TextEntityTypeItalic
import org.drinkless.tdlib.TdApi.TextEntityTypeUnderline

@Composable
fun TgRichText(
    entities: List<TdApi.TextEntity>,
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    navController: NavController?
) {
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
            val spanStyle = node.type.map { type ->
                when (type) {
                    is TextEntityTypeBold -> SpanStyle(fontWeight = FontWeight.Bold)

                    is TextEntityTypeItalic -> SpanStyle(fontStyle = FontStyle.Italic)

                    is TextEntityTypeUnderline -> SpanStyle(textDecoration = TextDecoration.Underline)

                    is TdApi.TextEntityTypeStrikethrough -> SpanStyle(textDecoration = TextDecoration.LineThrough)

                    is TdApi.TextEntityTypeSpoiler -> SpanStyle(color = Color.White.copy(alpha = 0.5f))

                    is TdApi.TextEntityTypeCode -> SpanStyle(fontFamily = jetbrainsMono)

                    is TdApi.TextEntityTypeHashtag -> SpanStyle()

                    else -> SpanStyle(fontWeight = FontWeight.Medium, color = NeoMain)
                }
            }

            LogUtils.info("TextNode", "$spanStyle ${spanStyle.sum()}")
            withStyle(spanStyle.sum()) {
                if (node.type.find { it is TdApi.TextEntityTypeUrl } != null) {
                    val link = LinkAnnotation.Clickable(node.text, linkInteractionListener = {
                        navController?.navigate(LinkProcessScreen(node.text))
                        LogUtils.info("URL", node.text)
                    })
                    withLink(link) {
                        append(node.text)
                    }
                }
                else {
                    append(node.text)
                }

                // 如果是#开头的就试试匹配颜色吧
                if (node.type.find { it is TdApi.TextEntityTypeHashtag } != null) {
                    try {
                        val color = parseColor(node.text)
                        inlineTextContent[node.text] = InlineTextContent(
                            Placeholder(
                                textStyle.fontSize * 1.5f,
                                textStyle.fontSize * 1.5f,
                                PlaceholderVerticalAlign.Center
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color,
                                            RoundedCornerShape(40)
                                        )
                                        .fillMaxSize(0.7f)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                        appendInlineContent(node.text)
                    } catch (_: Exception) {
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
fun TgRichText(
    formattedText: TdApi.FormattedText,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    navController: NavController?
) {
    TgRichText(formattedText.entities.toList(), formattedText.text, modifier, textStyle, navController)
}

fun List<SpanStyle>.sum(): SpanStyle {
    if (isEmpty()) return SpanStyle()
    var result = first()
    forEach { style ->
        result += style
        LogUtils.info("spanSum", "$style")
    }
    return result
}