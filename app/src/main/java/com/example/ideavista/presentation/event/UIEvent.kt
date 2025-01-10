package com.example.ideavista.presentation.event

sealed class UIEvent {
    object NavigateToHome : UIEvent()
    data class ShowError(val message: String) : UIEvent()
}