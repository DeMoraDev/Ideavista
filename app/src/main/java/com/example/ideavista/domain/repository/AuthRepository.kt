package com.example.ideavista.domain.repository

interface AuthRepository {
    suspend fun register(email: String, password: String): Result<String>
    suspend fun login(email: String, password: String): Result<String>
    suspend fun logout()
    fun isUserLoggedIn(): Boolean
}