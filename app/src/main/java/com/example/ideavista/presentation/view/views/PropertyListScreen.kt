package com.example.ideavista.presentation.view.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.presentation.view.composable.propertyComposables.FiltroBar
import com.example.ideavista.presentation.view.composable.propertyComposables.PropertyCard
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.example.ideavista.presentation.view.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PropertyListScreen(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel = koinViewModel()
) {

    val properties by homeScreenViewModel.properties.collectAsState()
    Log.d("PropertyListScreen", "Properties: $properties")
    LaunchedEffect(Unit) {
        homeScreenViewModel.fetchProperties()
    }
    Log.d("PropertyListScreen", "Properties: $properties")

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
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.LightGray.copy(alpha = 0.2f))
        ) {
            Log.d("PropertyListScreen", "Data: $properties")
            // FiltroBar como header fijo
            stickyHeader {
                FiltroBar()
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Viendo ${properties.size} viviendas de ${properties.size}",
                        fontSize = 18.sp,
                        color = NegroClaro
                    )
                }
            }

            items(properties) { property ->
                PropertyCard(
                    user_id = property.user_id ?: "Usuario desconocido", // Manejo de nulo
                    titulo = property.titulo ?: "Título no disponible",
                    ciudad = property.ciudad ?: "Ciudad no especificada",
                    codigo_postal = property.codigo_postal ?: 0, // Valor por defecto para números
                    direccion = property.direccion ?: "Dirección no disponible",
                    estado = property.estado ?: "Estado no especificado",
                    numero_baños = property.numero_baños ?: 0,
                    distancia = property.distancia ?: 0,
                    numero_habitaciones = property.numero_habitaciones ?: 0,
                    planta = property.planta ?: "Planta no especificada",
                    precio = property.precio?.toString() ?: "Precio no disponible",
                    tamaño = property.tamaño ?: 0,
                    tipo_anuncio = property.tipo_anuncio ?: "Tipo de anuncio no especificado",
                    tipo_propiedad = property.tipo_propiedad ?: "Tipo de propiedad no especificado",
                    descripcion = property.descripcion ?: "Descripción no disponible",
                    additionalInfo = property.additionalInfo ?: "Sin información adicional",
                    images = property.images
                )
            }
        }
    }
}
