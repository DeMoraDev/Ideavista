package com.example.ideavista.presentation.view.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Gris
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun CookieMessageContent(
    onAccept: () -> Unit,
    onReject: () -> Unit,
    onConfigure: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "En ideavista utilizamos cookies y tecnologías similares, propias" +
                    " y de terceros (ver nuestros 8 proveedores), para ofreceter una" +
                    " experiencia personalizada. Al hacerlo, procesamos datos personales" +
                    " como identificadores únicos y datos de navegación. Puedes aceptar o" +
                    " rechazar las cookies en los botones correspondientes. También" +
                    " puedes elegir qué cookies aceptar haciendo click en configurar. En" +
                    " cualquier momento, puedes retirar tu consentimiento o manifestar" +
                    " tu opinión al procesamiento basado en el interés legítimo haciendo" +
                    " clic en nuestra política de cookies.",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nosotros y nuestros proveedores hacemos el siguiente procesamiento" +
                    " de datos: anuncios personalizados y contenido, medida de anuncios" +
                    " y contenido, conocimientos de audiencia y desarrollo de productos, datos" +
                    " de geolocalización precisos e identificación a través de la exploración de" +
                    " dispositivos, almacenamiento y/o acceso de información a un dispositivo.",
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = onConfigure,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(
                Gris.copy(alpha = 0.3f),
                contentColor = Negro
            ),
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(
                text = "Configurar cookies",
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = onReject,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(Violeta),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Rechazar")
        }
        Button(
            onClick = onAccept,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(Violeta),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Aceptar y cerrar")
        }
    }
}