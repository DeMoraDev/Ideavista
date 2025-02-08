package com.example.ideavista.presentation.view.composable.maps

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
@Composable
fun GoogleMapScreen(
    context: Context,
    isSatelliteView: Boolean,
    moveToUserLocation: Boolean
) {
    val mapType = if (isSatelliteView) MapType.SATELLITE else MapType.NORMAL
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(moveToUserLocation) {
        if (moveToUserLocation) {
            val locationProvider = LocationServices.getFusedLocationProviderClient(context)
            try {
                val location = locationProvider.lastLocation.await()
                location?.let {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(
                        LatLng(it.latitude, it.longitude), 15f
                    )
                }
            } catch (e: SecurityException) {
                Log.e("GoogleMapScreen", "Error de permisos de ubicación", e)
            }
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = mapType)
    )
}
