package com.example.ideavista.domain.usecase

import com.example.ideavista.domain.repository.PreferencesRepository

class SaveCountryUseCase(private val preferencesRepository: PreferencesRepository) {
    suspend fun execute(country: String) {
        preferencesRepository.saveCountry(country)
    }
}