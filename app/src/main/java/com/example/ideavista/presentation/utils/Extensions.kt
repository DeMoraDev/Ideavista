package com.example.ideavista.presentation.utils


import android.graphics.Point
import androidx.compose.ui.geometry.Offset
import com.google.android.gms.maps.Projection
import com.google.android.gms.maps.model.LatLng

fun Offset.toPoint(): Point = Point(x.toInt(), y.toInt())

// Función de extensión para convertir Point a LatLng
fun Point.toLatLng(): LatLng = LatLng(this.y.toDouble(), this.x.toDouble())

// Función de extensión para convertir Point a Offset
fun Point.toOffset(): Offset = Offset(x.toFloat(), y.toFloat())


// Función de extensión para convertir LatLng a Offset
fun LatLng.toOffset(projection: Projection): Offset {
    val point = projection.toScreenLocation(this)
    return Offset(point.x.toFloat(), point.y.toFloat())
}