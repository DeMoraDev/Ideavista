package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.setValue
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
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.PropertyType
import com.example.ideavista.presentation.view.composable.generalComposables.CustomRadioButton
import com.example.ideavista.presentation.view.composable.generalComposables.CustomSimpleCheckbox
import com.example.ideavista.presentation.view.composable.generalComposables.CustomSwitch
import com.example.ideavista.presentation.view.composable.generalComposables.ExpandableCheckboxGroup
import com.example.ideavista.presentation.view.composable.home.BuyRentShareButtons
import com.example.ideavista.presentation.view.composable.home.MainDropdown
import com.example.ideavista.presentation.view.composable.propertyComposables.InputDropdownMenu
import com.example.ideavista.presentation.view.composable.propertyComposables.LoadingBar
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
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
    var selectedRadioButtonOption by remember { mutableStateOf("Indiferente") } //RadioButton temporal, en futuro manejar en ViewModel

    val garajeChecked by filterViewModel.garajeChecked.collectAsState()

    //Filtros principales
    var modoPropiedadselectedOption by remember { mutableStateOf(BuyRentShareButtonOptions.COMPRAR) }
    var selectedDropdownOption by remember { mutableStateOf(PropertyType.VIVIENDAS) }


    //Lista de items sin logica todavia


    val pisosChecked = remember { mutableStateOf(false) }
    val aticosChecked = remember { mutableStateOf(false) }
    val duplexChecked = remember { mutableStateOf(false) }

    val independientesChecked = remember { mutableStateOf(false) }
    val pareadosChecked = remember { mutableStateOf(false) }
    val adosadosChecked = remember { mutableStateOf(false) }
    val casasRusticasChecked = remember { mutableStateOf(false) }

    var banos1 by remember { mutableStateOf(false) }
    var banos2 by remember { mutableStateOf(false) }
    var banosMas by remember { mutableStateOf(false) }

    var habitacionesEstudio by remember { mutableStateOf(false) }
    var habitaciones1 by remember { mutableStateOf(false) }
    var habitaciones2 by remember { mutableStateOf(false) }
    var habitaciones3 by remember { mutableStateOf(false) }
    var habitacionesMas by remember { mutableStateOf(false) }

    var obraNueva by remember { mutableStateOf(false) }
    var buenEstado by remember { mutableStateOf(false) }
    var aReformar by remember { mutableStateOf(false) }

    var aireAcondicionadoChecked by remember { mutableStateOf(false) }
    var armariosEmpotrados by remember { mutableStateOf(false) }
    var ascensor by remember { mutableStateOf(false) }
    var balcon by remember { mutableStateOf(false) }
    var terraza by remember { mutableStateOf(false) }
    var exterior by remember { mutableStateOf(false) }
    var jardin by remember { mutableStateOf(false) }
    var piscina by remember { mutableStateOf(false) }
    var trastero by remember { mutableStateOf(false) }
    var viviendaAccesible by remember { mutableStateOf(false) }

    var ultimaPlanta by remember { mutableStateOf(false) }
    var plantasIntermedias by remember { mutableStateOf(false) }
    var bajos by remember { mutableStateOf(false) }

    var conPlano by remember { mutableStateOf(false) }
    var conVisitaVirtual by remember { mutableStateOf(false) }

    var deBancos by remember { mutableStateOf(false) }

    var isSwitchChecked by remember { mutableStateOf(false) }


    val modoPropiedad = SearchPreferences.getModoPropiedad()
    val dropdownDbValue = SearchPreferences.getDropdownDbValue()


    //Barra de carga mientras carga la llamada de numeros de viviendas
    val propertyCount by filterViewModel.propertyCount.collectAsState()

    val isLoading by filterViewModel.isCountLoading.collectAsState()

    LaunchedEffect(modoPropiedadselectedOption, selectedDropdownOption) {
        filterViewModel.fetchFilteredPropertyCount()
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
                    IconButton(onClick = {
                        if (filterViewModel.searchPerformed.value) {
                            // Si se realizó la búsqueda, mantenemos los filtros
                            navHostController.popBackStack()
                        } else {
                            // Si no se ha realizado la búsqueda, reseteamos los filtros
                            filterViewModel.resetFilters()
                            navHostController.popBackStack()
                        }
                    }) {
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
                        onClick = {
                            filterViewModel.setSearchPerformed()
                            navHostController.navigate("property")
                        },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        enabled = propertyCount != 0,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (propertyCount != 0) Violeta else Color.LightGray,
                            contentColor = if (propertyCount != 0) Color.White else Color.Gray
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {

                        Text(
                            text = if (propertyCount != 0) {
                                "Ver $propertyCount resultados"
                            } else {
                                "No hay $dropdownDbValue"
                            },
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
                    .background(Blanco),
                contentPadding = PaddingValues(16.dp, vertical = 28.dp)
            ) {
                if (isLoading) {
                    item {
                        LoadingBar()
                    }
                } else {
                    item {

                        //Columna filtros principales
                        Column {
                            BuyRentShareButtons(
                                selectedOption = modoPropiedadselectedOption,
                                onOptionSelected = { option ->
                                    // 2. Guarda en SearchPreferences
                                    SearchPreferences.setSelectedOption(option)
                                    modoPropiedadselectedOption = option
                                }
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            // Dropdown y botones
                            MainDropdown(
                                selectedOption = selectedDropdownOption,
                                onOptionSelected = { option ->
                                    // 2. Guarda en SearchPreferences
                                    SearchPreferences.setSelectedDropdownOption(option)
                                    selectedDropdownOption = option
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
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
                        Spacer(modifier = Modifier.height(18.dp))
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
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = "Tipo de vivienda",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        ExpandableCheckboxGroup(
                            title = "Pisos, Áticos, Dúplex",
                            children = listOf(
                                "Pisos" to pisosChecked,
                                "Áticos" to aticosChecked,
                                "Dúplex" to duplexChecked
                            )
                        )
                        ExpandableCheckboxGroup(
                            title = "Casas y Chalets",
                            children = listOf(
                                "Independientes" to independientesChecked,
                                "Pareados" to pareadosChecked,
                                "Adosados" to adosadosChecked,
                                "Casas rústicas" to casasRusticasChecked
                            )
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Column {
                            Text(
                                text = "Habitaciones",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CustomSimpleCheckbox(
                                checked = habitacionesEstudio,
                                onCheckedChange = {
                                    habitacionesEstudio = it
                                    // Si se marca "0 habitaciones", marcar también todas las opciones mayores
                                    if (it) {
                                        habitaciones1 = true
                                        habitaciones2 = true
                                        habitaciones3 = true
                                        habitacionesMas = true
                                    }
                                },
                                label = "0 habitaciones (Estudio)"
                            )
                            CustomSimpleCheckbox(
                                checked = habitaciones1,
                                onCheckedChange = {
                                    habitaciones1 = it
                                    // Si se marca "1 habitación", marcar también las opciones mayores
                                    if (it) {
                                        habitaciones2 = true
                                        habitaciones3 = true
                                        habitacionesMas = true
                                    }
                                },
                                label = "1"
                            )
                            CustomSimpleCheckbox(
                                checked = habitaciones2,
                                onCheckedChange = {
                                    habitaciones2 = it
                                    // Si se marca "2 habitaciones", marcar también las opciones mayores
                                    if (it) {
                                        habitaciones3 = true
                                        habitacionesMas = true
                                    }
                                },
                                label = "2"
                            )
                            CustomSimpleCheckbox(
                                checked = habitaciones3,
                                onCheckedChange = {
                                    habitaciones3 = it
                                    // Si se marca "3 habitaciones", marcar también "4 o más"
                                    if (it) {
                                        habitacionesMas = true
                                    }
                                },
                                label = "3"
                            )
                            CustomSimpleCheckbox(
                                checked = habitacionesMas,
                                onCheckedChange = { habitacionesMas = it },
                                label = "4 habitaciones o más"
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Column {
                            Text(
                                text = "Baños",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CustomSimpleCheckbox(
                                checked = banos1,
                                onCheckedChange = {
                                    banos1 = it
                                    // Si se marca "1 baño", marcar también las opciones mayores
                                    if (it) {
                                        banos2 = true
                                        banosMas = true
                                    }
                                },
                                label = "1"
                            )
                            CustomSimpleCheckbox(
                                checked = banos2,
                                onCheckedChange = {
                                    banos2 = it
                                    // Si se marca "2 baños", marcar también la opción de "3 o más"
                                    if (it) {
                                        banosMas = true
                                    }
                                },
                                label = "2"
                            )
                            CustomSimpleCheckbox(
                                checked = banosMas,
                                onCheckedChange = { banosMas = it },
                                label = "3 baños o más"
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        //Columna Estado
                        Column {
                            Text(
                                text = "Estado",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CustomSimpleCheckbox(
                                checked = obraNueva,
                                onCheckedChange = { obraNueva = it },
                                label = "Obra nueva"
                            )
                            CustomSimpleCheckbox(
                                checked = buenEstado,
                                onCheckedChange = { buenEstado = it },
                                label = "Buen estado"
                            )
                            CustomSimpleCheckbox(
                                checked = aReformar,
                                onCheckedChange = { aReformar = it },
                                label = "A reformar"
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        //Columna de Características
                        Column {
                            Text(
                                text = "Características",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CustomSimpleCheckbox(
                                checked = aireAcondicionadoChecked,
                                onCheckedChange = {
                                    aireAcondicionadoChecked = it
                                },
                                label = "Aire acondicionado"
                            )
                            CustomSimpleCheckbox(
                                checked = armariosEmpotrados,
                                onCheckedChange = { armariosEmpotrados = it },
                                label = "Armarios empotrados"
                            )
                            CustomSimpleCheckbox(
                                checked = ascensor,
                                onCheckedChange = { ascensor = it },
                                label = "Ascensor"
                            )
                            CustomSimpleCheckbox(
                                checked = balcon,
                                onCheckedChange = { balcon = it },
                                label = "Balcón"
                            )
                            CustomSimpleCheckbox(
                                checked = terraza,
                                onCheckedChange = { terraza = it },
                                label = "Terraza"
                            )
                            CustomSimpleCheckbox(
                                checked = exterior,
                                onCheckedChange = { exterior = it },
                                label = "Exterior"
                            )
                            CustomSimpleCheckbox(
                                checked = garajeChecked,
                                onCheckedChange = { isChecked ->
                                    filterViewModel.updateGarajeFilter(isChecked)
                                },
                                label = "Garaje"
                            )
                            CustomSimpleCheckbox(
                                checked = jardin,
                                onCheckedChange = { jardin = it },
                                label = "Jardín"
                            )
                            CustomSimpleCheckbox(
                                checked = piscina,
                                onCheckedChange = { piscina = it },
                                label = "Piscina"
                            )
                            CustomSimpleCheckbox(
                                checked = trastero,
                                onCheckedChange = { trastero = it },
                                label = "Trastero"
                            )
                            CustomSimpleCheckbox(
                                checked = viviendaAccesible,
                                onCheckedChange = { viviendaAccesible = it },
                                label = "Vivienda accesible"
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        //Columna de Características
                        Column {
                            Text(
                                text = "Planta",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CustomSimpleCheckbox(
                                checked = ultimaPlanta,
                                onCheckedChange = { ultimaPlanta = it },
                                label = "Última planta"
                            )
                            CustomSimpleCheckbox(
                                checked = plantasIntermedias,
                                onCheckedChange = { plantasIntermedias = it },
                                label = "Plantas intermedias"
                            )
                            CustomSimpleCheckbox(
                                checked = bajos,
                                onCheckedChange = { bajos = it },
                                label = "Bajos"
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Column {
                            Text(
                                text = "Multimedia",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            CustomSimpleCheckbox(
                                checked = conPlano,
                                onCheckedChange = { conPlano = it },
                                label = "Con plano"
                            )
                            CustomSimpleCheckbox(
                                checked = conVisitaVirtual,
                                onCheckedChange = { conVisitaVirtual = it },
                                label = "Con visita virtual"
                            )

                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = "Tipo de anuncio",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        CustomSimpleCheckbox(
                            checked = deBancos,
                            onCheckedChange = { deBancos = it },
                            label = "De bancos"
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = "Fecha de publicación",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        CustomRadioButton(
                            options = listOf(
                                "Indiferente",
                                "Últimas 48 horas",
                                "La última semana",
                                "El último mes"
                            ),
                            selectedOption = selectedRadioButtonOption,
                            onOptionSelected = { selectedRadioButtonOption = it }
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = "Descartados",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        CustomSwitch(
                            label = "Ver descartados",
                            isChecked = isSwitchChecked,
                            onCheckedChange = { isSwitchChecked = it }
                        )
                    }
                }
            }
        }
    }
}
