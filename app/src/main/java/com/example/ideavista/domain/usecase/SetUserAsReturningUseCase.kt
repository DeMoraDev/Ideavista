package com.example.ideavista.domain.usecase

import com.example.ideavista.domain.repository.PreferencesRepository

class SetUserAsReturningUseCase(private val preferencesRepository: PreferencesRepository) {
    suspend fun execute() {
        preferencesRepository.setUserAsReturning()
    }
}