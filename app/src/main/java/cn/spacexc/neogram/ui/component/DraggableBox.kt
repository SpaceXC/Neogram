package cn.spacexc.neogram.ui.component

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.pow

@Composable
fun DraggableBox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    threshold: Float,
    onProgressChange: (Float) -> Unit,
    triggerThreshold: Float,
    onTriggered: () -> Unit,
    content: @Composable () -> Unit
) {
    if (enabled) {
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
                        if (actualOffset > triggerThreshold) {
                            onTriggered()
                        }
                        scope.launch {
                            animate(
                                initialValue = currentOffset,
                                targetValue = 0f,
                                animationSpec = tween(250),
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
    } else {
        content()
    }
}