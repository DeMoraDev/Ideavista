package com.example.ideavista.presentation.view.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.placeholder
import com.example.ideavista.presentation.view.composable.maps.SingleOptionMaps
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.Blanco
import com.example.ideavista.presentation.view.theme.Negro
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MapsOptionScreen(
    navHostController: NavHostController
) {
    var searchText by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.mapsOptionScreen_scaffold_title),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Negro
                        )
                        Text(
                            text = stringResource(id = R.string.mapsOptionScreen_scaffold_button),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Violeta,
                            modifier = Modifier.clickable { /* Acción de filtro */ }
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Amarillo,
                    navigationIconContentColor = Violeta
                ),
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Amarillo)
                        .clipToBounds()
                        .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Blanco, shape = RoundedCornerShape(4.dp))
                            .border(1.dp, Negro, shape = RoundedCornerShape(4.dp))

                    ) {
                        SearchBar(
                            query = searchText,
                            onQueryChange = { searchText = it },
                            windowInsets = WindowInsets(top = 0.dp),
                            onSearch = { isSearchActive = false },
                            active = isSearchActive,
                            onActiveChange = { isSearchActive = it },
                            placeholder = {
                                Text(
                                    stringResource(id = R.string.mapsOptionScreen_scaffold_searchbar),
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Search,
                                    contentDescription = "Buscar",
                                    tint = Color.Gray,
                                )
                            },
                            trailingIcon = {
                                if (searchText.isNotEmpty()) {
                                    IconButton(
                                        onClick = { searchText = "" },
                                    ) {
                                        Icon(
                                            Icons.Filled.Clear,
                                            contentDescription = "Borrar",
                                            tint = Color.Gray
                                        )
                                    }
                                }
                            },
                            colors = SearchBarDefaults.colors(
                                containerColor = Color.White,
                                dividerColor = Color.Transparent
                            ),
                            content = {}
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 6.dp)
                ) {
                    SingleOptionMaps(
                        imageRes = R.drawable.option_maps_1,
                        title = stringResource(id = R.string.mapsOptionScreen_draw_title),
                        subtitle = stringResource(id = R.string.mapsOptionScreen_draw_message),
                        onClickOptionMaps = { navHostController.navigate("drawing") }
                    )
                    SingleOptionMaps(
                        imageRes = R.drawable.option_maps_2,
                        title = stringResource(id = R.string.mapsOptionScreen_search_title),
                        subtitle = stringResource(id = R.string.mapsOptionScreen_search_message),
                        onClickOptionMaps = { }
                    )
                    SingleOptionMaps(
                        imageRes = R.drawable.option_maps_3,
                        title = stringResource(id = R.string.mapsOptionScreen_around_title),
                        subtitle = stringResource(id = R.string.mapsOptionScreen_around_message),
                        onClickOptionMaps = { }
                    )
                    SingleOptionMaps(
                        imageRes = R.drawable.option_maps_4,
                        title = stringResource(id = R.string.mapsOptionScreen_phone_title),
                        subtitle = stringResource(id = R.string.mapsOptionScreen_phone_message),
                        onClickOptionMaps = { }
                    )
                }
            }
        }
    }
}
