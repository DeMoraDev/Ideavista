package com.example.ideavista.presentation.view.composable.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExposedDropdownMenuDefaults
import com.example.ideavista.presentation.view.theme.Blanco
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuItemColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDropdown() {
    // Lista de opciones
    val dropdownOptions = listOf(
        "Obra nueva",
        "Viviendas",
        "Oficinas",
        "Locales o naves",
        "Traspasos",
        "Garajes",
        "Terrenos",
        "Trasteros",
        "Edificios",
    )

    // Estado para manejar la selección actual y si el menú está desplegado
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(dropdownOptions[0]) }

    // Dropdown Box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }, // Cambiar el estado expandido
        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
    ) {
        // TextField para mostrar la opción seleccionada
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {}, // El valor no se puede cambiar directamente
            readOnly = true, // Hacer que el campo sea de solo lectura
            trailingIcon = { // Icono de despliegue
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Filled.KeyboardArrowDown
                        } else {
                            Icons.Filled.KeyboardArrowDown
                        },
                        contentDescription = ""
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor() // Necesario para posicionar el menú
                .border(BorderStroke(1.dp, color = Color.Gray))
                .background(Blanco)
        )

        // Dropdown Menu
        ExposedDropdownMenu(
            modifier = Modifier.background(Blanco),
            expanded = expanded,
            onDismissRequest = { expanded = false } // Cerrar el menú al hacer clic afuera
        ) {
            dropdownOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option // Actualizar la opción seleccionada
                        expanded = false // Cerrar el menú
                    },
                    modifier = Modifier.background(Blanco)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainDropdownContent() {
    MainDropdown()
}