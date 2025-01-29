package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.presentation.view.composable.detailComposables.InputWithSlider
import com.example.ideavista.presentation.view.composable.propertyComposables.ImagePager
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.BottomBarColor
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.PropertyViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    propertyId: String,
    propertyViewModel: PropertyViewModel = koinViewModel()
) {
    val propertyDetails = propertyViewModel.propertyDetails.collectAsState()

    var sliderValue by remember { mutableFloatStateOf(0f) }
    var value by remember { mutableStateOf(propertyViewModel.value) }

    fun onValueChange(newValue: Float) {
        value = newValue
        propertyViewModel.updateValue(newValue)  // Actualizamos el valor en el ViewModel
    }

    //TopBar dinámico

    val lazyListSState = rememberLazyListState()
    val scrollOffset = remember {
        derivedStateOf {
            lazyListSState.firstVisibleItemScrollOffset
        }
    }
    val firstVisibleIndex = remember {
        derivedStateOf {
            lazyListSState.firstVisibleItemIndex
        }
    }

    val topBarColor by remember {
        derivedStateOf {
            if (firstVisibleIndex.value > 0 || scrollOffset.value > 1840) {
                Blanco
            } else {
                Amarillo
            }
        }
    }

    LaunchedEffect(propertyId) {
        propertyViewModel.fetchPropertyDetails(propertyId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (firstVisibleIndex.value > 0 || scrollOffset.value > 1840) {
                        // Cambiar a un `Column` cuando el usuario hace scroll
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            val title = propertyDetails.value?.titulo ?: "Cargando..."
                            val price = propertyDetails.value?.precio ?: "Sin precio"
                            Text(
                                text = title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = "$price €",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal
                                )
                                if (propertyDetails.value?.garaje ?: false) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Tiene garaje",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }

                        }
                    } else {
                        // Mostrar un `Row` al principio
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val title = propertyDetails.value?.titulo ?: "Cargando..."
                            Text(
                                text = title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarColor, // Se usa el color dinámico
                    navigationIconContentColor = Violeta // Color del ícono de navegación
                ),
                actions = {
                    if (firstVisibleIndex.value > 0 || scrollOffset.value > 1840) {
                        IconButton(onClick = {
                            // Por ejemplo, podrías pasar el ID de la propiedad siguiente a través de los parámetros del NavController

                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.heart_outlined_icon),
                                contentDescription = "Atrás",
                                tint = Violeta,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        IconButton(onClick = {
                            // Acción adicional si la necesitas
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.trashcan_icon),
                                contentDescription = "Adelante",
                                tint = Violeta,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            // Por ejemplo, podrías pasar el ID de la propiedad siguiente a través de los parámetros del NavController

                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left_icon),
                                contentDescription = "Atrás",
                                tint = Violeta,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        IconButton(onClick = {
                            // Acción adicional si la necesitas
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_right_icon),
                                contentDescription = "Adelante",
                                tint = Violeta,
                                modifier = Modifier
                                    .size(19.dp)
                            )
                        }
                    }
                },
                modifier = Modifier.shadow(elevation = 4.dp)
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.shadow(
                    elevation = 10.dp
                ),
                containerColor = BottomBarColor,
                tonalElevation = 14.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.message_filled_icon),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Contactar",
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            )
                        }
                    }
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1f) // El botón ocupa la otra mitad del ancho disponible
                            .padding(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.phone_icon), // Cambié el ícono al de llamada
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Llamar",
                                color = Color.White,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListSState, //Conectar el estado de scroll aquí
                modifier = Modifier
                    .padding(innerPadding)
                    .background(color = Color.LightGray.copy(alpha = 0.2f))
            ) {
                if (propertyDetails.value != null) {
                    val property = propertyDetails.value
                    item {
                        Column(

                        ) {
                            if (property?.images?.isNotEmpty() == true) {
                                val images = property.images
                                val planos = property.planos
                                ImagePager(images, planos, showInmobiliaria = false)
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
                            ) {

                                //TODO Apartado Datos básicos y comentario
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(horizontal = 14.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Color.Transparent)
                                                .border(
                                                    1.dp,
                                                    color = Color.Black,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .clickable(onClick = {}),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.image_icon),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .size(38.dp)
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Color.Transparent)
                                                .border(
                                                    1.dp,
                                                    color = Color.Black,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .clickable(onClick = {}),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.blueprint_icon),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .background(Color.Transparent)
                                                    .size(22.dp)

                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Color.Transparent)
                                                .border(
                                                    1.dp,
                                                    color = Color.Black,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .clickable(onClick = {}),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "360",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Color.Transparent)
                                                .border(
                                                    1.dp,
                                                    color = Color.Black,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .clickable(onClick = {}),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.location_map_iconf),
                                                contentDescription = "Location",
                                                modifier = Modifier
                                                    .size(28.dp)
                                            )
                                        }


                                    }
                                    Text(
                                        text = "${property?.titulo},",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Negro,
                                        maxLines = Int.MAX_VALUE
                                    )
                                    Text(
                                        text = "${property?.direccion}, ${property?.ciudad}",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 18.sp,
                                        color = Negro
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "${property?.precio}€",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 28.sp,
                                        color = Negro
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Text(
                                            text = "${property?.numero_habitaciones} hab.",
                                            fontSize = 18.sp,
                                        )
                                        Spacer(modifier = Modifier.width(24.dp))
                                        Text(
                                            text = "${property?.tamaño} m²",
                                            fontSize = 18.sp,
                                        )
                                        Spacer(modifier = Modifier.width(24.dp))
                                        Text(
                                            text = property?.planta ?: "",
                                            fontSize = 18.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                    //TODO hacer composable reutilizable
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 20.dp),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.heart_outlined_icon),
                                                contentDescription = "Location",
                                                tint = Violeta,
                                                modifier = Modifier
                                                    .size(28.dp)
                                            )
                                            Text(
                                                text = "Guardar",
                                                fontWeight = FontWeight.Bold,
                                                color = Violeta
                                            )
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.trashcan_icon),
                                                contentDescription = "Location",
                                                tint = Violeta,
                                                modifier = Modifier
                                                    .size(24.dp)
                                            )
                                            Text(
                                                text = "Guardar",
                                                fontWeight = FontWeight.Bold,
                                                color = Violeta
                                            )
                                        }
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.share_icon),
                                                contentDescription = "Location",
                                                tint = Violeta,
                                                modifier = Modifier
                                                    .size(28.dp)
                                            )
                                            Text(
                                                text = "Guardar",
                                                fontWeight = FontWeight.Bold,
                                                color = Violeta
                                            )
                                        }
                                    }
                                    Text(
                                        text = "Comentario del anunciante",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Text(
                                            text = "Ver: ",
                                            fontSize = 16.sp
                                        )
                                        VerticalDivider(thickness = 2.dp, color = Negro)
                                        TextButton(
                                            onClick = {}
                                        ) {
                                            Text(
                                                text = "Traducciones",
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 18.sp, color = Violeta
                                            )
                                        }
                                    }
                                    Text(
                                        text = property?.comentario ?: "Sin comentario",
                                        fontSize = 18.sp,
                                        maxLines = 5,
                                        overflow = TextOverflow.Visible
                                    )
                                    TextButton(
                                        onClick = {},
                                        modifier = Modifier
                                    ) {
                                        Text(
                                            text = "Ir al comentario completo",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Violeta
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado mensaje hablar con anunciante
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(horizontal = 14.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start,
                                        modifier = Modifier.padding(vertical = 20.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.message_outlined_icon),
                                            contentDescription = "",
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "Si tienes alguna duda recuerda que puedes hablar con el anunciante por chat",
                                            fontSize = 18.sp,
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado Especificaciones
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Características básicas",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "· Casa independiente",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                    Text(
                                        text = "Equipamiento",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Certificado energético",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado Actualización de anuncio
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.info_icon),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Anuncio actualizado hace 30 días",
                                            fontSize = 18.sp,

                                            )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado multimedia ideavista
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Este anuncio tiene multimedia realizado por ideavista",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = "Un fotógrafo profesional ha visitado el inmueble y realizó " +
                                                "el multimedia. No se han aplicado filtros ni modificaciones.",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado visita inmueble
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.screen_icon),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Visita este inmueble sin moverte de casa",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = "Ya puedes solicitar una primera visita guiada virtual de " +
                                                "este inmueble. Un profesional te guiará con la información con " +
                                                "la información que necesitas.",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    TextButton(
                                        onClick = {}
                                    ) {
                                        Text(
                                            text = "Solicitar visita guiada virtual",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp,
                                            color = Violeta
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado decoración virtual
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(14.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Row {
                                        Icon(
                                            painter = painterResource(id = R.drawable.lamp_icon),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Decoración virtual",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Text(
                                        text = "Descubre cómo se ve esta casa decorada por profesionales.",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    TextButton(
                                        onClick = {}
                                    ) {
                                        Text(
                                            text = "Ver fotos con decoración virtual",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp,
                                            color = Violeta
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado Precio
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(18.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "Precio",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "${property?.precio} €",
                                        fontSize = 18.sp,
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "${property?.tamaño} €/m2", //TODO calcular precio m2
                                        fontSize = 18.sp,
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    TextButton(
                                        onClick = {}
                                    ) {
                                        Text(
                                            text = "Hacer una contraoferta",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp,
                                            color = Violeta
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                //TODO Apartado hipotecas
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(18.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    //Hipoteca Composable
                                    InputWithSlider(
                                        value = value,  // Usamos el valor reactivo aquí
                                        onValueChange = { newValue ->
                                            onValueChange(newValue)  // Actualizamos el valor en el Composable y en el ViewModel
                                        },
                                        range = 0f..2_000_000f,
                                        step = 10000f
                                    )
                                }
                                //TODO Apartado dirección y estadísticas
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(18.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {

                                }
                                //TODO Apartado anunciante
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Blanco)
                                        .padding(18.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {

                                }
                            }
                        }
                    }
                } else {
                    item {
                        Text(text = "Cargando detalles...", fontSize = 18.sp)
                    }
                }
            }

        }
    }
}

