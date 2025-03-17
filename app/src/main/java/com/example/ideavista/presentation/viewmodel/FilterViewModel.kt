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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FilterViewModel(private val fetchPropertiesPreviewUseCase: FetchPropertiesPreviewUseCase) :
    ViewModel() {


        //Se ejecuta cada vez que filters cambia,
        // evitando la necesidad de llamar manualmente a fetchFilteredPropertyCount()
    init {
        viewModelScope.launch {
            SearchPreferences.filters.collectLatest {
                fetchFilteredPropertyCount()
            }
        }
    }


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


    private val _propertiesPreview = MutableStateFlow<List<PropertyPreview>>(emptyList())
    val propertiesPreview: StateFlow<List<PropertyPreview>> = _propertiesPreview



    // TODO GARAJE

    val filters: StateFlow<Map<String, Boolean?>> = SearchPreferences.filters
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyMap())

    fun updateFilter(key: String, isChecked: Boolean) {
        SearchPreferences.setFilter(key, isChecked)
    }




    //TODO JARDIN FIN

    fun fetchFilteredPropertyCount() {
        viewModelScope.launch {
            val count = fetchPropertiesPreviewUseCase(
                SearchPreferences.getModoPropiedad(),
                SearchPreferences.getDropdownDbValue(),
                SearchPreferences.getFilters()
            ).size
            _propertyCount.value = count
        }
    }

    fun resetFilters() {
        SearchPreferences.clearFilters()
        _searchPerformed.value = false
    }

    // Estado para manejar si se ha realizado una busqueda con los filtros o no
    private val _searchPerformed = MutableStateFlow(false)
    val searchPerformed: StateFlow<Boolean> get() = _searchPerformed

    fun setSearchPerformed() {
        _searchPerformed.value = true
    }

}





