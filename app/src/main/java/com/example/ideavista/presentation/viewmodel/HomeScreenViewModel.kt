package com.example.ideavista.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.data.local.SearchPreferences
import com.example.ideavista.domain.repository.PropertyPreviewRepository
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
    private val fetchPropertiesPreviewUseCase: FetchPropertiesPreviewUseCase,
    private val repository: PropertyPreviewRepository

) : ViewModel() {

    private val _uiState = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    fun updateStep(newStep: HomeContentStep) {
        _uiState.value = _uiState.value.copy(step = newStep)
    }


    //Lista de propiedades Preview
    private val _properties_Preview = MutableStateFlow<List<PropertyPreview>>(emptyList())
    val properties_Preview: StateFlow<List<PropertyPreview>> = _properties_Preview

    fun fetchPropertiesWithFilters() {
        viewModelScope.launch {
            _isLoading.value = true

            val properties = repository.fetchPropertiesPreview(
                modoPropiedad = SearchPreferences.getModoPropiedad(),
                dropdownDbValue = SearchPreferences.getDropdownDbValue(),
                filters = SearchPreferences.getFilters()
            )
            _properties_Preview.value = properties

            _isLoading.value = false
            _isEmpty.value = properties.isEmpty()
        }
    }

  /*  fun fetchPropertiesPreview(
        tipoPropiedad: String,
        dropdownDbValue: String,
        garaje: Boolean? = null
    ) {
        Log.d(
            "Firestore",
            "Buscando propiedades con filtros: tipoPropiedad=$tipoPropiedad, garaje=$garaje"
        )

        viewModelScope.launch {
            _isLoading.value = true

            val result = fetchPropertiesPreviewUseCase(
                tipoPropiedad, dropdownDbValue, garaje
            )

            _properties_Preview.value = result
            _isLoading.value = false
            _isEmpty.value = result.isEmpty()
        }
    } */

    //Estados para PropertyListScreen, isLoading, data y isEmpty

    //Loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    //Empty call
    private val _isEmpty = MutableStateFlow(false)
    val isEmpty: StateFlow<Boolean> = _isEmpty


}