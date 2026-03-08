package cn.spacexc.neogram.ui.screen.messages.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.theme.BubbleGray
import cn.spacexc.neogram.ui.theme.miSans

@Composable
fun MessageNotification(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(modifier = Modifier.background(BubbleGray.copy(alpha = 0.5f), RoundedCornerShape(6.dp)).align(Alignment.Center)) {
            Text(
                text,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
                fontFamily = miSans,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun MessageNotificationPrev() {
    MessageNotification(text = "XC通过链接加入了群聊")
}