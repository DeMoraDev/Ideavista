package com.example.ideavista.presentation.view.composable.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Gris


@Composable
fun SingleOptionMaps(
    imageRes: Int,
    title: String,
    subtitle: String,
    onClickOptionMaps: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickOptionMaps() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp) // Mantiene el mismo alto que la imagen
        ) {
            // Imagen a la izquierda
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Imagen",
                modifier = Modifier.size(90.dp)
            )

            // Columna que tiene el borde y envuelve otra columna con padding
            Column(
                modifier = Modifier
                    .fillMaxHeight() // Hace que ocupe el alto completo del Row
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        val halfStrokeWidth = strokeWidth / 2

                        // Borde superior
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, halfStrokeWidth),
                            end = Offset(size.width, halfStrokeWidth),
                            strokeWidth = strokeWidth
                        )

                        // Borde inferior
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, size.height - halfStrokeWidth),
                            end = Offset(size.width, size.height - halfStrokeWidth),
                            strokeWidth = strokeWidth
                        )

                        // Borde derecho
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(size.width - halfStrokeWidth, 0f),
                            end = Offset(size.width - halfStrokeWidth, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
            ) {
                // Columna interna que contiene los textos con su padding
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = subtitle,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
