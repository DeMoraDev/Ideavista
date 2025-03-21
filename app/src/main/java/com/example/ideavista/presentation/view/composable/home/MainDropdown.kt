package com.example.ideavista.presentation.view.composable.home

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.state.PropertyType
import com.example.ideavista.presentation.view.theme.NegroClaro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDropdown(
    selectedOption: PropertyType,
    onOptionSelected: (PropertyType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val propertyTypes = PropertyType.entries
    val context = LocalContext.current

    fun PropertyType.getDisplayName(context: Context): String {
        return context.getString(this.displayNameRes)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
    ) {
        OutlinedTextField(
            value = selectedOption.getDisplayName(context),
            onValueChange = {},
            readOnly = true,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .border(
                    BorderStroke(1.dp, Color.Gray),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                ) // Borde redondeado
                .background(
                    Blanco,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
                ) // Fondo con esquinas redondeadas
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Blanco)
        ) {
            PropertyType.entries.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.getDisplayName(context),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = NegroClaro
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier.height(32.dp)
                )
            }
        }
    }
}
