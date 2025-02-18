package com.example.ideavista.presentation.view.composable.onboardingComposables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.R

@Composable
fun CountrySelectionStep(
    selectedCountry: String?,
    onCountrySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val countryData = listOf(
            "Portugal" to R.drawable.portugal,
            "España y Andorra" to R.drawable.spain,
            "Italia" to R.drawable.italy
        )

        Text(
            text = "Elige el país donde buscas o donde se encuentra tu inmueble",
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
            items(countryData) { (country, imageResId) ->
                val isSelected = country == selectedCountry
                CountryItem(
                    countryName = country,
                    imageResId = imageResId,
                    isSelected = isSelected,
                    onClick = { onCountrySelected(country) }
                )
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}