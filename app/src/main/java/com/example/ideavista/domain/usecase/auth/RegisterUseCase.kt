package com.example.ideavista.domain.usecase.auth

import com.example.ideavista.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<String> {
        return repository.register(email, password)
    }
}