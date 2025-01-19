package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ideavista.presentation.state.BuyRentShareButtonOptions
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.view.composable.home.ChatContent
import com.example.ideavista.presentation.view.composable.home.CustomTopBar
import com.example.ideavista.presentation.view.composable.home.FavoriteContent
import com.example.ideavista.presentation.view.composable.home.HomeContent
import com.example.ideavista.presentation.view.composable.home.MenuContent
import com.example.ideavista.presentation.view.composable.home.SearchContent
import com.example.ideavista.presentation.view.theme.BottomBarColor
import com.example.ideavista.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {

    val state = viewModel.uiState
    val isSelected = state.value.step

    val buyRentState = viewModel.buyRentState.collectAsState()

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
                            Icon(Icons.Default.Home, contentDescription = "Home Filled")
                        } else {
                            Icon(Icons.Outlined.Home, contentDescription = "Home Outlined")
                        }
                    },
                    label = { Text("Home") },
                    selected = state.value.step == HomeContentStep.Home,
                    onClick = { viewModel.updateStep(HomeContentStep.Home) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Search) {
                            Icon(Icons.Default.Search, contentDescription = "Search Filled")
                        } else {
                            Icon(Icons.Outlined.Search, contentDescription = "Search Outlined")
                        }
                    },
                    label = { Text("Search") },
                    selected = state.value.step == HomeContentStep.Search,
                    onClick = { viewModel.updateStep(HomeContentStep.Search) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Favorites) {
                            Icon(Icons.Default.Favorite, contentDescription = "Favorites Filled")
                        } else {
                            Icon(
                                Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorites Outlined"
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
                            Icon(Icons.Default.Chat, contentDescription = "Chat Filled")
                        } else {
                            Icon(Icons.Outlined.ChatBubble, contentDescription = "Chat Outlined")
                        }
                    },
                    label = { Text("Chat") },
                    selected = state.value.step == HomeContentStep.Chat,
                    onClick = { viewModel.updateStep(HomeContentStep.Chat) }
                )
                NavigationBarItem(
                    icon = {
                        if (isSelected == HomeContentStep.Menu) {
                            Icon(Icons.Default.Person, contentDescription = "Menu Filled")
                        } else {
                            Icon(Icons.Outlined.Person, contentDescription = "Menu Outlined")
                        }
                    },
                    label = { Text("Home") },
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
                    buyOnClick = { viewModel.onBuyRentButtonClicked(BuyRentShareButtonOptions.COMPRAR) },
                    rentOnClick = { viewModel.onBuyRentButtonClicked(BuyRentShareButtonOptions.ALQUILAR) },
                    shareOnClick = { viewModel.onBuyRentButtonClicked(BuyRentShareButtonOptions.COMPARTIR) },
                    buttonState = buyRentState.value.selectedOption
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
