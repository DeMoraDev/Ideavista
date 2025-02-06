package com.example.ideavista.presentation.view.composable.generalComposables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun CustomSwitch(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Sin clickable en el Row
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(end = 8.dp) // Espacio entre texto y switch
        )

        Box(
            modifier = Modifier
                .width(36.dp)  // Tamaño del switch (track y thumb juntos)
                .height(24.dp), // Ajuste para que no se recorte
            contentAlignment = Alignment.CenterStart
        ) {
            // TRACK (Más delgado)
            Box(
                modifier = Modifier
                    .width(38.dp)  // El track tiene el mismo tamaño que el contenedor
                    .height(18.dp) // Track más delgado
                    .clip(CircleShape)
                    .background(
                        if (isChecked) Violeta.copy(alpha = 0.5f)
                        else Color.Gray.copy(alpha = 0.3f)
                    )
                    .align(Alignment.Center)
            )

            // THUMB (Más grande y sobre el track)
            val thumbOffset by animateDpAsState(
                targetValue = if (isChecked) 16.dp else 0.dp, // Desplazamiento suave
                animationSpec = tween(durationMillis = 300) // Tiempo de animación
            )

            Box(
                modifier = Modifier
                    .size(20.dp) // Tamaño del thumb (no se altera)
                    .offset(x = thumbOffset) // Movimiento suave del thumb
                    .clip(CircleShape) // Mantiene el thumb con bordes redondeados
                    .background(if (isChecked) Violeta else Color.Gray)
                    .clickable(
                        onClick = { onCheckedChange(!isChecked) }, // Solo cambia el estado
                        indication = rememberRipple(
                            bounded = false, // El ripple se extiende fuera del contenedor
                            color = if (isChecked) Color.Gray else Violeta, // Cambiar el color según el estado
                            radius = 40.dp // Hacemos que el ripple sea más grande, pero no afecta el thumb
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )
        }
    }
}


@Preview
@Composable
fun PreviewToggleSwitch() {
    var isChecked by remember { mutableStateOf(false) }

    CustomSwitch(
        label = "Opción",
        isChecked = isChecked,
        onCheckedChange = { isChecked = it }
    )
}
