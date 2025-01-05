package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    viewModel: SplashScreenViewModel = koinViewModel()
) {
    val isNewUser by viewModel.isNewUser.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkUserStatus()
        delay(2000)
        if (!isNewUser) {
            navHostController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navHostController.navigate("onboarding") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Amarillo),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )
    }
}
