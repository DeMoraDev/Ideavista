package com.example.ideavista.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.domain.usecase.CheckUserStatusUseCase
import com.example.ideavista.domain.usecase.SetUserAsReturningUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val checkUserStatusUseCase: CheckUserStatusUseCase
) : ViewModel() {

    private val _isNewUser = MutableStateFlow(true)
    val isNewUser: StateFlow<Boolean> get() = _isNewUser

    fun checkUserStatus() {
        viewModelScope.launch {
            checkUserStatusUseCase.execute().collect { isNew ->
                _isNewUser.value = isNew
            }
        }
    }
}