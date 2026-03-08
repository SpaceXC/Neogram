package cn.spacexc.neogram.ui.screen.test

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import cn.spacexc.neogram.ui.component.modifier.lazyRotateInput
import kotlinx.serialization.Serializable

@Serializable
object UITestScreen

@Composable
fun UITestScreen() {
    val scope = rememberCoroutineScope()
    var rotaryValue by remember { mutableFloatStateOf(0f) }
    /*val focusRequester = remember { FocusRequester() }
    val actualRotatoryValue by animateFloatAsState(rotaryValue)
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
            .onRotaryScrollEvent {
                rotaryValue += it.verticalScrollPixels
                true
            }
    ) {
        DraggableBox(modifier = Modifier, 50f, {}, 50f, {}) {
            Text(
                "Swipe me!",
                fontFamily = miSans,
                fontSize = 15.sp,
                color = White,
            )
        }
        Text(
            "$actualRotatoryValue",
            fontFamily = miSans,
            fontSize = 15.sp,
            color = White,
            modifier = Modifier.focusRequester(focusRequester).focusable()
        )
    }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }*/

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .lazyRotateInput(focusRequester, scrollState)
        /*.onRotaryScrollEvent {
            coroutineScope.launch { scrollState.scrollTo((scrollState.value + it.verticalScrollPixels).roundToInt()) }
            true
        }
        .focusRequester(focusRequester)
        .focusable()*/,
        state = scrollState
    ) {
        repeat(100) {
            item {
                Text(
                    text = "item $it",
                    color = White,
                )
            }
        }
    }
    LaunchedEffect(Unit) { focusRequester.requestFocus() }
}