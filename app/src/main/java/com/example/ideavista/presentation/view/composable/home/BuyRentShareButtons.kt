package com.example.ideavista.presentation.view.composable.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun BuyRentShareButtons(
    selectedOption: BuyRentShareButtonOptions,
    onOptionSelected: (BuyRentShareButtonOptions) -> Unit
) {
    Log.d("BuyRentUI", "Estado actual en la UI: $selectedOption")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
            .height(54.dp)
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón 1 - Comprar
            Button(
                onClick = { onOptionSelected(BuyRentShareButtonOptions.COMPRAR) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == BuyRentShareButtonOptions.COMPRAR)
                        Violeta.copy(alpha = 0.1f) else Color.White
                ),
                border = BorderStroke(
                    2.dp,
                    if (selectedOption == BuyRentShareButtonOptions.COMPRAR) Violeta else Color.Gray
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = "Comprar",
                    color = if (selectedOption == BuyRentShareButtonOptions.COMPRAR) Violeta else Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            // Botón 2 - Alquilar
            Button(
                onClick = { onOptionSelected(BuyRentShareButtonOptions.ALQUILAR) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == BuyRentShareButtonOptions.ALQUILAR)
                        Violeta.copy(alpha = 0.1f) else Color.White
                ),
                border = BorderStroke(
                    2.dp,
                    if (selectedOption == BuyRentShareButtonOptions.ALQUILAR) Violeta else Color.Gray
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = "Alquilar",
                    color = if (selectedOption == BuyRentShareButtonOptions.ALQUILAR) Violeta else Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            // Botón 3 - Compartir
            Button(
                onClick = { onOptionSelected(BuyRentShareButtonOptions.COMPARTIR) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == BuyRentShareButtonOptions.COMPARTIR)
                        Violeta.copy(alpha = 0.1f) else Color.White
                ),
                border = BorderStroke(
                    2.dp,
                    if (selectedOption == BuyRentShareButtonOptions.COMPARTIR) Violeta else Color.Gray
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = "Compartir",
                    color = if (selectedOption == BuyRentShareButtonOptions.COMPARTIR) Violeta else Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}
