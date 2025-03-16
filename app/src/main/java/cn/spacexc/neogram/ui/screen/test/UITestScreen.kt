package cn.spacexc.neogram.ui.screen.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.component.DraggableBox
import cn.spacexc.neogram.ui.theme.miSans
import kotlinx.serialization.Serializable

@Serializable
object UITestScreen

@Composable
fun UITestScreen() {
    val scope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
    ) {
        DraggableBox(modifier = Modifier, 50f, {}, {}) {
            Text(
                "Swipe me!",
                fontFamily = miSans,
                fontSize = 15.sp,
                color = Color.White,
            )
        }
    }
}