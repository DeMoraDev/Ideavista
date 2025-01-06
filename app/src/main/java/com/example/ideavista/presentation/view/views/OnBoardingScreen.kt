package com.example.ideavista.presentation.view.views

import LanguageSelectionStep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.R
import com.example.ideavista.presentation.view.composable.CountrySelectionStep
import com.example.ideavista.presentation.view.composable.PermissionsRequestStep
import com.example.ideavista.presentation.view.navigation.NavigationRoutes
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreen(
    navHostController: NavHostController,
    viewModel: OnboardingViewModel
) {

    val selectedLanguage = viewModel.selectedLanguage.collectAsState()
    val selectedCountry = viewModel.selectedCountry.collectAsState()

    // Paginas del onboarding
    var onboardingStep by remember { mutableStateOf(1) }

    //Fuente pixelada
    val basis33Font = FontFamily(
        Font(R.font.basis33)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Amarillo)
    ) {
        // Box para el título "Ideavista"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f), // Ocupa 1/4 de la pantalla
            contentAlignment = Alignment.Center // Centrar el contenido dentro de este Box
        ) {
            Text(
                text = "ideavista",
                fontFamily = basis33Font,
                fontSize = 60.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Contenedor principal debajo del título con esquinas redondeadas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f) // Ocupa el 75% restante de la pantalla
                .align(Alignment.BottomStart) // Alinear en la parte inferior
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                ) // Esquinas redondeadas en la parte superior
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Contenido dinámico
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopCenter
                ) {
                    when (onboardingStep) {
                        1 -> LanguageSelectionStep(
                            selectedLanguage = selectedLanguage.value,
                            onLanguageSelected = { viewModel.selectLanguage(it) }
                        )

                        2 -> CountrySelectionStep(
                            selectedCountry = selectedCountry.value,
                            onCountrySelected = { viewModel.selectCountry(it) }
                        )

                        3 -> PermissionsRequestStep()
                    }
                }
                // Botón Continuar
                Button(
                    onClick = {
                        if (onboardingStep < 3) {
                            onboardingStep++  // Cambiar al siguiente paso
                        } else {
                            // Navegar al Login Screen después del Onboarding
                            navHostController.navigate(NavigationRoutes.login) {
                                popUpTo(NavigationRoutes.Onboarding) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), // Separación superior al botón
                    colors = ButtonDefaults.buttonColors(Violeta),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Continuar",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
