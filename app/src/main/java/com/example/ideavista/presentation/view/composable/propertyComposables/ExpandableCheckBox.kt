package com.example.ideavista.presentation.view.composable.propertyComposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp

@Composable
fun ExpandableCheckbox(
    parentLabel: String,
    childLabels: List<String>,
    parentChecked: Boolean,
    childChecks: List<Boolean>,
    onParentCheckedChange: (Boolean) -> Unit,
    onChildCheckedChange: (Int, Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column() {
        // Row para el checkbox del padre
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }, // Toggle de expandir
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Separa checkbox e ícono
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = parentChecked,
                    onCheckedChange = {
                        onParentCheckedChange(it)  // Llamada a la función para cambiar el estado del padre
                    }
                )
                Text(parentLabel)
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Ocultar" else "Mostrar"
                )
            }
        }

        // Checkboxes hijos (visibles solo si `expanded` es true)
        if (expanded) {
            Column(modifier = Modifier.padding(start = 32.dp)) {
                childLabels.forEachIndexed { index, label ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = childChecks[index],
                            onCheckedChange = { checked ->
                                onChildCheckedChange(index, checked)  // Llamada a la función para cambiar el estado de los hijos
                            }
                        )
                        Text(label)
                    }
                }
            }
        }
    }
}