package com.example.ideavista.presentation.view.composable.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta

@Composable
fun MenuContent(
    onClickLogInMenu: () -> Unit,
) {

    //TODO Refactorizar todo esto con composables reutilizables + Mejorar la apariencia(No exactamente como la original)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        // Item para el Box del Menú Cuenta
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.PersonOutline,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Entra en tu cuenta",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Une los favoritos y las búsquedas de tu ordenador, tablet y móvil.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { onClickLogInMenu() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Violeta),
                        elevation = ButtonDefaults.buttonElevation(0.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(text = "Entrar en tu cuenta", color = Blanco)
                    }
                }
            }
        }

        // Item para "Tus inmuebles"
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Tus inmuebles",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp), // Padding reducido dentro del Row
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Valorar tu casa gratis",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f) // Ocupa todo el espacio posible
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ir a valorar",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Buscar agencias para vender",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Encuentra la más adecuada para vender tu casa",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NegroClaro
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Buscar agencias",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Publica tu inmueble",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Tus 2 primeros anuncios son gratis",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NegroClaro
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ir a publicar anuncio",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
            }
        }

        // Item para "Ajustes"
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Ajustes",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "País de búsqueda",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Elegir país",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Idioma",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Elegir idioma",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Apariencia",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Elegir apariencia",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Servicios para ti",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Hipotecas",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Te conseguimos la mejor hipoteca gratuitamente",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NegroClaro
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ir a hipotecas",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Seguro de impago",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Protege el alquiler de tu vivienda",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NegroClaro
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ir a seguros de impago",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "News",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Noticias sobre el mercado inmobiliario",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NegroClaro
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ir a noticias",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Sobre ideavista",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Política de privacidad, condiciones de servicios, condiciones generales",
                                fontSize = 13.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Medium,
                                color = NegroClaro
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Buscar agencias",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { } // Hacer clic en toda la fila
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Versión actual 12.14.0",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Ir a versiones",
                            tint = Violeta,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
            }
        }
    }
}


