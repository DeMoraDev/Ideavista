package com.example.ideavista.presentation.view.composable.generalComposables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun CustomRadioButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    Column {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Violeta,
                        unselectedColor = Color.Gray
                    )
                )
                Text(text = option)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomRadioButtonGroup() {
    var selectedOption by remember { mutableStateOf("Opción 1") }

    CustomRadioButton(
        options = listOf("Opción 1", "Opción 2", "Opción 3"), // Lista personalizada
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it },
    )
}