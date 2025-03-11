package com.example.ideavista.presentation.view.composable.themeComposables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.R
import com.example.ideavista.presentation.view.composable.onboardingComposables.CountryItem
import com.example.ideavista.presentation.view.theme.Gris
import com.example.ideavista.presentation.view.theme.Violeta
//TODO este composable es igual que el de CountryItem, habría que hacer uno reutilizable
@Composable
fun SingleThemeOption(
    option: String,
    imageResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(if (isSelected) Gris.copy(alpha = 0.2f) else Color.Transparent)
            .border(
                width = 1.dp,
                color = if (isSelected) Violeta else Gris.copy(0.4f),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Imagen de theme",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f) // Mantiene la proporción cuadrada
                        .clip(RoundedCornerShape(4.dp))
                        .size(80.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = option,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Seleccionado",
                    tint = Violeta,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCountryItem() {
    CountryItem(
        countryName = "España",
        imageResId = R.drawable.spain,
        isSelected = true
    ) {}
}
