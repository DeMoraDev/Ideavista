package com.example.ideavista.presentation.view.composable.loginComposables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBackContent() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Iniciar sesión",
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Back action */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { /* Ahora no action */ }) {
                        Text(
                            text = "Ahora no",
                            color = Violeta,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Amarillo
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hola de nuevo ;-)",
                fontWeight = FontWeight.Bold,
                color = NegroClaro,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 35.dp)
            )
            Text(
                text = "eduardoelbastardo55@gmail.com",
                fontWeight = FontWeight.SemiBold,
                color = NegroClaro,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            )
            Text(
                text = "Contraseña",
                fontWeight = FontWeight.SemiBold,
                color = NegroClaro,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 16.dp)
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* No action */ },
                placeholder = { Text(text = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black
                )
            )
            TextButton(
                onClick = {}
            ) {
                Text(text = "¿Has olvidado tu contraseña?", color = Violeta)
            }

            Button(
                onClick = { /* Continuar action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp), // Separación superior al botón
                colors = ButtonDefaults.buttonColors(Violeta),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 19.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun UserBackContentPreview() {
    UserBackContent()
}