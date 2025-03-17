package com.example.ideavista.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.data.local.SearchPreferences
import com.example.ideavista.domain.repository.PropertyPreviewRepository
import com.example.ideavista.domain.usecase.properties.FetchPropertiesPreviewUseCase
import com.example.ideavista.presentation.utils.PropertyPreview
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FilterViewModel(private val fetchPropertiesPreviewUseCase: FetchPropertiesPreviewUseCase) :
    ViewModel() {

    //Llamada filterScreen

    private val db = Firebase.firestore

    private val _propertyCount = MutableStateFlow(0)
    val propertyCount: StateFlow<Int> = _propertyCount

    //Estado de loading
    private val _isLoading = MutableStateFlow(false)
    val isCountLoading: StateFlow<Boolean> = _isLoading

    fun fetchPropertyCount(modoPropiedad: String, dropdownDbValue: String) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)
            try {
                val snapshot = db.collection("property")
                    .whereEqualTo("modo_propiedad", modoPropiedad)
                    .whereEqualTo("tipo_propiedad", dropdownDbValue)
                    .get()
                    .await()

                _propertyCount.value = snapshot.size()
            } catch (e: Exception) {
                Log.e("Firestore", "Error obteniendo el conteo de propiedades: ${e.message}")
                _propertyCount.value = 0
            } finally {
                _isLoading.value = false
            }
        }
    }

    // TODO GARAJE

    private val _propertiesPreview = MutableStateFlow<List<PropertyPreview>>(emptyList())
    val propertiesPreview: StateFlow<List<PropertyPreview>> = _propertiesPreview

    val garajeChecked: StateFlow<Boolean> = SearchPreferences.garajeChecked
        .map { it ?: false } // Si es null, se establece como false
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun updateGarajeFilter(isChecked: Boolean) {
        SearchPreferences.setGarajeChecked(isChecked)
        fetchFilteredPropertyCount()
    }

    //TODO JARDIN

    val jardinChecked: StateFlow<Boolean> = SearchPreferences.jardinChecked
        .map { it ?: false } // Si es null, se establece como false
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun updateJardinFilter(isChecked: Boolean) {
        SearchPreferences.setJardinChecked(isChecked)
        fetchFilteredPropertyCount()
    }


    //TODO JARDIN FIN

    fun fetchFilteredPropertyCount() {
        viewModelScope.launch {
            val count = fetchPropertiesPreviewUseCase(
                SearchPreferences.getModoPropiedad(),
                SearchPreferences.getDropdownDbValue(),
                SearchPreferences.getGarajeChecked(),
                SearchPreferences.getJardinChecked()
            ).size
            _propertyCount.value = count
        }
    }

    fun resetFilters() {
        SearchPreferences.setGarajeChecked(false)
        SearchPreferences.setJardinChecked(false)
        _searchPerformed.value = false // Resetear el estado de búsqueda
    }

    // Estado para manejar si se ha realizado una busqueda con los filtros o no
    private val _searchPerformed = MutableStateFlow(false)
    val searchPerformed: StateFlow<Boolean> get() = _searchPerformed

    fun setSearchPerformed() {
        _searchPerformed.value = true
    }

}





