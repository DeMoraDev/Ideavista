package com.example.ideavista.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _isUserLoggedIn = mutableStateOf(authRepository.isUserLoggedIn())
    val isUserLoggedIn: State<Boolean> get() = _isUserLoggedIn

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logout()
            _isUserLoggedIn.value = false
        }
    }
}
