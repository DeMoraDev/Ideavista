package com.example.ideavista.presentation.view.composable.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
import com.example.ideavista.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Violeta


@Composable
fun HomeContent() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.livingroom1),
                contentDescription = "Imagen Home",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        // Primer Box (amarillo)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Amarillo),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ocupa solo el ancho disponible
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Botón 1
                    Button(
                        onClick = { /* Acción del botón */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.Gray),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(text = "Comprar", color = Color.Black)
                    }

                    // Botón 2
                    Button(
                        onClick = { /* Acción del botón */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.Gray),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(text = "Alquilar", color = Color.Black)
                    }

                    // Botón 3
                    Button(
                        onClick = { /* Acción del botón */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.Gray),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(text = "Compartir", color = Color.Black)
                    }
                }

                // Dropdown y botones
                MainDropdown()
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color.Gray),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Text(text = "¿Dónde quieres buscar?", color = Color.Black)
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    Text(text = "Buscar", color = Blanco)
                }
            }
        }

        // Segundo Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) // Espaciado opcional entre los boxes
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                shape = RectangleShape,
                border = BorderStroke(1.dp, color = Violeta),
                colors = ButtonDefaults.buttonColors(Blanco),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(text = "Publica tu anuncio", color = Violeta)
            }
        }
    }
}


@Preview
@Composable
fun HomeContentPreview() {
    HomeContent()
}