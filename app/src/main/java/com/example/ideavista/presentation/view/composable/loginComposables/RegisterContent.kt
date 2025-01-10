package com.example.ideavista.presentation.view.composable.loginComposables

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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Marron
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    email: String,
    onEmailConfirmed: (String) -> Unit,
    onRegister: (String, String) -> Unit
) {
    var confirmEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember {mutableStateOf(false)}

    //TODO Hay que migrar esta logica fuera del composable
    val passwordConditions = listOf(
        "Una mayúscula" to { password.any { it.isUpperCase() } },
        "Un número" to { password.any { it.isDigit() } },
        "Un carácter especial" to { password.any { ".,!#$%&*()-_+?<>".contains(it) } },
        "Al menos 8 caracteres" to { password.length >= 8 }
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Tu email",
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 35.dp)
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
                    imageVector = Icons.Default.Info, // Puedes usar otro icono si lo prefieres
                    contentDescription = "Error Icon",
                    tint = Marron,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el icono y el texto
                Text(
                    text = "Los emails no coinciden",
                    color = Marron,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Text(
            text = "Repite tu email",
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 35.dp)
        )
        OutlinedTextField(
            value = confirmEmail,
            onValueChange = {
                confirmEmail = it
                showEmailError = false
            },
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
                    imageVector = Icons.Default.Info, // Puedes usar otro icono si lo prefieres
                    contentDescription = "Error Icon",
                    tint = Marron,
                    modifier = Modifier.size(21.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el icono y el texto
                Text(
                    text = "Los emails no coinciden",
                    color = Marron,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Text(
            text = "Elige una contraseña",
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 35.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            visualTransformation = PasswordVisualTransformation(),
            isError = showPasswordError
        )
        val unmetConditions = passwordConditions.filter { !it.second() }.map { it.first }

        if (showPasswordError && unmetConditions.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "La contraseña no cumple con los siguientes requisitos:",
                    color = Marron,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                unmetConditions.forEach { condition ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Error Icon",
                            tint = Marron,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = condition,
                            color = Marron,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }else{
            Text(
                text = "Incluye al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter" +
                        " especial"
            )
        }

        Button(
            onClick = {
                showEmailError = email != confirmEmail
                showPasswordError = unmetConditions.isNotEmpty()
                if (!showEmailError && !showPasswordError) {
                    onRegister(email, password)
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
                text = "Continuar",
                fontSize = 19.sp
            )
        }
    }
}
