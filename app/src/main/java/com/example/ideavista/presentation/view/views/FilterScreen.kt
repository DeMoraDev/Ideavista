package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.data.local.SearchPreferences
import com.example.ideavista.presentation.view.composable.propertyComposables.InputDropdownMenu
import com.example.ideavista.presentation.view.composable.propertyComposables.LoadingBar
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.BottomBarColor
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.FilterViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    navHostController: NavHostController,
    filterViewModel: FilterViewModel = koinViewModel()
) {

    var villasChecked by remember { mutableStateOf(false) }

    val modoPropiedad = SearchPreferences.getModoPropiedad()
    val dropdownDbValue = SearchPreferences.getDropdownDbValue()


    //Barra de carga mientras carga la llamada de numeros de viviendas
    val propertyCount by filterViewModel.propertyCount.collectAsState()

    val isLoading by filterViewModel.isCountLoading.collectAsState()

    LaunchedEffect(Unit) {
        filterViewModel.fetchPropertyCount(modoPropiedad, dropdownDbValue)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Filtros",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.x_icon),
                            contentDescription = "",
                            tint = Violeta,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(18.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Amarillo, // Se usa el color dinámico
                    navigationIconContentColor = Violeta // Color del ícono de navegación
                ),
                actions = {},
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
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { navHostController.navigate("property") },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {

                        Text(
                            text = "Ver $propertyCount resultados",
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Color.LightGray.copy(alpha = 0.2f)),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (isLoading) {
                    item {
                        LoadingBar()
                    }
                } else {
                    item {
                        /*   BuyRentShareButtons(
                               buyOnClick = {
                                  // homeScreenViewModel.onBuyRentButtonClicked(BuyRentShareButtonOptions.COMPRAR)
                                   navHostController.navigate("property")
                               },
                               rentOnClick = {
                                  // homeScreenViewModel.onBuyRentButtonClicked( BuyRentShareButtonOptions.ALQUILAR )
                                   navHostController.navigate("property")
                               },
                               shareOnClick = {
                                  // homeScreenViewModel.onBuyRentButtonClicked( BuyRentShareButtonOptions.COMPARTIR )
                                   navHostController.navigate("property")
                               },
                               buttonState = buyRentShareState.selectedOption
                        )*/

                        Text(
                            text = "Precio",
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            InputDropdownMenu(
                                label = "Min",
                                options = listOf(
                                    "100",
                                    "150",
                                    "200",
                                    "250",
                                    "300",
                                    "350",
                                    "400",
                                    "450",
                                    "500",
                                    "550"
                                ),
                                onValueChange = {},
                                modifier = Modifier.weight(1f)
                            )
                            InputDropdownMenu(
                                label = "Max",
                                options = listOf(
                                    "100",
                                    "150",
                                    "200",
                                    "250",
                                    "300",
                                    "350",
                                    "400",
                                    "450",
                                    "500",
                                    "550"
                                ),
                                onValueChange = {},
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Tamaño",
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            InputDropdownMenu(
                                label = "Min",
                                options = listOf(
                                    "40m²",
                                    "50m²",
                                    "60m²",
                                    "70m²",
                                    "80m²",
                                    "90m²",
                                    "100m²",
                                    "110m²",
                                    "120m²",
                                    "130m²",
                                ),
                                onValueChange = {},
                                modifier = Modifier.weight(1f)
                            )
                            InputDropdownMenu(
                                label = "Max",
                                options = listOf(
                                    "40m²",
                                    "50m²",
                                    "60m²",
                                    "70m²",
                                    "80m²",
                                    "90m²",
                                    "100m²",
                                    "110m²",
                                    "120m²",
                                    "130m²",
                                ),
                                onValueChange = {},
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Tipo de vivienda")

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Otras denominaciones")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = villasChecked,
                                onCheckedChange = { villasChecked = it },

                                )
                            Text(text = "Villas")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Habitaciones")
                    }
                }
            }
        }
    }
}
