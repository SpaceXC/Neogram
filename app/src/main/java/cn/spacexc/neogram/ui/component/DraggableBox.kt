package cn.spacexc.neogram.ui.component

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import cn.spacexc.neogram.ui.theme.miSans
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.pow

@Composable
fun DraggableBox(
    modifier: Modifier = Modifier,
    threshold: Float,
    onProgressChange: (Float) -> Unit,
    onTriggered: () -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    var currentOffset by remember { mutableFloatStateOf(0f) }
    val actualOffset by remember {
        derivedStateOf {
            if (currentOffset > threshold) {
                val delta = currentOffset - threshold
                delta.pow(-1)
                threshold + delta.pow(0.6f)
            } else if (currentOffset < 0) {
                val delta = currentOffset.absoluteValue
                delta.pow(-1)
                -delta.pow(0.6f)
            } else currentOffset
        }
    }
    LaunchedEffect(actualOffset) {
        onProgressChange((actualOffset / threshold).coerceIn(0f..1f))
    }
    Box(modifier = modifier
        .offset { IntOffset(x = actualOffset.toInt(), y = 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onHorizontalDrag = { change, dragAmount ->
                    currentOffset += dragAmount
                },
                onDragEnd = {
                    // 监听手指抬起事件
                    if (actualOffset > threshold) {
                        onTriggered()
                    }
                    scope.launch {
                        animate(
                            initialValue = currentOffset,
                            targetValue = 0f,
                            animationSpec = tween(durationMillis = 200),
                        ) { value, _ ->
                            currentOffset = value
                        }
                    }
                }
            )
        }
    ) {
        content()
    }
}