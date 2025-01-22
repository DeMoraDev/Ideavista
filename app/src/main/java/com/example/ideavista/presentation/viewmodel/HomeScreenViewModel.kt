package com.example.ideavista.presentation.viewmodel

import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.presentation.state.BuyRentButtonState
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.HomeScreenState
import com.example.ideavista.presentation.state.TipoPropiedad
import com.example.ideavista.presentation.utils.Property
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeScreenViewModel : ViewModel() {

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


    fun fetchProperties() {
        val selectedOption = _buyRentState.value.selectedOption ?: return
        val tipoPropiedadString = when (selectedOption) {
            BuyRentShareButtonOptions.COMPRAR -> "comprar"
            BuyRentShareButtonOptions.ALQUILAR -> "alquilar"
            BuyRentShareButtonOptions.COMPARTIR -> "compartir"
        }

        val db = FirebaseFirestore.getInstance()
        db.collection("property")
            .whereEqualTo("tipo_propiedad", tipoPropiedadString)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d(
                        "FetchProperties",
                        "No se encontraron propiedades para el tipo $tipoPropiedadString"
                    )
                    _properties.value = emptyList()
                } else {
                    Log.d("FetchProperties", "Se obtuvieron ${documents.size()} documentos")
                    val result = documents.mapNotNull { document ->
                        try {
                            val property = document.toObject(Property::class.java)
                            Log.d("FetchProperties", "Documento mapeado correctamente: $property")
                            property
                        } catch (e: Exception) {
                            Log.e("FetchProperties", "Error mapeando documento: ${e.message}")
                            null
                        }
                    }
                    _properties.value = result
                    Log.d("HomeScreenViewModel", "Properties actualizados: $result")
                    Log.d(
                        "FetchProperties",
                        "Propiedades asignadas a _properties.value: ${_properties.value}"
                    )
                }
            }
            .addOnFailureListener { e ->
                Log.e("FetchProperties", "Fallo al obtener propiedades: ${e.message}")
                e.printStackTrace()
            }
    }
}

/* //Estado de tipo de propiedad
 private val _propertyType = MutableStateFlow("")
 val propertyType: StateFlow<String> get() = _propertyType
 //Función para cambiar el estado del tipo de propiedad
 fun onPropertyTypeSelected(type: String) {
     _propertyType.value = type
 }

 //Estado de la dirección de la propiedad
 private val _address = MutableStateFlow("")
 val address: StateFlow<String> get() = _address
 //Su función para cambiar dirección
 fun onAddressChanged(newAddress: String) {
     _address.value = newAddress
 } */
