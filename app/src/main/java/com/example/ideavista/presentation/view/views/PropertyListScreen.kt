package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.composable.propertyComposables.FiltroBar
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PropertyListScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            verticalArrangement = Arrangement.spacedBy((-8).dp)
                        ) {
                            Text(
                                text = "9 viviendas, comprar",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Negro,
                                maxLines = 1,
                            )
                            Text(
                                text = "Beniparrell, Valencia",
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = Violeta,
                                maxLines = 1,
                            )
                        }
                        Button(
                            onClick = { },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(6.dp),  // Añadiendo paddings sin el `clip`
                            colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                            elevation = ButtonDefaults.buttonElevation(0.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            Text(text = "Guardar búsqueda", color = Blanco)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Acción para retroceder */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Amarillo, // Color personalizado del fondo
                    navigationIconContentColor = Violeta // Color del ícono de navegación
                ),
                actions = {
                    // Puedes agregar más acciones aquí si lo deseas
                },
                modifier = Modifier.shadow(elevation = 4.dp)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            // FiltroBar como header fijo
            stickyHeader {
                FiltroBar()
            }

            // Resto del contenido aquí
            items(10) { index ->
                Text("Elemento $index")
            }
        }
    }
}
@Preview
@Composable
fun PropertyListScreenPreview() {
    PropertyListScreen()
}