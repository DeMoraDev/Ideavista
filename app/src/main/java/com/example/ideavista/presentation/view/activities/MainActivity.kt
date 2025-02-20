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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.ideavista.app.di.appModule
import com.example.ideavista.app.di.networkModule
import com.example.ideavista.app.utils.LocaleUtils
import com.example.ideavista.data.local.DataStore.UserPreferences
import com.example.ideavista.presentation.view.navigation.NavManager
import com.example.ideavista.presentation.view.theme.IdeavistaTheme
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    //Cargar preferencias de Usuario
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferences = UserPreferences(this)

        lifecycleScope.launch {
            userPreferences.selectedLanguage.collect { savedLanguage ->
                LocaleUtils.changeAppLanguage(this@MainActivity, savedLanguage)
            }
        }

        enableEdgeToEdge()
        setContent {
            IdeavistaTheme {
                val navController = rememberNavController()
                NavManager(navHostController = navController)
            }
        }
    }
}