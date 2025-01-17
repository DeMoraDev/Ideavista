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
import com.example.ideavista.presentation.event.UIEvent
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
    val passwordError: State<String?> get() = _emailError

    private val _emailError = mutableStateOf<String?>(null)
    val emailError: State<String?> get() = _emailError


    //Checkboxes de Privacidad y Cookies
    private val _isCheckedPrivacy = mutableStateOf(false)
    val isCheckedPrivacy: State<Boolean> get() = _isCheckedPrivacy

    fun onCheckedPrivacy(isChecked: Boolean) {
        _isCheckedPrivacy.value = isChecked
    }



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
        isRegisteredUser(email) { isRegistered ->
            when {
                isRegistered -> {
                    // Usuario ya registrado, cambiar al paso de iniciar sesión
                    _uiState.value =
                        _uiState.value.copy(step = LoginStep.AlreadyUser, emailError = null)
                }

                else -> {
                    // Usuario no registrado, pasar al paso de registro o nombre dependiendo de cómo lo manejes
                    if (_uiState.value.step == LoginStep.Register) {
                        _uiState.value =
                            _uiState.value.copy(step = LoginStep.Name, emailError = null)
                    } else {
                        _uiState.value =
                            _uiState.value.copy(step = LoginStep.Register, emailError = null)
                    }
                }
            }
        }
    }

    // Función de validación (opcional)
    private fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Función para manejar el evento de registro
    fun onEmailConfirmed(confirmEmail: String) {
        _uiState.value = _uiState.value.copy(confirmEmail = confirmEmail, step = LoginStep.Register)
    }

    // Función para manejar el ingreso de contraseña y login
    fun onPasswordEntered(password: String) {
        if (!validatePassword(password)) {
            _authState.value = AuthState.Error(_emailError.value ?: "Contraseña no válida")
            return
        }
        login(_uiState.value.email, password)
    }

    fun onPasswordRegister(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onNameEntered(name: String) {
        if (!validateName(name)) {
            _authState.value = AuthState.Error(_emailError.value ?: "Nombre no válido")
            return
        }
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun validateName(name: String): Boolean {
        return name.length >= 2 &&
                name.all { it.isLetter() || it.isWhitespace() || it in "'-" } &&
                name.trim() == name &&
                name.length <= 50
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

    fun resetToLogin() {
        _uiState.value = LoginScreenState()
    }

    // Validación de la contraseña
    private fun validatePassword(password: String): Boolean {
        return when {
            password.length < 8 -> {
                _emailError.value = "La contraseña debe tener al menos 8 caracteres."
                false
            }

            !password.any { it.isDigit() } -> {
                _emailError.value = "La contraseña debe contener al menos un número."
                false
            }

            !password.any { it.isUpperCase() } -> {
                _emailError.value = "La contraseña debe contener al menos una letra mayúscula."
                false
            }

            !password.any { it.isLowerCase() } -> {
                _emailError.value = "La contraseña debe contener al menos una letra minúscula."
                false
            }

            !password.any { "!@#\$%^.&*()-_=+<>?".contains(it) } -> {
                _emailError.value = "La contraseña debe contener al menos un carácter especial."
                false
            }

            else -> {
                _emailError.value = null
                true
            }
        }
    }

    fun validateEmails(email: String, confirmEmail: String): Boolean {
        return email == confirmEmail
    }

    // Sobrecarga de la función de registro para manejar validaciones
    fun register(email: String, password: String, confirmEmail: String) {
        val name = _uiState.value.name

        if (!validatePassword(password)) {
            _authState.value = AuthState.Error(_passwordError.value ?: "Error en la validación de la password")
            return
        }
        if (!validateEmails(email, confirmEmail)) {
            _authState.value = AuthState.Error("El correo electrónico de confirmación no coincide.")
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
                        "name" to name
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

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val result = loginUseCase.invoke(email, password)
            if (result.isSuccess) {
                Log.d("Login", "Usuario logeado correctamente: $email")
                _authState.value = AuthState.Success // Actualiza el estado de autenticación
                _uiEvent.value = UIEvent.NavigateToHome // Navega al home
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Unknown Error"
                Log.e("Login", "Error al iniciar sesión: $errorMessage")
                _authState.value = AuthState.Error(errorMessage) // Muestra error en el estado
                _uiEvent.value = UIEvent.ShowError("Error de inicio de sesión: $errorMessage")
            }
        }
    }

    //Resetear evento para envitar problemas
    fun resetUiEvent() {
        _uiEvent.value = null
    }

}