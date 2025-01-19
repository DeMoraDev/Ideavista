package com.example.ideavista.presentation.view.composable.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material3.*
import androidx.compose.material3.Text
import com.example.ideavista.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Violeta


@Composable
fun HomeContent(
    buyOnClick: () -> Unit,
    rentOnClick: () -> Unit,
    shareOnClick: () -> Unit,
    buttonState: BuyRentShareButtonOptions?

) {

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
                    .padding(top = 18.dp, start = 10.dp, end = 10.dp, bottom = 12.dp)
            ) {
                BuyRentShareButtons(
                    buyOnClick = { buyOnClick() },
                    rentOnClick = { rentOnClick() },
                    shareOnClick = { shareOnClick() },
                    buttonState = buttonState
                )
                Spacer(modifier = Modifier.height(15.dp))
                // Dropdown y botones
                MainDropdown()
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp)
                        .height(52.dp)
                        .clip(RoundedCornerShape(4.dp)) // Recorta las esquinas del Row
                        .background(color = Color.White) // Fondo blanco dentro del recorte
                        .border(
                            BorderStroke(1.5.dp, color = Color.Gray),
                            shape = RoundedCornerShape(4.dp)
                        ) // Borde redondeado
                        .padding(horizontal = 12.dp), // Asegura espacio interno para el contenido
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "¿Dónde quieres buscar?",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.location_icon),
                        contentDescription = "Location",
                        tint = Violeta,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(4.dp),  // Define los bordes redondeados
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),  // Añadiendo paddings sin el `clip`
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
                .padding(10.dp)
        ) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                border = BorderStroke(3.dp, color = Violeta),
                colors = ButtonDefaults.buttonColors(Blanco),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = "Publica tu anuncio",
                    color = Violeta,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    HomeContent(
        buyOnClick = TODO(),
        rentOnClick = TODO(),
        shareOnClick = TODO(),
        buttonState = TODO()
    )
}