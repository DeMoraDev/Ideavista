package com.example.ideavista.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ideavista.presentation.view.views.LoginScreen
import com.example.ideavista.presentation.view.views.MainScreen
import com.example.ideavista.presentation.view.views.OnboardingScreen
import com.example.ideavista.presentation.view.views.SplashScreen
import com.example.ideavista.presentation.viewmodel.OnboardingViewModel
import com.example.ideavista.presentation.viewmodel.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavigationRoutes.Splash) {
        // Splash Screen
        composable(NavigationRoutes.Splash) {
            val splashScreenViewModel: SplashScreenViewModel = koinViewModel()
            SplashScreen(navHostController = navHostController, viewModel = splashScreenViewModel)
        }


        // Onboarding Screen
        composable(NavigationRoutes.Onboarding) {
            val onboardingViewModel: OnboardingViewModel = koinViewModel()
            OnboardingScreen(navHostController = navHostController, viewModel = onboardingViewModel)
        }

        // Login Screen
        composable(NavigationRoutes.login) {
            LoginScreen(navHostController = navHostController)
        }


        composable(NavigationRoutes.main) {
            MainScreen(navHostController = navHostController)
        }
    }
}
