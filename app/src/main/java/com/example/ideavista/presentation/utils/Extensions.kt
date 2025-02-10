package com.example.ideavista.presentation.utils


import android.graphics.Point
import androidx.compose.ui.geometry.Offset

fun Offset.toPoint(): Point = Point(x.toInt(), y.toInt())
