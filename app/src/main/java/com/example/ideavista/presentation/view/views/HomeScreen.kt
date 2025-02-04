package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.data.local.SearchPreferences
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.PropertyType
import com.example.ideavista.presentation.view.composable.home.ChatContent
import com.example.ideavista.presentation.view.composable.home.CustomTopBar
import com.example.ideavista.presentation.view.composable.home.FavoriteContent
import com.example.ideavista.presentation.view.composable.home.HomeContent
import com.example.ideavista.presentation.view.composable.home.MenuContent
import com.example.ideavista.presentation.view.composable.home.SearchContent
import com.example.ideavista.presentation.view.theme.BottomBarColor
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {

    var modoPropiedadselectedOption by remember { mutableStateOf(BuyRentShareButtonOptions.COMPRAR) }
    var selectedDropdownOption by remember { mutableStateOf(PropertyType.VIVIENDAS) }

    val state = viewModel.uiState
    val isSelected = state.value.step


    // Aquí se maneja la lógica del Scaffold
    Scaffold(
        topBar = {
            CustomTopBar(
                step = isSelected
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
                //TODO Hacer estos items de alguna forma más reutilizable
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Home) {
                            Icon(
                                painter = painterResource(id = R.drawable.home_filled_icon), // Reemplaza con el nombre de tu recurso
                                contentDescription = "Custom Icon",
                                tint = Violeta,
                                modifier = Modifier.size(22.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.home_outlined_icon), // Reemplaza con el nombre de tu recurso
                                contentDescription = "Custom Icon",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("Home") },
                    selected = state.value.step == HomeContentStep.Home,
                    onClick = { viewModel.updateStep(HomeContentStep.Home) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Search) {
                            Icon(
                                painter = painterResource(id = R.drawable.bell_filled_icon), // Reemplaza con el nombre de tu recurso
                                contentDescription = "Search filled Icon",
                                tint = Violeta,
                                modifier = Modifier.size(22.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.bell_outlined_icon), // Reemplaza con el nombre de tu recurso
                                contentDescription = "Search outlined Icon",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = { Text("Search") },
                    selected = state.value.step == HomeContentStep.Search,
                    onClick = { viewModel.updateStep(HomeContentStep.Search) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Favorites) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Favorites Filled",
                                tint = Violeta,
                            )
                        } else {
                            Icon(
                                Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorites Outlined",
                                tint = Color.Unspecified,
                            )
                        }
                    },
                    label = { Text("Favorites") },
                    selected = state.value.step == HomeContentStep.Favorites,
                    onClick = { viewModel.updateStep(HomeContentStep.Favorites) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Chat) {
                            Icon(
                                painter = painterResource(id = R.drawable.message_filled_icon),
                                contentDescription = "Message Icon",
                                tint = Violeta,
                                modifier = Modifier.size(22.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.message_outlined_icon),
                                contentDescription = "Message Icon",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    },
                    label = { Text("Chat") },
                    selected = state.value.step == HomeContentStep.Chat,
                    onClick = { viewModel.updateStep(HomeContentStep.Chat) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Menu) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Menu Filled",
                                tint = Violeta,
                                modifier = Modifier.size(28.dp)
                            )
                        } else {
                            Icon(
                                Icons.Outlined.Person,
                                contentDescription = "Menu Outlined",
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    },
                    label = { Text("Menu") },
                    selected = state.value.step == HomeContentStep.Menu,
                    onClick = { viewModel.updateStep(HomeContentStep.Menu) }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (state.value.step) {
                HomeContentStep.Home -> HomeContent(
                    onSearchClick = {
                        SearchPreferences.setSelectedOption(modoPropiedadselectedOption)
                        SearchPreferences.setSelectedDropdownOption(selectedDropdownOption)
                        navHostController.navigate("property")
                    },
                    selectedOption = modoPropiedadselectedOption,
                    onOptionSelected = { newOption ->
                        modoPropiedadselectedOption = newOption
                    },
                    selectedDropdownOption = selectedDropdownOption,
                    onDropdownOptionSelected = { newOption -> selectedDropdownOption = newOption }
                )

                HomeContentStep.Search -> SearchContent()
                HomeContentStep.Favorites -> FavoriteContent()
                HomeContentStep.Chat -> ChatContent()
                HomeContentStep.Menu -> MenuContent(
                    onClickLogInMenu = { navHostController.navigate("login") }
                )
            }
        }
    }
}
