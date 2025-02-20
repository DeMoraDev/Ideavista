package com.example.ideavista.data.local.DataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale

val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {
    private object PreferencesKeys {
        val IS_NEW_USER = booleanPreferencesKey("is_new_user")
        val LANGUAGE = stringPreferencesKey("language")
        val COUNTRY = stringPreferencesKey("country")
    }

    suspend fun setUserAsReturning() {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_NEW_USER] = false
        }
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = language
        }
    }

    suspend fun saveCountry(country: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.COUNTRY] = country
        }
    }

    val isNewUser: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.IS_NEW_USER] ?: true
        }

    val selectedLanguage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.LANGUAGE] ?: Locale.getDefault().language
        }


    val selectedCountry: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.COUNTRY]
        }
}