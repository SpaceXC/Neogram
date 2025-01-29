package cn.spacexc.neogram.ui.component

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.theme.miSans
import cn.spacexc.neogram.utils.processTextEntities
import org.drinkless.tdlib.TdApi

@Composable
fun TgRichText(entities: List<TdApi.TextEntity>, text: String) {
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

                else -> {
                    append(node.text)
                }
            }
        }
    }
    Text(
        annotatedString,
        color = Color.White,
        fontFamily = miSans,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        inlineContent = inlineTextContent
    )
}