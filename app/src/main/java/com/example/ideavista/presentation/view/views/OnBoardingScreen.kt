package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.presentation.view.navigation.NavigationRoutes
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Gris
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.R

@Composable
fun OnboardingScreen(navHostController: NavHostController) {

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
                verticalArrangement = Arrangement.SpaceBetween // Espaciado uniforme entre los elementos
            ) {
                // Contenido dinámico
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // Deja que la lista ocupe el espacio disponible
                    contentAlignment = Alignment.TopCenter
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


@Composable
fun LanguageSelection() {

    val (selectedLanguage, setSelectedLanguage) = remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Elige el idioma de la aplicación",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            items(languageList) { language ->
                val isSelected = language == selectedLanguage
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isSelected) Gris.copy(alpha = 0.2f) else Color.Transparent)
                        .border(
                            width = 1.5.dp,
                            color = if (isSelected) Violeta else Gris.copy(0.4f),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(onClick = {
                            setSelectedLanguage(language)
                        })


                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween // Asegura que el texto y el icono estén a los extremos
                    ) {
                        Text(
                            text = language,
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp)
                        )
                        if (selectedLanguage == language) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Seleccionado",
                                tint = Violeta,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountrySelection() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Elige el idioma de la aplicación",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            items(countryList) { language ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(width = 1.dp, color = Gris, shape = RoundedCornerShape(4.dp))


                ) {
                    Text(
                        text = language,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PermissionsRequest() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Gracias por descargarte nuestra aplicación",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "A continuación necesitamos pedirte permiso para poder personalizar tu experiencia según el uso que hagas de nuestra app y de la web. Te agradecemos si lo aceptas.",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 17.sp,
            textAlign = TextAlign.Left
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

val countryList = listOf(
    "Portugal",
    "España y Andorra",
    "Italia"
)