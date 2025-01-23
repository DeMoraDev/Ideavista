package com.example.ideavista.presentation.view.composable.propertyComposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Violeta


@Composable
fun LoadingBar() {

    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp),
        color = Violeta,
        strokeCap = StrokeCap.Square,
        backgroundColor = Blanco
    )
}