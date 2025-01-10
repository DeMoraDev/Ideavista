package com.example.ideavista.presentation.state

//Estado para el Login

data class LoginScreenState(
    val step: LoginStep = LoginStep.Login,
    val email: String = "",
    val password: String = ""
)
