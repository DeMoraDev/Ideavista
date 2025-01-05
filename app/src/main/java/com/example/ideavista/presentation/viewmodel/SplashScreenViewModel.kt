package com.example.ideavista.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val _isNewUser = MutableStateFlow(true)
    val isNewUser: StateFlow<Boolean> get() = _isNewUser

    fun checkUserStatus() {
        viewModelScope.launch {
            _isNewUser.value = sharedPreferences.getBoolean("is_new_user", true)
        }
    }

    fun setUserAsReturning() {
        sharedPreferences.edit().putBoolean("is_new_user", false).apply()
    }

}