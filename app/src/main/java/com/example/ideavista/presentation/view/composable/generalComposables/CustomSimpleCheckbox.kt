package com.example.ideavista.presentation.view.composable.generalComposables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun CustomSimpleCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Violeta,
                uncheckedColor = Color.Gray,
                checkmarkColor = Blanco
            )
        )
        Text(text = label)
    }
}
