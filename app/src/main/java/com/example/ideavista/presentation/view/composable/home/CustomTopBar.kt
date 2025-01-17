package com.example.ideavista.presentation.view.composable.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ideavista.R
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.view.theme.Amarillo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(step: HomeContentStep) {

    val pixelFont = FontFamily(
        Font(R.font.ideal_font)
    )

    val backgroundColor = when (step) {
        HomeContentStep.Home -> Color.White
        else -> Amarillo
    }

    TopAppBar(
        title = {
            when (step) {
                HomeContentStep.Home ->
                    Text(
                        text = "ideavista",
                        fontSize = 32.sp,
                        fontFamily = pixelFont,
                        color = Color.Black, // color texto
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )

                HomeContentStep.Search ->
                    Text(
                        text = "Tus búsquedas",
                        fontSize = 18.sp,
                        color = Color.Black, // color texto
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterStart)
                    )

                HomeContentStep.Favorites ->
                    Text(
                        text = "Tus favoritos",
                        fontSize = 18.sp,
                        color = Color.Black, // color texto
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterStart)
                    )

                HomeContentStep.Chat ->
                    Text(
                        text = "Tus conversaciones",
                        fontSize = 18.sp,
                        color = Color.Black, // color texto
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterStart)
                    )

                HomeContentStep.Menu ->
                    Text(
                        text = "Menú",
                        fontSize = 18.sp,
                        color = Color.Black, // color texto
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterStart)
                    )
            }
        },
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor // Aplica el color dinámico
        )
    )
}
