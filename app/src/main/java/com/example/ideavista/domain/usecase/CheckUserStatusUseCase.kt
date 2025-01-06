package com.example.ideavista.domain.usecase

import com.example.ideavista.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class CheckUserStatusUseCase(private val preferencesRepository: PreferencesRepository) {

    fun execute(): Flow<Boolean> = preferencesRepository.isNewUser()

}