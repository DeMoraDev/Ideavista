package com.example.ideavista.presentation.view.composable.loginComposables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.R
import com.example.ideavista.presentation.view.theme.Azul
import com.example.ideavista.presentation.view.theme.Marron
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    email: String,
    onEmailEntered: (String) -> Unit,
    onContinue: () -> Unit,
    onGoogleSignIn: () -> Unit,
    errorMessage: String?
) {
    val pixelFont = FontFamily(
        Font(R.font.ideavista)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ideavista",
            fontSize = 45.sp,
            fontFamily = pixelFont,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 30.dp)
        )
        Text(
            text = "España y Andorra",
            fontSize = 17.sp
        )

        Text(
            text = "Inicia sesión o regístrate",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 24.dp)
        )

        Text(
            text = "Tu email",
            fontWeight = FontWeight.SemiBold,
            color = NegroClaro,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
        )

        var emailInput by remember { mutableStateOf(email) }

        OutlinedTextField(
            value = emailInput,
            onValueChange = {
                emailInput = it
                onEmailEntered(it)
            },
            placeholder = { Text(text = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )
        // Muestra el mensaje de error debajo del botón si existe
        if (errorMessage != null) {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error Icon",
                    tint = Marron,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(9.dp))
                Text(
                    modifier = Modifier,
                    text = errorMessage,
                    fontWeight = FontWeight.Medium,
                    color = Marron,
                    fontSize = 16.sp
                )
            }
        }

        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(Violeta),
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
        ) {
            Text(
                text = "Continuar",
                fontSize = 19.sp
            )
        }

        Text(
            text = "También puedes",
            modifier = Modifier.padding(top = 24.dp)
        )

        OutlinedButton(
            onClick = onGoogleSignIn,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Continuar con Google",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), // Espaciado externo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Puedes consultar:",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
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

    }
}
