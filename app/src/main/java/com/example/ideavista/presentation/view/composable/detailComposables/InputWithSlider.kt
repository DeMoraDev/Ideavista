package com.example.ideavista.presentation.view.composable.detailComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Negro
import kotlin.math.roundToInt

@Composable
fun InputWithSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float>,
    step: Float
) {
    var text by remember { mutableStateOf(value.toString()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Input con diseño personalizado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .background(Color.White, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 12.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        // Intentar convertir el texto a un número
                        val newValue = it.toFloatOrNull()
                        if (newValue != null && newValue in range) {
                            val roundedValue = (newValue / step).roundToInt() * step
                            text = it // Actualizar el texto
                            onValueChange(roundedValue) // Actualizar el valor redondeado
                        } else if (it.isEmpty()) {
                            text = "" // Permite el valor vacío para borrar el campo
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Negro,
                        textAlign = TextAlign.Center // Alineamos el texto a la izquierda
                    ),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                text = "Introduce un valor",
                                color = Color.Gray,
                                style = TextStyle(fontSize = 16.sp)
                            )
                        }
                        innerTextField() // Esto muestra el texto cuando está vacío
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "€",
                    fontSize = 16.sp,
                    color = Negro
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Slider personalizado
        Slider(
            value = value,
            onValueChange = {
                onValueChange((it / step).roundToInt() * step) // Redondea y actualiza el valor
            },
            valueRange = range,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun InputWithSliderPreview() {
    var propertyValue by remember { mutableStateOf(250000f) }

    InputWithSlider(
        value = propertyValue,  // Pasamos el estado de Compose (Float)
        onValueChange = { newValue ->
            propertyValue = newValue  // Actualizamos el valor del estado
        },
        range = 0f..2_000_000f,
        step = 10000f
    )
}
