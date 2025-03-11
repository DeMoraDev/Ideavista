package com.example.ideavista.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ThemeViewModel() : ViewModel() {

   /* private val _selectedTheme = MutableStateFlow<String?>("Light")
    val selectedTheme: StateFlow<String?> = _selectedTheme

    fun selectTheme(theme: String) {
        viewModelScope.launch {
            _selectedTheme.value = theme
            //Use case para guardar el theme en DataStore
        }
    }
    */



    //Tipo boolear
    // Usamos StateFlow en lugar de mutableStateOf
    private val _isDarkTheme = MutableStateFlow(false)  // Inicializa en false (Modo claro)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme  // Exponemos como StateFlow

    fun setTheme(darkMode: Boolean) {
        _isDarkTheme.value = darkMode  // Cambia el valor de _isDarkTheme
        Log.d("Theme", "Cambiando a modo oscuro: $darkMode")
    }

}