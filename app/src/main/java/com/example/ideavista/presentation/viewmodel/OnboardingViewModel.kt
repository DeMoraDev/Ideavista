package com.example.ideavista.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.usecase.SaveCountryUseCase
import com.example.ideavista.domain.usecase.SaveLanguageUseCase
import com.example.ideavista.domain.usecase.SetUserAsReturningUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,
    private val setUserAsReturningUseCase: SetUserAsReturningUseCase
) : ViewModel() {

    // Estado para el idioma seleccionado
    private val _selectedLanguage = MutableStateFlow<String?>(null)
    val selectedLanguage: StateFlow<String?> = _selectedLanguage

    // Estado para el país seleccionado
    private val _selectedCountry = MutableStateFlow<String?>(null)
    val selectedCountry: StateFlow<String?> = _selectedCountry

    fun selectLanguage(language: String) {
        viewModelScope.launch {
            _selectedLanguage.value = language // Actualiza el estado local
            saveLanguageUseCase.execute(language) // Guarda el estado
        }
    }

    fun selectCountry(country: String) {
        viewModelScope.launch {
            _selectedCountry.value = country // Actualiza el estado local
            saveCountryUseCase.execute(country) // Guarda el estado
        }
    }

    //Marca las preferencias como completadas- Usuario no nuevo
    fun setUserAsReturning() {
        viewModelScope.launch {
            setUserAsReturningUseCase.execute()
        }
    }
}