import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Gris
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun LanguageSelectionStep(
    selectedLanguage: String?,
    onLanguageSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Elige el idioma de la aplicación",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 20.dp)
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
                            onLanguageSelected(language)
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

// Lista de idiomas
val languageList = listOf(
    "Español", "Italiano", "Portugués", "English",
    "Catalá", "Dansk", "Deutsch", "Français",
    "Nederlands", "Norsk", "Polski", "Românâ",
    "Suomi", "Svenska", "Eλληνικά", "Русский",
    "українська", "Japones"
)
