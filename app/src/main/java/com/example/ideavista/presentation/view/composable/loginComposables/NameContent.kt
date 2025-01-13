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
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Error
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Azul
import com.example.ideavista.presentation.view.theme.Gris
import com.example.ideavista.presentation.view.theme.Marron
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameContent(
    email: String,
    confirmEmail: String,
    password: String,
    name: String,
    onNameEntered: (String) -> Unit,
    onRegister: (String, String, String) -> Unit,
    onCheckedPrivacy: (Boolean) -> Unit
) {
    var currentName by remember { mutableStateOf(name) }
    var showNameError by remember { mutableStateOf(false) }

    var checkedPrivacy by remember { mutableStateOf(false) }
    var checkedNews by remember { mutableStateOf(false) }

    // Condiciones para validar el nombre
    val nameConditions = listOf(
        "Debe tener al menos 2 caracteres" to { currentName.length >= 2 },
        "No debe contener números" to { currentName.all { it.isLetter() || it.isWhitespace() || it in "'-" } },
        "No debe empezar ni terminar con un espacio" to { currentName.trim() == currentName },
        "No debe superar los 50 caracteres" to { currentName.length <= 50 }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Solo nos falta tu nombre",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = NegroClaro,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = "Escribe tu nombre",
            fontWeight = FontWeight.Bold,
            color = NegroClaro,
            modifier = Modifier.padding(top = 30.dp)
        )
        OutlinedTextField(
            value = currentName,
            onValueChange = {
                currentName = it
                showNameError = false // Reinicia el error cuando el usuario escribe
                onNameEntered(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            isError = showNameError,
            trailingIcon = {
                if (currentName.isNotEmpty()) {
                    IconButton(onClick = { currentName = "" }) {
                        Icon(
                            imageVector = Icons.Default.Dangerous,
                            contentDescription = "Clear text",
                            tint = Color.Black
                        )
                    }
                }
            }
        )
        //TODO CheckBoxes no están alineados, enlaces de privacidad, cookies tampoco y añadir que sea necesario checkear privacidad para registrarse
        val unmetConditions = nameConditions.filter { !it.second() }.map { it.first }

        if (showNameError && unmetConditions.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error Icon",
                        tint = Marron,
                        modifier = Modifier
                            .size(20.dp)
                            .alignBy(FirstBaseline)
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Text(
                        text = "Tu nombre debe cumplir las siguientes condiciones:",
                        color = Marron,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                            .alignBy(FirstBaseline)
                    )
                }
                unmetConditions.forEach { condition ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            start = 40.dp,
                            bottom = 1.dp
                        )
                    ) {
                        Text(
                            text = "·",
                            color = Marron,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(end = 8.dp)
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
        }
        Text(
            text = "Te recomendamos que uses tu nombre real. Lo compartirás " +
                    "con los anunciantes al contactar",
            fontWeight = FontWeight.Medium,
            color = Gris,
            modifier = Modifier.padding(top = 4.dp)
        )
        CheckboxWithText(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            checked = checkedPrivacy,
            onCheckedChange = { checkedPrivacy = it },
            text = "Aceptar la política de privacidad y los términos y condiciones generales",
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            TextButton(
                onClick = { },
                modifier = Modifier.padding(0.dp) // Elimina padding adicional
            ) {
                Text(
                    text = "Política de privacidad",
                    fontSize = 15.sp,
                    color = Azul,
                    fontWeight = FontWeight.SemiBold
                )
            }
            TextButton(
                onClick = { },
                modifier = Modifier.padding(0.dp) // Elimina padding adicional
            ) {
                Text(
                    text = "Términos y condiciones generales",
                    fontSize = 15.sp,
                    color = Azul,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        CheckboxWithText(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            checked = checkedNews,
            onCheckedChange = { checkedNews = it },
            text = "Recibir información comercial de inmuebles, noticias y ofertas de servicios desde " +
                    "ideavista, id/hipotecas; id/seguros; Avaibook; BDMI; Rentalia.",
        )
        Button(
            onClick = {
                showNameError = unmetConditions.isNotEmpty()

                if (!showNameError) {
                    onRegister(email, password, confirmEmail)
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
                text = "Crear nueva cuenta",
                fontSize = 19.sp
            )
        }
    }
}
