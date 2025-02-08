package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.presentation.view.composable.maps.GoogleMapScreen
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import com.example.ideavista.presentation.view.composable.maps.RequestLocationPermission


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawingMapScreen(
    navHostController: NavHostController
) {

    var permissionGranted by remember { mutableStateOf(false) }

    var isSatelliteView by remember { mutableStateOf(false) }
    var moveToUserLocation by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(40.7128, -74.0060),
            20f
        )
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Tu propia zona", //TODO CAMBIAR  a "Dibuja tu zona" cuando se este dibujando y en el estado isLoading -> Column{Text= Cargando, Text= "Tu zona dibujada"}
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Negro,
                                modifier = Modifier.weight(1f)
                            )
                            Button(
                                onClick = { },
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.padding(start = 8.dp),  // Añadiendo paddings sin el `clip`
                                colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                                elevation = ButtonDefaults.buttonElevation(0.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp)
                            ) {
                                Text(text = "Guardar búsqueda", color = Blanco)
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Amarillo,
                        navigationIconContentColor = Violeta
                    ),
                    modifier = Modifier.shadow(elevation = 4.dp)
                )
            }
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                // Botón grande centrado en la parte inferior
                FloatingActionButton(
                    onClick = { /* Acción de dibujar */ },
                    shape = RoundedCornerShape(4.dp),
                    containerColor = Blanco,
                    contentColor = Violeta,
                    modifier = Modifier.border(2.dp, Violeta, shape = RoundedCornerShape(4.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.draw_map_icon),
                            contentDescription = "Dibujar zona",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                        Text(
                            text = "Dibujar tu zona",
                            color = Violeta,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                // Botones pequeños alineados a la derecha
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    FloatingActionButton(
                        onClick = { isSatelliteView = !isSatelliteView },
                        shape = RoundedCornerShape(4.dp),
                        containerColor = Blanco,
                        contentColor = Violeta,
                        modifier = Modifier.border(2.dp, Violeta, shape = RoundedCornerShape(4.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.tipo_mapa_icon),
                            contentDescription = "Cambiar tipo de mapa",
                            tint = Violeta,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                    FloatingActionButton(
                        onClick = { moveToUserLocation = true },
                        shape = RoundedCornerShape(4.dp),
                        containerColor = Blanco,
                        contentColor = Violeta,
                        modifier = Modifier.border(2.dp, Violeta, shape = RoundedCornerShape(4.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location_icon),
                            contentDescription = "Ir a tu ubicación",
                            tint = Violeta,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Llamamos a la función RequestLocationPermission y pasamos el manejo de los permisos
            RequestLocationPermission(
                onPermissionGranted = {
                    permissionGranted = true // Si el permiso es concedido, podemos mostrar el mapa
                },
                onPermissionDenied = {
                    // Muestra un mensaje o acción si el permiso fue denegado
                    permissionGranted = false
                }
            )

            // Si los permisos fueron concedidos, mostramos el mapa
            if (permissionGranted) {
                GoogleMapScreen(
                    context = LocalContext.current,
                    isSatelliteView = isSatelliteView,
                    moveToUserLocation = moveToUserLocation
                )
            } else {
                // Puedes mostrar un mensaje o alguna interfaz indicando que el permiso es necesario
                Text(text = "Por favor, concede los permisos para acceder a la ubicación.")
            }
        }
    }
}

