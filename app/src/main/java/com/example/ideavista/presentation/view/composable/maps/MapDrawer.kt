package com.example.ideavista.presentation.view.composable.maps

import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import com.example.ideavista.presentation.state.DrawerMotionEvent
import com.example.ideavista.presentation.utils.MapPolygonState
import com.example.ideavista.presentation.utils.toPoint
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun MapDrawer(onDrawingEnd: (List<Point>) -> Unit) {
    var state by remember { mutableStateOf(MapPolygonState()) }
    val brush = remember { SolidColor(Violeta) }
    val path = remember { Path() }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitEachGesture {
                    val screenPoints = mutableListOf<Point>()

                    awaitPointerEvent().changes.first().also { changes ->
                        val position = changes.position
                        screenPoints.add(position.toPoint())
                        state = state.copy(
                            currentPosition = position,
                            event = DrawerMotionEvent.down
                        )
                    }

                    var event: PointerEvent

                    do {
                        event = awaitPointerEvent()
                        event.changes.forEach { changes ->
                            val position = changes.position
                            screenPoints.add(position.toPoint())
                            state = state.copy(
                                currentPosition = position,
                                event = DrawerMotionEvent.move
                            )
                        }
                    } while (event.changes.any { it.pressed })

                    event.changes.first().also { change ->
                        state = state.copy(
                            currentPosition = change.position,
                            event = DrawerMotionEvent.up
                        )
                        screenPoints.add(change.position.toPoint())
                    }

                    onDrawingEnd.invoke(screenPoints)
                }
            },
        onDraw = {
            when (state.event) {
                DrawerMotionEvent.idle -> Unit
                DrawerMotionEvent.up, DrawerMotionEvent.move -> path.lineTo(
                    state.currentPosition.x,
                    state.currentPosition.y
                )
                DrawerMotionEvent.down -> path.moveTo(
                    state.currentPosition.x,
                    state.currentPosition.y
                )
            }
            drawPath(
                path = path,
                brush = brush,
                style = Stroke(width = 10f)
            )
        }
    )
}
