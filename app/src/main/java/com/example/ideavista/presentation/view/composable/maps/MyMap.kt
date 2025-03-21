package com.example.ideavista.presentation.view.composable.maps

import android.graphics.Point
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MyMap(points: List<Point>,isDrawingMode: Boolean) {
    val valencia = LatLng(39.4699, -0.3763) // 📍 Ubicación  Valencia
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(valencia, 12f)
    }

    var geoPoints by remember { mutableStateOf(listOf<LatLng>()) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true, scrollGesturesEnabled = !isDrawingMode)
    ) {
        cameraPositionState.projection?.let { projection ->
            geoPoints = points.map { projection.fromScreenLocation(it) }
        }
        if (geoPoints.isNotEmpty()) {
            Polygon(
                points = geoPoints,
                fillColor = Color.Blue.copy(alpha = 0.4f)
            )
        }
    }
}
