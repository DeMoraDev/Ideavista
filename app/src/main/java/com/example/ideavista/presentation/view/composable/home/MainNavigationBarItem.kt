package com.example.ideavista.presentation.view.composable.home

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import com.example.ideavista.presentation.state.HomeContentStep
import com.example.ideavista.presentation.state.HomeScreenState
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

@Composable
fun MainNavigationBarItem(
    state: HomeScreenState,
    step: HomeContentStep,
    iconFilled: @Composable () -> Unit,
    iconOutlined: @Composable () -> Unit,
    label: String,
    onClick: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                if (state.step == step) {
                    iconFilled()
                } else {
                    iconOutlined()
                }
            },
            label = { Text(label) },
            selected = state.step == step,
            onClick = onClick
        )
    }

}