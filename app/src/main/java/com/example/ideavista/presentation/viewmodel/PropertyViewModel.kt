package com.example.ideavista.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.presentation.utils.Property
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PropertyViewModel(private val propertyRepository: PropertyRepository) : ViewModel() {


    private val _propertyDetails = MutableStateFlow<Property?>(null)
    val propertyDetails: StateFlow<Property?> = _propertyDetails


    fun fetchPropertyDetails(propertyId: String) {
        viewModelScope.launch {
            // Llamamos al repositorio para obtener los detalles de la propiedad
            val details = propertyRepository.getPropertyById(propertyId)
            _propertyDetails.value = details
        }
    }
}