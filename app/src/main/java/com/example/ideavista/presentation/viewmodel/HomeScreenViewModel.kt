package com.example.ideavista.presentation.viewmodel

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.usecase.properties.FetchPropertiesPreviewUseCase
import com.example.ideavista.domain.usecase.properties.FetchPropertiesUseCase
import com.example.ideavista.presentation.state.BuyRentButtonState
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.HomeScreenState
import com.example.ideavista.presentation.state.TipoPropiedad
import com.example.ideavista.presentation.utils.Property
import com.example.ideavista.presentation.utils.PropertyPreview
import com.google.firebase.firestore.FirebaseFirestore
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

    //Filtros de búsqueda

    //Instancia Firestore

    //Estado del botón de función de propiedad
    private val _buyRentState =
        MutableStateFlow(BuyRentButtonState(selectedOption = BuyRentShareButtonOptions.COMPRAR))
    val buyRentState: StateFlow<BuyRentButtonState> get() = _buyRentState

    //Función para cambiar estado del boton
    fun onBuyRentButtonClicked(option: BuyRentShareButtonOptions) {
        _buyRentState.value = _buyRentState.value.copy(selectedOption = option)
    }

    //Lista de propiedades que coinciden
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties


    fun fetchProperties(tipoPropiedad: String) {
        viewModelScope.launch {
            _properties.value = fetchPropertiesUseCase(tipoPropiedad)
        }
    }


    //Lista de propiedades Preview
    private val _properties_Preview = MutableStateFlow<List<PropertyPreview>>(emptyList())
    val properties_Preview: StateFlow<List<PropertyPreview>> = _properties_Preview

    fun fetchPropertiesPreview(tipoPropiedad: String) {
        viewModelScope.launch {
            _properties_Preview.value = fetchPropertiesPreviewUseCase(tipoPropiedad)
        }
    }
}

