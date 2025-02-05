package com.example.ideavista.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.usecase.properties.FetchPropertiesPreviewUseCase
import com.example.ideavista.domain.usecase.properties.FetchPropertiesUseCase
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.HomeScreenState
import com.example.ideavista.presentation.utils.Property
import com.example.ideavista.presentation.utils.PropertyPreview
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeScreenViewModel(
    private val fetchPropertiesUseCase: FetchPropertiesUseCase,
    private val fetchPropertiesPreviewUseCase: FetchPropertiesPreviewUseCase

) : ViewModel() {

    private val _uiState = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    fun updateStep(newStep: HomeContentStep) {
        _uiState.value = _uiState.value.copy(step = newStep)
    }


    //Lista de propiedades Preview
    private val _properties_Preview = MutableStateFlow<List<PropertyPreview>>(emptyList())
    val properties_Preview: StateFlow<List<PropertyPreview>> = _properties_Preview

    fun fetchPropertiesPreview(tipoPropiedad: String, dropdownDbValue: String) {
        Log.d("Firestore", "Buscando propiedades de tipo: $tipoPropiedad")
        viewModelScope.launch {
            // Se marca como cargando
            _isLoading.value = true

            // Llamamos al caso de uso y obtenemos los resultados
            val result = fetchPropertiesPreviewUseCase(tipoPropiedad, dropdownDbValue)

            // Asignamos los resultados a _properties_Preview
            _properties_Preview.value = result

            // Actualizamos el estado de _isLoading a false una vez terminada la carga
            _isLoading.value = false

            // Verificamos si el resultado está vacío y actualizamos el estado de _isEmpty
            _isEmpty.value = result.isEmpty()
        }
    }

    //Estados para PropertyListScreen, isLoading, data y isEmpty

    //Loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    //Empty call
    private val _isEmpty = MutableStateFlow(false)
    val isEmpty: StateFlow<Boolean> = _isEmpty


}