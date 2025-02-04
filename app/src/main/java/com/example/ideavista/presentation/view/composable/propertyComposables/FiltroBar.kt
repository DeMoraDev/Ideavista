package com.example.ideavista.presentation.view.composable.propertyComposables

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FormatLineSpacing
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.core.VerticalSeparator
import com.example.ideavista.presentation.view.theme.Negro

@Composable
fun FiltroBar(
    onFilterClick: () -> Unit,
    onOrderbyClick: () -> Unit,
    onMapsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Box 1
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { onFilterClick() }
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList, // Cambia el ícono según la necesidad
                    contentDescription = "Filtro",
                    tint = Negro
                )
                Spacer(modifier = Modifier.width(4.dp)) // Espaciado entre ícono y texto
                Text(
                    text = "Filtrar",
                    fontSize = 12.sp,
                    color = Negro
                )
            }
        }
        VerticalSeparator(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.height(30.dp),
        )
        // Box 2
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { onOrderbyClick() }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FormatLineSpacing, // Cambia el ícono según la necesidad
                    contentDescription = "Opción 2",
                    tint = Negro
                )
                Spacer(modifier = Modifier.width(4.dp)) // Espaciado entre ícono y texto
                Text(
                    text = "Ordenar",
                    fontSize = 12.sp,
                    color = Negro
                )
            }
        }
        VerticalSeparator(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.height(30.dp),
        )
        // Box 3
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { onMapsClick() }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn, // Cambia el ícono según la necesidad
                    contentDescription = "Opción 3",
                    tint = Negro
                )
                Spacer(modifier = Modifier.width(4.dp)) // Espaciado entre ícono y texto
                Text(
                    text = "Mapa",
                    fontSize = 12.sp,
                    color = Negro
                )
            }
        }
    }
}