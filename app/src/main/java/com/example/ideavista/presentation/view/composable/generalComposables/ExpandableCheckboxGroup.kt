package com.example.ideavista.presentation.view.composable.generalComposables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Componente reutilizable para un grupo de checkboxes con un padre y múltiples hijos
@Composable
fun ExpandableCheckboxGroup(
    title: String,
    children: List<Pair<String, MutableState<Boolean>>>
) {
    var parentChecked by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    // Función para actualizar el estado del padre basado en los hijos
    fun updateParentState() {
        parentChecked = children.any { it.second.value }
    }

    Column {
        // Checkbox principal con botón de desplegar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = parentChecked,
                onCheckedChange = { isChecked ->
                    parentChecked = isChecked
                    children.forEach { it.second.value = isChecked }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Blue,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
            )
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = "Desplegar"
                )
            }
        }

        // Mostrar los hijos con animación de expansión y contracción
        Column(
            modifier = Modifier
                .animateContentSize(animationSpec = tween(300))
                .fillMaxWidth()
        ) {
            if (isExpanded) {
                children.forEach { (label, state) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 32.dp, bottom = 4.dp)
                    ) {
                        Checkbox(
                            checked = state.value,
                            onCheckedChange = { isChecked ->
                                state.value = isChecked
                                updateParentState()
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Blue,
                                uncheckedColor = Color.Gray,
                                checkmarkColor = Color.White
                            )
                        )
                        Text(text = label, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ExpandableCheckboxGroupPreview() {
    val pisosChecked = remember { mutableStateOf(false) }
    val aticosChecked = remember { mutableStateOf(false) }
    val duplexChecked = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        ExpandableCheckboxGroup(
            title = "Pisos, Áticos, Dúplex",
            children = listOf(
                "Pisos" to pisosChecked,
                "Áticos" to aticosChecked,
                "Dúplex" to duplexChecked
            )
        )
    }
}