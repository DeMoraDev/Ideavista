package com.example.ideavista.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ideavista.domain.usecase.auth.LoginUseCase
import com.example.ideavista.domain.usecase.auth.RegisterUseCase
import com.example.ideavista.presentation.state.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.ideavista.presentation.state.LoginScreenState
import com.example.ideavista.presentation.state.LoginStep
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.ideavista.presentation.event.UIEvent
import com.example.ideavista.presentation.view.navigation.NavigationRoutes.login
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginScreenViewModel(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _uiState = mutableStateOf(LoginScreenState())
    val uiState: State<LoginScreenState> get() = _uiState

    //Evento para navegar al Home al logearse
    private val _uiEvent = MutableStateFlow<UIEvent?>(null)
    val uiEvent: StateFlow<UIEvent?> = _uiEvent

    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError: State<String?> get() = _passwordError


    // Función para manejar el cambio de contraseña
    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    // Función para manejar el evento de olvido de contraseña
    fun onForgotPassword() {
        viewModelScope.launch {
            // Aquí puedes agregar la lógica para enviar un correo de recuperación.
            _authState.value = AuthState.Error("Forgot Password not implemented yet.")
        }
    }


    //Boton Continuar del Login
    fun onContinueClick() {
        val email = _uiState.value.email

        val errorMessage = when {
            email.isBlank() -> "El campo de correo no puede estar vacío."
            !validateEmail(email) -> "Revisa el email, parece que hay un error"
            else -> null
        }

        if (errorMessage != null) {
            _uiState.value = _uiState.value.copy(emailError = errorMessage)
            return
        }
        //TODO mensaje de registro satisfactirior
        //TODO hashear contraseña y deshashear

        isRegisteredUser(email) { isRegistered ->
            if (isRegistered) {
                // Usuario ya registrado, cambiar al paso de iniciar sesión
                _uiState.value = _uiState.value.copy(step = LoginStep.AlreadyUser, emailError = null)
            } else {
                // Usuario no registrado, cambiar al paso de registro
                _uiState.value = _uiState.value.copy(step = LoginStep.Register, emailError = null)
            }
        }
    }

    // Función de validación (opcional)
    private fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Función para manejar el evento de registro
    fun onEmailConfirmed(email: String) {
        _uiState.value = _uiState.value.copy(email = email, step = LoginStep.Register)
    }

    // Función para manejar el ingreso de contraseña y login
    fun onPasswordEntered(password: String) {
        if (!validatePassword(password)) {
            _authState.value = AuthState.Error(_passwordError.value ?: "Error de validación")
            return
        }
        login(_uiState.value.email, password)
    }

    //Llamada a la API de Firebase para ver si el email está registrado
    fun isRegisteredUser(email: String, callback: (Boolean) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users").whereEqualTo("email", email).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot = task.result
                    callback(querySnapshot?.documents?.isNotEmpty() == true)
                } else {
                    Log.e(
                        "FirestoreQuery",
                        "Error al verificar si el usuario está registrado.",
                        task.exception
                    )
                    callback(false)
                }
            }
    }

    // Función para manejar inicio de sesión con Google
    fun onGoogleSignIn() {
        viewModelScope.launch {
            // Aquí puedes implementar la lógica de inicio de sesión con Google.
            _authState.value = AuthState.Error("Google Sign-In not implemented yet.")
        }
    }

    // Función para manejar el registro del usuario
    fun onRegister(email: String, password: String) {
        register(email, password)
    }

    fun resetToLogin() {
        _uiState.value = LoginScreenState()
    }

    // Validación de la contraseña
    private fun validatePassword(password: String): Boolean {
        return when {
            password.length < 8 -> {
                _passwordError.value = "La contraseña debe tener al menos 8 caracteres."
                false
            }
            !password.any { it.isDigit() } -> {
                _passwordError.value = "La contraseña debe contener al menos un número."
                false
            }
            !password.any { it.isUpperCase() } -> {
                _passwordError.value = "La contraseña debe contener al menos una letra mayúscula."
                false
            }
            !password.any { it.isLowerCase() } -> {
                _passwordError.value = "La contraseña debe contener al menos una letra minúscula."
                false
            }
            !password.any { "!@#\$%^.&*()-_=+<>?".contains(it) } -> {
                _passwordError.value = "La contraseña debe contener al menos un carácter especial."
                false
            }
            else -> {
                _passwordError.value = null
                true
            }
        }
    }

    // Sobrecarga de la función de registro para manejar validaciones
    fun register(email: String, password: String) {

        if (!validatePassword(password)) {
            _authState.value = AuthState.Error(_passwordError.value ?: "Error de validación")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = registerUseCase.invoke(email, password)
            if (result.isSuccess) {
                // Obtener el UID del usuario autenticado
                val firebaseUser = FirebaseAuth.getInstance().currentUser
                val uid = firebaseUser?.uid

                if (uid != null) {
                    // Crear un nuevo documento en la colección `users`
                    val user = hashMapOf(
                        "email" to email,
                        "registration_date" to Timestamp.now(),
                        "user_id" to uid,
                        "password" to password
                    )

                    // Guardar el documento con el UID como ID
                    FirebaseFirestore.getInstance().collection("users").document(uid).set(user)
                        .addOnCompleteListener { firestoreResult ->
                            if (firestoreResult.isSuccessful) {
                                _authState.value = AuthState.Success
                            } else {
                                _authState.value = AuthState.Error(
                                    firestoreResult.exception?.message
                                        ?: "Error al guardar datos en Firestore"
                                )
                            }
                        }
                } else {
                    _authState.value = AuthState.Error("No se pudo obtener el UID del usuario.")
                }
            } else {
                _authState.value =
                    AuthState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }

    // TODO: Hashear y deshashear Password, manejar mejor los inputs de email y contraseña(igual o no),
    // TODO: Condiciones de contraseña, ojo para ocultar, 4ºStep para el nombre en el register

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = loginUseCase.invoke(email, password)
            if (result.isSuccess) {
                Log.d("Login", "Usuario logeado correctamente: $email")
                _authState.value = AuthState.Success
                _uiEvent.value = UIEvent.NavigateToHome // Emitimos evento para navegar
            } else {
                _authState.value =
                    AuthState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
                _uiEvent.value = UIEvent.ShowError("Error de inicio de sesión")
            }
        }
    }

    //Resetear evento para envitar problemas
    fun resetUiEvent() {
        _uiEvent.value = null
    }

}