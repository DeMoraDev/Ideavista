package com.example.ideavista.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun setUserAsReturning()
    suspend fun saveLanguage(language: String)
    suspend fun saveCountry(country: String)

    fun isNewUser(): Flow<Boolean>
    fun getLanguage(): Flow<String?>
    fun getCountry(): Flow<String?>
}