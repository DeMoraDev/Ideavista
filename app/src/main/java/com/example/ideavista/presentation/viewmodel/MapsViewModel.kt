package com.example.ideavista.presentation.viewmodel

import android.graphics.Point
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.data.local.SearchPreferences
import com.example.ideavista.domain.usecase.properties.FetchPropertiesPreviewUseCase
import com.example.ideavista.presentation.state.DrawerMotionEvent
import com.example.ideavista.presentation.utils.MapPolygonState
import com.google.android.gms.maps.Projection
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class MapsViewModel(
    private val fetchPropertiesPreviewUseCase: FetchPropertiesPreviewUseCase
) : ViewModel() {

    var state by mutableStateOf(MapPolygonState())
        private set

    var firstPoint by mutableStateOf<Offset?>(null)
        private set

    var polygonCompleted by mutableStateOf(false)
        private set

    fun updatePosition(position: Offset, event: DrawerMotionEvent) {
        state = state.copy(currentPosition = position, event = event)

        if (event == DrawerMotionEvent.down && firstPoint == null) {
            firstPoint = position
        }

        if (event == DrawerMotionEvent.up) {
            polygonCompleted = true // Aquí podrías validar si el polígono se cerró bien
        }
    }

    fun getFirstPointLatLng(projection: Projection?): LatLng? {
        return firstPoint?.let { offset ->
            projection?.fromScreenLocation(Point(offset.x.toInt(), offset.y.toInt()))
        }
    }

    fun resetPolygon() {
        _points.value = emptyList()
        _polygonComplete.value = false
        _isDrawingMode.value = false
        firstPoint = null
        polygonCompleted = false
        state = MapPolygonState()
    }




    private val _propertyCount = MutableStateFlow(0)
    val propertyCount: StateFlow<Int> = _propertyCount.asStateFlow()

    private val _points = MutableStateFlow<List<Point>>(emptyList())
    val points: StateFlow<List<Point>> = _points.asStateFlow()

    private val _isDrawingMode = MutableStateFlow(false)
    val isDrawingMode: StateFlow<Boolean> = _isDrawingMode.asStateFlow()

    private val _permissionGranted = MutableStateFlow(false)
    val permissionGranted: StateFlow<Boolean> = _permissionGranted.asStateFlow()

    private val _polygonComplete = MutableStateFlow(false)
    val polygonComplete: StateFlow<Boolean> = _polygonComplete.asStateFlow()

    fun setDrawingMode(isDrawing: Boolean) {
        _isDrawingMode.value = isDrawing
    }

    fun setPermissionGranted(granted: Boolean) {
        _permissionGranted.value = granted
    }

    fun setPoints(newPoints: List<Point>) {
        _points.value = newPoints
    }

    fun setPolygonComplete(complete: Boolean) {
        _polygonComplete.value = complete
    }


    fun fetchFilteredPropertyCount() {
        viewModelScope.launch {
            val count = fetchPropertiesPreviewUseCase(
                SearchPreferences.getModoPropiedad(),
                SearchPreferences.getDropdownDbValue(),
                SearchPreferences.getGarajeChecked()
            ).size
            _propertyCount.value = count
        }
    }
}