package com.example.ideavista.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.HomeScreenState
import com.example.ideavista.presentation.state.LoginScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel : ViewModel() {

    private val _uiState = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    fun updateStep(newStep: HomeContentStep) {
        _uiState.value = _uiState.value.copy(step = newStep)
    }

}