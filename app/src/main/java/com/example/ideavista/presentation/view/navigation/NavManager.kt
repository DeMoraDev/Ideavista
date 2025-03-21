package com.example.ideavista.presentation.view.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ideavista.presentation.view.views.DetailScreen
import com.example.ideavista.presentation.view.views.DrawingMapScreen
import com.example.ideavista.presentation.view.views.FilterScreen
import com.example.ideavista.presentation.view.views.HomeScreen
import com.example.ideavista.presentation.view.views.LanguageScreen
import com.example.ideavista.presentation.view.views.MapsOptionScreen
import com.example.ideavista.presentation.view.views.OnboardingScreen
import com.example.ideavista.presentation.view.views.PropertyListScreen
import com.example.ideavista.presentation.view.views.SplashScreen
import com.example.ideavista.presentation.view.views.ThemeScreen
import com.example.ideavista.presentation.viewmodel.AuthViewModel
import com.example.ideavista.presentation.viewmodel.FilterViewModel
import com.example.ideavista.presentation.viewmodel.HomeScreenViewModel
import com.example.ideavista.presentation.viewmodel.LoginScreenViewModel
import com.example.ideavista.presentation.viewmodel.MapsViewModel
import com.example.ideavista.presentation.viewmodel.OnboardingViewModel
import com.example.ideavista.presentation.viewmodel.SplashScreenViewModel
import com.example.ideavista.presentation.viewmodel.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavManager(navHostController: NavHostController) {

    val authViewModel: AuthViewModel = koinViewModel()

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
            val loginScreenViewModel: LoginScreenViewModel = koinViewModel()
            LoginScreen(navHostController = navHostController, viewModel = loginScreenViewModel)
        }

        // Home Screen
        composable(NavigationRoutes.main) {
            val homeScreenViewModel: HomeScreenViewModel = koinViewModel()
            HomeScreen(
                navHostController = navHostController,
                viewModel = homeScreenViewModel
            )
        }

        composable(NavigationRoutes.Property) {
            val homeScreenViewModel: HomeScreenViewModel = koinViewModel()
            PropertyListScreen(
                navHostController = navHostController,
                homeScreenViewModel = homeScreenViewModel
            )
        }
        composable("propertyDetail/{propertyId}") { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getString("propertyId")
            propertyId?.let {
                DetailScreen(
                    propertyId = it,
                    navHostController = navHostController
                )
            }
        }
        composable(NavigationRoutes.FilterScreen) {
            val filterViewModel: FilterViewModel = koinViewModel()
            FilterScreen(
                navHostController = navHostController,
                filterViewModel = filterViewModel
            )
        }

        //Maps
        composable(NavigationRoutes.MapOptions) {
            MapsOptionScreen(
                navHostController = navHostController,
            )
        }
        composable(NavigationRoutes.Drawing) {
            val drawingViewModel: MapsViewModel = koinViewModel()
            DrawingMapScreen(
                navHostController = navHostController,
                viewModel = drawingViewModel
            )
        }

        composable(NavigationRoutes.Language) {
            val onboardingViewModel: OnboardingViewModel = koinViewModel()
            LanguageScreen(
                navHostController = navHostController,
                viewModel = onboardingViewModel
            )
        }

        composable(NavigationRoutes.Theme) {
            val themeViewModel: ThemeViewModel = koinViewModel()
            ThemeScreen(
                navHostController = navHostController,
                themeViewModel = themeViewModel,
            )
        }
    }
}