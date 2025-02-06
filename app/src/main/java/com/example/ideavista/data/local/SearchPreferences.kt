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
        private val _garajeChecked = MutableStateFlow<Boolean?>(null)
        val garajeChecked: StateFlow<Boolean?> get() = _garajeChecked

        fun setGarajeChecked(value: Boolean) {
            _garajeChecked.value = if (value) true else null
        }

        fun getGarajeChecked(): Boolean? {
            return _garajeChecked.value
        }

        // Aquí puedes agregar más filtros como precio, número de habitaciones, etc.

        fun resetFilters() {
            _garajeChecked.value = null
        }
    }

}

