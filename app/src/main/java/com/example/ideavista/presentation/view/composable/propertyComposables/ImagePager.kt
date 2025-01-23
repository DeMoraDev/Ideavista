package com.example.ideavista.presentation.view.composable.propertyComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagePager(images: List<String>) {
    val pagerState = rememberPagerState()


    Box {
        HorizontalPager(
            state = pagerState,
            count = images.size,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Ajusta la altura de la imagen
        ) { page ->
            GlideImage(
                model = images[page], // Muestra la imagen correspondiente al índice actual
                contentDescription = "Property Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Indicadores de las páginas- puntitos tipicos
        /*  HorizontalPagerIndicator(
              pagerState = pagerState,
              modifier = Modifier
                  .align(Alignment.BottomCenter)
                  .padding(16.dp),
              activeColor = Color.White,
              inactiveColor = Color.Gray
          ) */

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .background(Color.Black.copy(alpha = 0.4f), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
                color = Color.White,
            )
        }
        //Logo inmobiliaria TODO- Hacer condicion para logo inmobiliaria y hacer algun mapeo para convertir el nombre de las inmobiliarias -> logo
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd) // Posiciona en la esquina superior derecha
                .padding(vertical = 6.dp)
        ) {
            GlideImage(
                model = "https://github.com/user-attachments/assets/5d50714c-ac46-4d86-826b-2d561ed3b6e1",
                contentDescription = "Inmobiliaria Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(70.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
        //Marca de agua TODO- Condición para la marca de agua y pasar variable del nombre inmobiliaria
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = "Paco Inmobiliaria",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.White.copy(alpha = 0.3f),
            )
        }
    }
}