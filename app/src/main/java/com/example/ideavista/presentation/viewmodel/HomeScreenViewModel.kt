package com.example.ideavista.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ideavista.presentation.state.BuyRentButtonState
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel : ViewModel() {

    private val _uiState = mutableStateOf(HomeScreenState())
    val uiState: State<HomeScreenState> get() = _uiState

    fun updateStep(newStep: HomeContentStep) {
        _uiState.value = _uiState.value.copy(step = newStep)
    }

    private val _buyRentState = MutableStateFlow(BuyRentButtonState(selectedOption = BuyRentShareButtonOptions.COMPRAR))
    val buyRentState: StateFlow<BuyRentButtonState> get() = _buyRentState

    fun onBuyRentButtonClicked(option: BuyRentShareButtonOptions) {
        _buyRentState.value = _buyRentState.value.copy(selectedOption = option)
    }
}