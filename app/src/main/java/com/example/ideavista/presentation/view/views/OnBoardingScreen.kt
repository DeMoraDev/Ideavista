package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.presentation.view.navigation.NavigationRoutes
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun OnboardingScreen(navHostController: NavHostController) {
    var onboardingStep by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Amarillo)
    ) {
        // Nombre de la aplicación en la parte superior
        Text(
            text = "Ideavista",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 48.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        // Contenido dinámico en la Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            when (onboardingStep) {
                1 -> LanguageSelection()  // Selección de idioma
                2 -> CountrySelection()   // Selección de país
                3 -> PermissionsRequest() // Solicitar permisos
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
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(Violeta)
        ) {
            Text(text = "Continuar", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}


@Composable
fun LanguageSelection() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Elige el idioma de la aplicación",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            items(languageList) { language ->
                Text(
                    text = language,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun CountrySelection() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Elige el país donde buscas o donde se encuentra tu inmueble",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = "España y Andorra", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Italia", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Portugal", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun PermissionsRequest() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gracias por descargarte nuestra aplicación",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "A continuación necesitamos pedirte permiso para poder personalizar tu experiencia según el uso que hagas de nuestra app y de la web. Te agradecemos si lo aceptas.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

// Lista de idiomas
val languageList = listOf(
    "Español",
    "Italiano",
    "Portugués",
    "English",
    "Catalá",
    "Dansk",
    "Deutsch",
    "Français",
    "Nederlands",
    "Norsk",
    "Polski",
    "Românâ",
    "Suomi",
    "Svenska",
    "Eλληνικά",
    "Русский",
    "українська",
    "Japones"
)
