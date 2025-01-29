package com.example.ideavista.data.sources

import android.util.Log
import com.example.ideavista.presentation.utils.Property
import com.example.ideavista.presentation.utils.PropertyPreview
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PropertyPreviewDataSourceImpl(private val db: FirebaseFirestore) : PropertyPreviewDataSource {

    override suspend fun fetchPropertiesPreview(tipoPropiedad: String): List<PropertyPreview> {
        val snapshot = db.collection("property")
            .whereEqualTo("tipo_propiedad", tipoPropiedad)
            .get()
            .await()

        for (document in snapshot.documents) {
            Log.d("Firestore", "Documento: ${document.data}")
        }

        return snapshot.documents.mapNotNull { document ->
            try {
                PropertyPreview(
                    id = document.id,
                    user_id = document.getString("user_id") ?: "",
                    titulo = document.getString("titulo") ?: "",
                    ciudad = document.getString("ciudad") ?: "",
                    images = document.get("images") as? List<String> ?: emptyList(),
                    planos = document.get("planos") as? List<String> ?: emptyList(),
                    direccion = document.getString("direccion") ?: "",
                    estado = document.getString("estado") ?: "",
                    garaje = document.getBoolean("garaje") ?: false,
                    numero_habitaciones = document.getLong("numero_habitaciones")?.toInt() ?: 0,
                    planta = document.getString("planta") ?: "",
                    distancia = document.getLong("distancia")?.toInt() ?: 0,
                    precio = document.getLong("precio"),
                    tamaño = document.getLong("tamaño")?.toInt(),
                    descripcion = document.getString("descripcion") ?: "",
                    additionalInfo = document.getString("additionalInfo") ?: ""
                )
            } catch (e: Exception) {
                Log.e("Firestore", "Error mapeando PropertyPreview: ${e.message}")
                null
            }
        }
    }
}