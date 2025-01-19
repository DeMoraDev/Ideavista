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
import com.example.ideavista.presentation.view.composable.onboardingComposables.CookieMessageContent
import com.example.ideavista.presentation.view.composable.onboardingComposables.CountrySelectionStep
import com.example.ideavista.presentation.view.composable.onboardingComposables.PermissionsRequestStep
import com.example.ideavista.presentation.view.navigation.NavigationRoutes
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.presentation.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navHostController: NavHostController,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()
    val selectedCountry by viewModel.selectedCountry.collectAsState()

    // Paginas del onboarding
    var onboardingStep by remember { mutableStateOf(1) }

    //Fuente pixelada
    val pixelFont = FontFamily(
        Font(R.font.ideal_font)
    )

    //Mensaje cookies
    var showCookiesMessage by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val MessageState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true) // Esto evita estados intermedios


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
                fontFamily = pixelFont,
                fontSize = 60.sp,
                fontWeight = FontWeight.Medium,
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
                            selectedLanguage = selectedLanguage,
                            onLanguageSelected = { viewModel.selectLanguage(it) }
                        )

                        2 -> CountrySelectionStep(
                            selectedCountry = selectedCountry,
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
                            showCookiesMessage = true
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

                if (showCookiesMessage) {
                    ModalBottomSheet(
                        onDismissRequest = { showCookiesMessage = false },
                        sheetState = MessageState
                    ) {
                        CookieMessageContent(
                            onAccept = {
                                showCookiesMessage = false
                                viewModel.setUserAsReturning()
                                // Navegar al Login Screen después del Onboarding
                                navHostController.navigate(NavigationRoutes.login) {
                                    popUpTo(NavigationRoutes.Onboarding) { inclusive = true }
                                }
                            },
                            onReject = {
                                showCookiesMessage = false
                                // Lógica adicional al rechazar TODO Añadir Loading Spinner encima del botón de continuar y al cargar un textoboton Omitir y al pulsar irá al Login también
                            },
                            onConfigure = {
                                // Lógica para configurar cookies
                            }
                        )
                    }

                    // Expande completamente el Bottom Sheet
                    LaunchedEffect(Unit) {
                        scope.launch {
                            MessageState.show()
                        }
                    }
                }
            }
        }
    }
}