package com.example.ideavista.presentation.view.composable.maps

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import android.provider.Settings
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RequestLocationPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val fineLocationPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onPermissionGranted() // Permiso concedido
        } else {
            onPermissionDenied() // Permiso denegado
        }
    }

    // Si no tienes el permiso, lo solicitamos
    if (fineLocationPermission != PackageManager.PERMISSION_GRANTED) {
        LaunchedEffect(Unit) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    } else {
        onPermissionGranted() // Si ya tenemos el permiso, mostramos el mapa
    }
}

@Composable
fun PermissionDeniedScreen() {
    val context = LocalContext.current // Esto ahora está dentro del contexto composable

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "No se ha concedido el permiso para acceder a la ubicación.")
        Button(
            onClick = {
                // Redirigir a la configuración de la app para activar el permiso
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:${context.packageName}")
                context.startActivity(intent)
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Ir a configuración")
        }
    }
}