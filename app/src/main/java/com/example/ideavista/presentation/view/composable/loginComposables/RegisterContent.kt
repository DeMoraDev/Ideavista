package com.example.ideavista.presentation.view.composable.loginComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.R
import com.example.ideavista.presentation.view.theme.Marron
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    email: String,
    onEmailConfirmed: (String) -> Unit,
    onPasswordRegister: (String) -> Unit,
    onContinue: () -> Unit,
    goToLoginOnClick: () -> Unit
) {
    var confirmEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    //TODO Hay que migrar esta logica fuera del composable
    val passwordConditions = listOf(
        stringResource(id = R.string.registerContent_password_error_caps) to { password.any { it.isUpperCase() } },
        stringResource(id = R.string.registerContent_password_error_numbers) to { password.any { it.isDigit() } },
        stringResource(id = R.string.registerContent_password_error_special_character) to { password.any { ".,!#$%&*()-_+?<>".contains(it) } },
        stringResource(id = R.string.registerContent_password_error_at_least_eight_characters) to { password.length >= 8 }
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.registerContent_email_input),
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 30.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {},
            readOnly = true, //Campo solo de lectura
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            isError = showEmailError
        )
        if (showEmailError && email != confirmEmail) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error Icon",
                    tint = Marron,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el icono y el texto
                Text(
                    text = stringResource(id = R.string.registerContent_email_error),
                    color = Marron,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Text(
            text = stringResource(id = R.string.registerContent_email_repeat_input),
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 20.dp)
        )
        OutlinedTextField(
            value = confirmEmail,
            onValueChange = {
                confirmEmail = it
                showEmailError = false
                onEmailConfirmed(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            isError = showEmailError,
            trailingIcon = {
                if (confirmEmail.isNotEmpty()) {
                    IconButton(onClick = { confirmEmail = "" }) {
                        Icon(
                            imageVector = Icons.Default.Dangerous,
                            contentDescription = "Clear text",
                            tint = Color.Black
                        )
                    }
                }
            }
        )
        if (showEmailError && email != confirmEmail) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error Icon",
                    tint = Marron,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el icono y el texto
                Text(
                    text = stringResource(id = R.string.registerContent_password_error_caps),
                    color = Marron,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Text(
            text = stringResource(id = R.string.registerContent_password_input),
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 20.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                onPasswordRegister(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Outlined.Visibility // Ícono para mostrar la contraseña
                } else {
                    Icons.Default.Visibility // Ícono para ocultar la contraseña
                }

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            isError = showPasswordError
        )
        val unmetConditions = passwordConditions.filter { !it.second() }.map { it.first }

        if (showPasswordError && unmetConditions.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top // Alinea respecto a la parte superior del texto
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error Icon",
                        tint = Marron,
                        modifier = Modifier
                            .size(20.dp)
                            .alignBy(FirstBaseline) // Alinea con la línea base del texto
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Text(
                        text = stringResource(id = R.string.registerContent_password_error_message),
                        color = Marron,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                            .alignBy(FirstBaseline) // Alinea con la línea base del ícono
                    )
                }
                unmetConditions.forEach { condition ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            start = 40.dp,
                            bottom = 1.dp
                        ) // Sangría y espacio entre líneas
                    ) {
                        Text(
                            text = "·",
                            color = Marron,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(end = 8.dp) // Espacio después del "·"
                        )
                        Text(
                            text = condition,
                            color = Marron,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        } else {
            Text(
                text = stringResource(id = R.string.registerContent_password_conditions)
            )
        }

        Button(
            onClick = {
                showEmailError = email != confirmEmail
                showPasswordError = unmetConditions.isNotEmpty()

                if (!showEmailError && !showPasswordError) {
                    onContinue()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            colors = ButtonDefaults.buttonColors(Violeta),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
        ) {
            Text(
                text = stringResource(id = R.string.registerContent_button_continue),
                fontSize = 19.sp
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.registerContent_already_account),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            TextButton(
                onClick = goToLoginOnClick
            ) {
                Text(
                    text = stringResource(id = R.string.registerContent_login_button),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Violeta
                )
            }
        }
    }
}
