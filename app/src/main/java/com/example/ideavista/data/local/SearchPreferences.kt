package com.example.ideavista.data.local

import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.PropertyType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchPreferences {
    companion object {
        //Modo propiedad
        // Estado para almacenar la opción seleccionada
        private val _selectedOption = MutableStateFlow<BuyRentShareButtonOptions?>(null)
        val selectedOption: StateFlow<BuyRentShareButtonOptions?> get() = _selectedOption

        // Función para actualizar la opción seleccionada
        fun setSelectedOption(option: BuyRentShareButtonOptions) {
            _selectedOption.value = option
        }

        // Función para obtener el tipo de propiedad basado en la opción seleccionada
        fun getModoPropiedad(): String {
            return when (_selectedOption.value) {
                BuyRentShareButtonOptions.COMPRAR -> "comprar"
                BuyRentShareButtonOptions.ALQUILAR -> "alquilar"
                BuyRentShareButtonOptions.COMPARTIR -> "compartir"
                null -> ""
            }
        }

        //Tipo Propiedad

        private val _selectedDropdownOption = MutableStateFlow<PropertyType?>(null)
        val selectedDropdownOption: StateFlow<PropertyType?> get() = _selectedDropdownOption

        fun setSelectedDropdownOption(option: PropertyType) {
            _selectedDropdownOption.value = option
        }

        fun getDropdownDbValue(): String {
            return _selectedDropdownOption.value?.dbValue ?: ""
        }

        // Filtros adicionales

        private val _filters = MutableStateFlow<Map<String, Boolean?>>(emptyMap())
        val filters: StateFlow<Map<String, Boolean?>> get() = _filters

        fun setFilter(key: String, value: Boolean) {
            _filters.value = _filters.value.toMutableMap().apply {
                this[key] = if (value) true else null
            }
        }

        fun getFilters(): Map<String, Boolean?> {
            return _filters.value
        }


        fun clearFilters() {
            _filters.value = emptyMap()
        }
    }

}

