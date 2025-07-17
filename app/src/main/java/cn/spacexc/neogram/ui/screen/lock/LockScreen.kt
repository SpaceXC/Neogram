package cn.spacexc.neogram.ui.screen.lock

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.spacexc.neogram.ui.screen.messages.within
import cn.spacexc.neogram.ui.theme.NeoMain
import cn.spacexc.neogram.utils.ifMinus1ThenNull
import kotlinx.serialization.Serializable

@Serializable
data object LockScreen

@Composable
fun LockScreen(navController: NavController) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val localDensity = LocalDensity.current
    var dragStated by remember { mutableStateOf(false) }
    val path = remember { mutableStateListOf<Int>() }
    var positions by remember { mutableStateOf(listOf<Offset>()) }
    val touchRange = remember { with(localDensity) { 8.dp.toPx().toInt() } }    //手指触碰到以点为圆心以此为半径的圆内即判定为触碰到这个点
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectTapGestures(onPress = { offset ->
                    println("$offset")
                    currentPosition = offset
                    offset.getCurrentPoint(positions, touchRange)
                        ?.let { positionIndex ->
                            /*if (!path.contains(positionIndex))*/ path.add(
                                positionIndex
                            )
                        }
                    currentPosition = positions[path.last()]
                })
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        println("START")
                    },
                    onDragEnd = {
                        println("END")
                        currentPosition = positions[path.last()]
                        path.clear()
                    },
                    onDragCancel = {
                        println("CANCEL")
                    },
                    onDrag = { change, dragAmount ->
                        println(change.position)
                        currentPosition = change.position
                        change.position.getCurrentPoint(positions, touchRange)
                            ?.let { positionIndex ->
                                /*if (!path.contains(positionIndex))*/ path.add(positionIndex)
                            }

                    })
            }
            .drawBehind {
                if (positions.isEmpty()) positions = getDotPositions()

                val dotSize = 4.dp.toPx()

                repeat(9) { index ->
                    val line = index / 3
                    val row = index - line * 3

                    val topLeftCorner = Offset(x = size.width / 3 * row, y = size.height / 3 * line)
                    val bottomRightCorner =
                        Offset(x = size.width / 3 * (row + 1), y = size.height / 3 * (line + 1))


                    val center = middlePoint(topLeftCorner, bottomRightCorner)

                    val color = if (path.contains(index)) NeoMain else Color(
                        217,
                        217,
                        217
                    ).copy(alpha = 0.2f)

                    drawCircle(
                        color = color,
                        radius = dotSize,
                        center = center
                    )
                }
            }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .graphicsLayer {
                compositingStrategy =
                    CompositingStrategy.Offscreen
            }
            .drawBehind {
                val dotSize = 4.dp.toPx()

                path.forEachIndexed { index, offsetIndex ->
                    if (index < path.size - 1) {
                        val nextOffsetIndex = path[index + 1]
                        drawLine(
                            NeoMain,
                            start = positions[offsetIndex],
                            end = positions[nextOffsetIndex],
                            cap = StrokeCap.Round,
                            strokeWidth = dotSize * 0.5f
                        )
                    }
                }

                if (path.isNotEmpty()) {
                    drawLine(
                        NeoMain,
                        start = positions[path.last()],
                        end = currentPosition,
                        cap = StrokeCap.Round,
                        strokeWidth = dotSize * 0.5f
                    )
                }

                drawRect(
                    brush = Brush.linearGradient(
                        0f to Color.Transparent,
                        1f to Color.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, 0f)
                    ),
                    blendMode = BlendMode.DstIn
                )
            })
}

fun middlePoint(offset1: Offset, offset2: Offset): Offset {
    return Offset(
        (offset1.x + offset2.x) / 2,
        (offset1.y + offset2.y) / 2
    )
}

fun DrawScope.getDotPositions(): List<Offset> {
    val positions = mutableListOf<Offset>()

    repeat(9) { index ->
        val line = index / 3
        val row = index - line * 3

        val topLeftCorner = Offset(x = size.width / 3 * row, y = size.height / 3 * line)
        val bottomRightCorner =
            Offset(x = size.width / 3 * (row + 1), y = size.height / 3 * (line + 1))

        val center = middlePoint(topLeftCorner, bottomRightCorner)

        positions.add(center)
    }

    return positions
}

fun Offset.getCurrentPoint(positions: List<Offset>, range: Int): Int? {
    return positions.indexOfFirst { center ->
        val start = Offset(center.x - range, center.y - range)
        val end = Offset(center.x + range, center.y + range)
        this within Pair(start, end)
    }.ifMinus1ThenNull()
}