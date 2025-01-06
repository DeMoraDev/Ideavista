package com.example.ideavista.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    // Estados para los pasos del onboarding
    private val _selectedLanguage = MutableStateFlow<String?>(null)
    val selectedLanguage: StateFlow<String?> = _selectedLanguage

    private val _selectedCountry = MutableStateFlow<String?>(null)
    val selectedCountry: StateFlow<String?> = _selectedCountry

    // Función para seleccionar idioma
    fun selectLanguage(language: String) {
        _selectedLanguage.value = language
    }

    // Función para seleccionar país
    fun selectCountry(country: String) {
        _selectedCountry.value = country
    }
}
