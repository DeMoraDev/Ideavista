package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.ideavista.presentation.view.composable.onboardingComposables.CountryItem
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(
    navHostController: NavHostController,
    themeViewModel: ThemeViewModel
) {

    //val selectedTheme = themeViewModel.selectedTheme.collectAsState()
    val isDarkTheme = themeViewModel.isDarkTheme.collectAsState().value

    val optionList = listOf(
        "Modo claro" to R.drawable.portugal,
        "Modo oscuro" to R.drawable.spain,
        "Predeterminado" to R.drawable.italy
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "Apariencia", //TODO AÑADIR A STRINGS
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Negro
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Amarillo,
                    navigationIconContentColor = Violeta
                ),
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            items(optionList) { (option, imageResId) ->
                val isSelected = (option == "Modo oscuro") == isDarkTheme
                CountryItem(
                    countryName = option,
                    imageResId = imageResId,
                    isSelected = isSelected,
                    onClick = {
                        when (option) {
                            "Modo claro" -> themeViewModel.setTheme(false)
                            "Modo oscuro" -> themeViewModel.setTheme(true)
                            "Predeterminado" -> themeViewModel.setTheme(false)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
        }

    }
}