package com.example.ideavista.presentation.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.example.ideavista.presentation.state.DrawerMotionEvent

data class MapPolygonState(
    val currentPosition: Offset = Offset.Unspecified,
    val event: DrawerMotionEvent = DrawerMotionEvent.idle,
    val path: Path = Path()
)
