package com.example.ideavista.presentation.view.composable.loginComposables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.R
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Crear cuenta",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
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
                            fontSize = 18.sp,
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
                text = "Tu email",
                fontWeight = FontWeight.Bold,
                color = NegroClaro,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 35.dp)
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
            Text(
                text = "Repite tu email",
                fontWeight = FontWeight.Bold,
                color = NegroClaro,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 35.dp)
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
            Text(
                text = "Elige una contraseña",
                fontWeight = FontWeight.Bold,
                color = NegroClaro,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 35.dp)
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
            Text(
                text = "Incluye al menos 8 caracteres, una mayúscula, una minúscula, un número y un " +
                        "carácter especial",
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp)
            )
            Button(
                onClick = { /* Continuar action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp), // Separación superior al botón
                colors = ButtonDefaults.buttonColors(Violeta),
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 19.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "¿Ya tienes cuenta?", fontSize = 18.sp)
                TextButton(
                    onClick = {}
                ) {
                    Text(text = "Iniciar sesión", color = Violeta,fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterContentPreview() {
    RegisterContent()
}