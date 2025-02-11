package com.example.ideavista.presentation.view.composable.maps

import android.graphics.Point
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import com.example.ideavista.presentation.state.DrawerMotionEvent
import com.example.ideavista.presentation.utils.MapPolygonState
import com.example.ideavista.presentation.utils.toPoint

@Composable
fun MapDrawer(
    state: MapPolygonState,
    polygonCompleted: Boolean,
    onUpdatePosition: (Offset, DrawerMotionEvent) -> Unit,
    onDrawingEnd: (List<Point>) -> Unit
) {
    val brush = remember { SolidColor(Color.Black) }
    val path = remember { Path() }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitEachGesture {
                    val screenPoints = mutableListOf<Point>()

                    awaitPointerEvent().changes
                        .first()
                        .also { changes ->
                            val position = changes.position
                            Log.d("MapDrawer", "First touch detected at: $position")
                            screenPoints.add(position.toPoint())
                            onUpdatePosition(position, DrawerMotionEvent.down)
                        }

                    var event: PointerEvent

                    do {
                        event = awaitPointerEvent()
                        event.changes.forEach { changes ->
                            val position = changes.position
                            Log.d("MapDrawer", "Pointer move at: $position")
                            screenPoints.add(position.toPoint())
                            onUpdatePosition(position, DrawerMotionEvent.move)
                        }
                    } while (event.changes.any { it.pressed })

                    event.changes
                        .first()
                        .also { change ->
                            Log.d("MapDrawer", "Pointer up at: ${change.position}")
                            onUpdatePosition(change.position, DrawerMotionEvent.up)
                            screenPoints.add(change.position.toPoint())
                        }

                    Log.d("MapDrawer", "Final path: $screenPoints")
                    onDrawingEnd.invoke(screenPoints)
                }
            },
        onDraw = {
            if (state.currentPosition != Offset.Unspecified) {
                when (state.event) {
                    DrawerMotionEvent.idle -> Unit
                    DrawerMotionEvent.up, DrawerMotionEvent.move -> {
                        Log.d("MapDrawer", "Drawing line to: ${state.currentPosition}")
                        path.lineTo(state.currentPosition.x, state.currentPosition.y)
                    }

                    DrawerMotionEvent.down -> {
                        Log.d("MapDrawer", "Starting new path at: ${state.currentPosition}")
                        path.moveTo(state.currentPosition.x, state.currentPosition.y)
                    }
                }
            }

            drawPath(
                path = path,
                brush = brush,
                style = Stroke(width = 10f)
            )
        }
    )
}
