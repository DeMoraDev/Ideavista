package com.example.ideavista.presentation.utils

import androidx.compose.ui.geometry.Offset
import com.example.ideavista.presentation.state.DrawerMotionEvent

data class MapPolygonState(
    val currentPosition: Offset = Offset.Zero,
    val event: DrawerMotionEvent = DrawerMotionEvent.idle
)
