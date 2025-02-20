package com.example.ideavista.presentation.view.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.presentation.view.composable.maps.MapDrawer
import com.example.ideavista.presentation.view.composable.maps.MyMap
import com.example.ideavista.presentation.view.composable.maps.RequestLocationPermission
import com.example.ideavista.presentation.view.composable.propertyComposables.LoadingBar
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.MapsViewModel
import com.google.android.gms.maps.Projection
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawingMapScreen(
    navHostController: NavHostController,
    viewModel: MapsViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.checkPermissions()  // Verificar si ya tiene permisos
    }

    val isLoading by viewModel.isLoading.collectAsState()

    var projection by remember { mutableStateOf<Projection?>(null) }
    val firstPointLatLng = viewModel.getFirstPointLatLng(projection)


    val propertyCount by viewModel.propertyCount.collectAsState()
    val points by viewModel.points.collectAsState()
    val isDrawingMode by viewModel.isDrawingMode.collectAsState()
    val permissionGranted by viewModel.permissionGranted.collectAsState()
    val polygonComplete by viewModel.polygonComplete.collectAsState()

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
                                text = stringResource(id = R.string.drawmapScreen_scaffold_title),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Negro,
                                modifier = Modifier.weight(1f)
                            )
                            Button(
                                onClick = { },
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.padding(start = 8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                                elevation = ButtonDefaults.buttonElevation(0.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp)
                            ) {
                                Text(text = stringResource(id = R.string.drawmapScreen_scaffold_button), color = Blanco)
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
                if (!isDrawingMode) {
                    FloatingActionButton(
                        onClick = {
                            if (!polygonComplete) {
                                viewModel.setDrawingMode(true)
                            } else {
                                navHostController.navigate("property")
                            }
                        },
                        shape = RoundedCornerShape(4.dp),
                        containerColor = if (polygonComplete) Violeta else Blanco,
                        contentColor = Blanco,
                        modifier = Modifier
                            .border(
                                width = if (!polygonComplete) 2.dp else 0.dp,
                                color = Violeta,
                                shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        if (!polygonComplete) {
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
                                    text = stringResource(id = R.string.drawmapScreen_draw_button),
                                    color = Violeta,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        } else {
                            Text(
                                text = "${stringResource(id = R.string.drawmapScreen_see)} $propertyCount ${stringResource(id = R.string.drawmapScreen_properties)}",
                                color = Blanco,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    FloatingActionButton(
                        onClick = { },
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
                        onClick = { },
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
            if (isLoading) {
                LoadingBar()
                RequestLocationPermission(
                    onPermissionGranted = { viewModel.setPermissionGranted(true) },
                    onPermissionDenied = { viewModel.setPermissionGranted(false) }
                )
            }else{
                if (permissionGranted){
                    Box(modifier = Modifier.fillMaxSize()) {
                        MyMap(points = points, isDrawingMode = isDrawingMode)
                        if (isDrawingMode && !polygonComplete) {
                            MapDrawer(
                                state = viewModel.state,
                                polygonCompleted = viewModel.polygonCompleted,
                                onUpdatePosition = viewModel::updatePosition,

                                onDrawingEnd = { drawnPoints ->
                                    viewModel.setPoints(drawnPoints)
                                    viewModel.setPolygonComplete(true)
                                    viewModel.setDrawingMode(false)
                                    viewModel.fetchFilteredPropertyCount()
                                }
                            )
                        }
                        if (viewModel.polygonCompleted && viewModel.firstPoint != null) {
                            val firstPointOffset = viewModel.firstPoint!!
                            points.firstOrNull()?.let { firstPoint ->
                                IconButton(
                                    onClick = { viewModel.resetPolygon() },
                                    modifier = Modifier.offset {
                                        IntOffset(
                                            firstPointOffset.x.toInt(),
                                            firstPointOffset.y.toInt()
                                        )
                                    }

                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.cancel_icon),
                                        contentDescription = "Cancelar polígono",
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                        }
                    }
                }else{
                    Text(text = "No hay contenido")
                }
            }
        }
    }
}