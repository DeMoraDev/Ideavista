package com.example.ideavista.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ideavista.app.di.appModule
import com.example.ideavista.app.di.networkModule
import com.example.ideavista.presentation.view.navigation.NavManager
import com.example.ideavista.presentation.view.theme.IdeavistaTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            IdeavistaTheme {
                val navController = rememberNavController()

                // Usar el NavManager para gestionar la navegación
                NavManager(navHostController = navController)
            }
        }
    }
}