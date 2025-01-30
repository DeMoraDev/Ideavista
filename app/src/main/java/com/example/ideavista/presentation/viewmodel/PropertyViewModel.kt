package com.example.ideavista.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.presentation.utils.Property
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PropertyViewModel(private val propertyRepository: PropertyRepository) : ViewModel() {

    //Datos del cache

    private val _propertyDetails = MutableStateFlow<Property?>(null)
    val propertyDetails: StateFlow<Property?> = _propertyDetails


    fun fetchPropertyDetails(propertyId: String) {
        viewModelScope.launch {

            _isLoading.value = true  // 🔹 Activa shimmer antes de la consulta

            try {
                delay(1500)
                // Llamamos al repositorio para obtener los detalles de la propiedad
                val details = propertyRepository.getPropertyById(propertyId)
                _propertyDetails.value = details

            } catch (e: Exception) {
                Log.e("Firestore", "Error al obtener detalles: ${e.message}")
            } finally {
                _isLoading.value = false // 🔹 Desactiva shimmer cuando termina la llamada
            }

        }
    }

    //Efecto shimmer, estado de Loading

    private val _isLoading = MutableStateFlow(true) // 🔹
    val isLoading: StateFlow<Boolean> = _isLoading

    //Hipoteca
    private var _value: Float = 250000f
    val value: Float
        get() = _value  // Exponer el valor como un Float, sin MutableState

    // Función para actualizar el valor
    fun updateValue(newValue: Float) {
        _value = newValue
    }
}
