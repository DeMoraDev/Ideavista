package com.example.ideavista.presentation.view.composable.propertyComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Negro

@Composable
fun PropertyCard(
    imageUrl: String,
    title: String,
    subtitle: String,
    city: String,
    price: String,
    features: List<String>,
    description: String,
    additionalInfo: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Imagen arriba
            AsyncImage(
                model = imageUrl,
                contentDescription = "Property Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Row con Iconos
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
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Negro,
                        maxLines = Int.MAX_VALUE,
                        modifier = Modifier
                            .weight(2f)
                    )
                    Text(
                        text = subtitle,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
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
                text = city,
                fontWeight = FontWeight.Normal ,
                fontSize = 18.sp,
                color = Negro,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texto del precio
            Text(
                text = price,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                features.forEach { feature ->
                    Text(
                        text = feature,
                        fontSize = 12.sp,
                        color = Negro
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Texto de descripción
            Text(
                text = description,
                fontSize = 14.sp,
                color = Negro,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Texto de información adicional
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Amarillo.copy(alpha = 0.3f))
            ) {
                Text(
                    text = additionalInfo,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Negro,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PropertyItemCardPreview() {
    PropertyCard(
        imageUrl = "https://via.placeholder.com/400",
        title = "Dúplex en avenida de la Plana,",
        subtitle = "a 1 km",
        city = "Silla",
        price = "€269.000€",
        features = listOf("3 hab.", "166 m²", "Planta 3º exterior con ascensor"),
        description = "Exclusivo ático dúplex, un oasis que combina un diseño moderno y elegante",
        additionalInfo = "Reformado"
    )
}