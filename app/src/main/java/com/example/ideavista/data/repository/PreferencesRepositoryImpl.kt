package com.example.ideavista.data.repository

import com.example.ideavista.data.local.DataStore.UserPreferences
import com.example.ideavista.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(private val userPreferences: UserPreferences) : PreferencesRepository {

    override suspend fun setUserAsReturning() {
        userPreferences.setUserAsReturning()
    }

    override suspend fun saveLanguage(language: String) {
        userPreferences.saveLanguage(language)
    }

    override suspend fun saveCountry(country: String) {
        userPreferences.saveCountry(country)
    }

    override fun isNewUser(): Flow<Boolean> {
        return userPreferences.isNewUser
    }

    override fun getLanguage(): Flow<String?> {
        return userPreferences.selectedLanguage
    }

    override fun getCountry(): Flow<String?> {
        return userPreferences.selectedCountry
    }

}