package com.example.ideavista.domain.usecase

import com.example.ideavista.data.local.DataStore.UserPreferences
import kotlinx.coroutines.flow.Flow

class GetLanguageUseCase(private val userPreferences: UserPreferences) {
    operator fun invoke(): Flow<String> {
        return userPreferences.selectedLanguage
    }
}