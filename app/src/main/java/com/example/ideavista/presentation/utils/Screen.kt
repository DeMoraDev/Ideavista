package com.example.ideavista.presentation.utils

sealed class Screen(val route: String) {
    object Home: Screen("main")
    object Search: Screen("search")
    object Favorites: Screen("favorites")
    object Chat: Screen("chat")
    object Menu: Screen("menu")
}
