package com.example.ideavista.data.local.DataStore.model

data class UserPreferencesModel(
    val isNewUser: Boolean = true,
    val language: String? = null,
    val country: String? = null
)
