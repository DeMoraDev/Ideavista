package com.example.ideavista.domain.usecase

import com.example.ideavista.domain.repository.PreferencesRepository

class SaveLanguageUseCase(private val preferencesRepository: PreferencesRepository) {
    suspend fun execute(language: String) {
        preferencesRepository.saveLanguage(language)
    }
}