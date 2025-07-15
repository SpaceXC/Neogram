package cn.spacexc.neogram.ui.component

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
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
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.pow

enum class DraggableBoxDirection {
    SwipeToRight, SwipeToLeft
}

@Composable
fun DraggableBox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    threshold: Float,
    onProgressChange: (Float) -> Unit,
    triggerThreshold: Float,
    direction: DraggableBoxDirection = DraggableBoxDirection.SwipeToRight,
    onTriggered: () -> Unit,
    content: @Composable () -> Unit
) {
    if (enabled) {
        val scope = rememberCoroutineScope()
        var currentOffset by remember { mutableFloatStateOf(0f) }
        val actualOffset by remember {
            derivedStateOf {
                when(direction) {
                    DraggableBoxDirection.SwipeToRight -> {
                        if (currentOffset > threshold) {
                            val delta = currentOffset - threshold
                            threshold + delta.pow(0.6f)
                        } else if (currentOffset < 0) {
                            val delta = currentOffset.absoluteValue
                            -delta.pow(0.6f)
                        } else currentOffset
                    }
                    DraggableBoxDirection.SwipeToLeft -> {
                        if (currentOffset < -threshold) {
                            val delta = -(currentOffset + threshold)
                            -threshold - delta.pow(0.6f)
                        } else if (currentOffset > 0) {
                            val delta = currentOffset.absoluteValue
                            delta.pow(0.6f)
                        } else currentOffset
                    }
                }
            }
        }
        LaunchedEffect(actualOffset) {
            when (direction) {
                DraggableBoxDirection.SwipeToRight -> onProgressChange(
                    (actualOffset / threshold).coerceIn(
                        0f..1f
                    )
                )

                DraggableBoxDirection.SwipeToLeft -> onProgressChange(
                    (-actualOffset / threshold).coerceIn(
                        0f..1f
                    )
                )
            }
        }
        Box(
            modifier = modifier
                .offset { IntOffset(x = actualOffset.toInt(), y = 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change, dragAmount ->
                            currentOffset += dragAmount
                        },
                        onDragEnd = {
                            // 监听手指抬起事件
                            when(direction) {
                                DraggableBoxDirection.SwipeToRight -> {
                                    if (actualOffset > triggerThreshold) {
                                        onTriggered()
                                    }
                                }
                                DraggableBoxDirection.SwipeToLeft -> {
                                    if (actualOffset < -triggerThreshold) {
                                        onTriggered()
                                    }
                                }
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