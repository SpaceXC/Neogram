package cn.spacexc.neogram.ui.screen.messages.ui.bubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cn.spacexc.neogram.ui.theme.BubbleGray
import cn.spacexc.neogram.ui.theme.NeoMain

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    senderIsMe: Boolean,
    isPreviousOneContinuous: Boolean,
    isNextOneContinuous: Boolean,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.then(
            if (senderIsMe) {
                Modifier
                    .padding(start = 4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = if (isPreviousOneContinuous) 3.dp else 15.dp,
                            bottomStart = 15.dp,
                            bottomEnd = if (isNextOneContinuous) 3.dp else 15.dp
                        )
                    )
                    .background(NeoMain)
            } else {
                Modifier
                    .padding(end = 4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (isPreviousOneContinuous) 3.dp else 15.dp,
                            topEnd = 15.dp,
                            bottomStart = if (isNextOneContinuous) 3.dp else 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .background(BubbleGray)
            }
        )
    ) { content() }
}