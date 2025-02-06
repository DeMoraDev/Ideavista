package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.ideavista.R
import com.example.ideavista.data.local.SearchPreferences
import com.example.ideavista.presentation.view.composable.propertyComposables.LoadingBar
import com.example.ideavista.presentation.view.theme.NegroClaro


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PropertyListScreen(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel = koinViewModel()
) {

    val propertiesPreview by homeScreenViewModel.properties_Preview.collectAsState()

    val modoPropiedad = SearchPreferences.getModoPropiedad()
    val dropdownDbValue = SearchPreferences.getDropdownDbValue()


    //Estados de carga y emptyCall
    val isLoading by homeScreenViewModel.isLoading.collectAsState()
    val isEmpty by homeScreenViewModel.isEmpty.collectAsState()



    LaunchedEffect(Unit) {
        homeScreenViewModel.fetchPropertiesWithFilters()
    }

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
                                text = "${propertiesPreview.size} $dropdownDbValue, $modoPropiedad",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Negro,
                                maxLines = 1,
                            )
                            Text(
                                text = "Valencia, Valencia",
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
                    IconButton(onClick = { navHostController.navigate("main") }) {
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
                .background(color = Color.LightGray.copy(alpha = 0.2f)),
        ) {

            // FiltroBar como header fijo
            stickyHeader {
                FiltroBar(
                    onFilterClick = { navHostController.navigate("filter") },
                    onOrderbyClick = {},
                    onMapsClick = {}
                )
            }
            // Mostrar barra de carga cuando isLoading es verdadero
            if (isLoading) {
                item {
                    LoadingBar()
                }
            }

            // Mostrar mensaje de "No hay resultados" si la lista está vacía y no está cargando
            if (!isLoading && propertiesPreview.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFFFEEDB)) // Color de fondo del box
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.empty_property_list),
                                    contentDescription = "No se han encontrado propiedades",
                                    contentScale = ContentScale.Crop, // Ajusta la imagen sin dejar espacios
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)) // Asegura que respete el clippeo del box
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = "Ahora no hay anuncios con tus criterios",
                                            fontSize = 18.sp,
                                            color = Negro,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Si tienes flexibilidad, puedes ver habitaciones con tus filtros alrededor " +
                                                    "de tu zona de búsqueda.",
                                            fontSize = 16.sp,
                                            color = Negro,
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Ir al mapa",
                                            fontSize = 18.sp,
                                            color = Violeta,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Start,
                                            modifier = Modifier.clickable(onClick = {})
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Mostrar información sobre la cantidad de resultados cuando la lista no está vacía
            if (propertiesPreview.isNotEmpty() && !isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(12.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = "Viendo ${propertiesPreview.size} viviendas de ${propertiesPreview.size}",
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }

            // Mostrar los items de la lista de propiedades
            items(propertiesPreview) { property ->
                PropertyCard(
                    user_id = property.user_id ?: "Usuario desconocido",
                    titulo = property.titulo ?: "Título no disponible",
                    ciudad = property.ciudad ?: "Ciudad no especificada",
                    direccion = property.direccion ?: "Dirección no disponible",
                    estado = property.estado ?: "Estado no especificado",
                    distancia = property.distancia ?: 0,
                    numero_habitaciones = property.numero_habitaciones ?: 0,
                    planta = property.planta ?: "Planta no especificada",
                    precio = property.precio?.toString() ?: "Precio no disponible",
                    tamaño = property.tamaño ?: 0,
                    descripcion = property.descripcion ?: "Descripción no disponible",
                    additionalInfo = property.additionalInfo ?: "Sin información adicional",
                    images = property.images,
                    planos = property.planos,
                    garaje = property.garaje,
                    onPropertyClick = { navHostController.navigate("propertyDetail/${property.id}") }
                )
                Spacer(modifier = Modifier.height(30.dp)) // Espacio entre los items
            }
        }
    }
}