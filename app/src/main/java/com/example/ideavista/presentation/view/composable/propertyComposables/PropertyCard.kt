package com.example.ideavista.presentation.view.composable.propertyComposables

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.ideavista.R
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyCard(
    user_id: String,
    titulo: String,
    ciudad: String,
    images: List<String>,
    codigo_postal: Int,
    direccion: String,
    estado: String,
    numero_baños: Int,
    distancia: Int,
    numero_habitaciones: Int,
    planta: String,
    precio: String,
    tamaño: Int,
    tipo_anuncio: String,
    tipo_propiedad: String,
    descripcion: String,
    additionalInfo: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { },
        // elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            // Slider de imágenes
            if (images.isNotEmpty()) {
                ImagePager(images)
            } else {
                Text(
                    text = "No hay imágenes disponibles",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blanco)
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        val halfStrokeWidth = strokeWidth / 2

                        // Borde izquierdo
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(halfStrokeWidth, 0f),
                            end = Offset(halfStrokeWidth, size.height),
                            strokeWidth = strokeWidth
                        )

                        // Borde derecho
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(size.width - halfStrokeWidth, 0f),
                            end = Offset(size.width - halfStrokeWidth, size.height),
                            strokeWidth = strokeWidth
                        )

                        // Borde inferior
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, size.height - halfStrokeWidth),
                            end = Offset(size.width, size.height - halfStrokeWidth),
                            strokeWidth = strokeWidth
                        )
                    }
            ) {  // Row con Iconos
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    Icon(Icons.Default.Share, contentDescription = "Share")
                    Icon(Icons.Default.Info, contentDescription = "Info")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Row con título y subtítulo
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()

                    ) {
                        Text(
                            text = "$titulo,",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Negro,
                            maxLines = Int.MAX_VALUE,
                            modifier = Modifier
                                .weight(2f)
                        )
                        Text(
                            text = "a $distancia km",
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            color = Negro,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
                Text(
                    text = ciudad,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Negro,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Texto del precio
                Text(
                    text = "$precio€",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    color = Negro,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Row con 3 textos
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "$numero_habitaciones hab.",
                        fontSize = 18.sp,

                        )
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "$tamaño m²",
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = planta,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()

                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Texto de descripción
                Text(
                    text = descripcion,
                    fontSize = 18.sp,
                    color = Negro,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (additionalInfo.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Amarillo.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = additionalInfo,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                            color = NegroClaro,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blanco)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp
                        )
                    ) // No bordes superiores
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        val halfStrokeWidth = strokeWidth / 2

                        // Borde izquierdo
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(halfStrokeWidth, 0f),
                            end = Offset(halfStrokeWidth, size.height),
                            strokeWidth = strokeWidth
                        )

                        // Borde derecho
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(size.width - halfStrokeWidth, 0f),
                            end = Offset(size.width - halfStrokeWidth, size.height),
                            strokeWidth = strokeWidth
                        )

                        // Borde inferior
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, size.height - halfStrokeWidth),
                            end = Offset(size.width, size.height - halfStrokeWidth),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp), // Espaciado
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.CenterHorizontally
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado entre el ícono y el texto
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.message_outlined_icon),
                        contentDescription = "Message Icon",
                        tint = Violeta,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Contactar",
                        color = Violeta, fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado entre el ícono y el texto
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone_icon),
                        contentDescription = "Phone Icon",
                        tint = Violeta,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Llamar",
                        color = Violeta, fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Empujar los íconos de la derecha
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaciado entre íconos
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trashcan_icon),
                        contentDescription = "Trash can Icon",
                        tint = Violeta,
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Trash can icon",
                        tint = Violeta,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
    }
}