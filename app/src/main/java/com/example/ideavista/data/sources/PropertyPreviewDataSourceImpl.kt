package com.example.ideavista.data.sources

import android.util.Log
import com.example.ideavista.data.local.Cache
import com.example.ideavista.presentation.utils.PropertyPreview
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PropertyPreviewDataSourceImpl(private val db: FirebaseFirestore) : PropertyPreviewDataSource {

    override suspend fun fetchPropertiesPreview(
        modoPropiedad: String,
        dropdownDbValue: String,
        garaje: Boolean?,
        jardin: Boolean?
    ): List<PropertyPreview> {
        // Construcción de la consulta dinámica
        var query = db.collection("property")
            .whereEqualTo("modo_propiedad", modoPropiedad)
            .whereEqualTo("tipo_propiedad", dropdownDbValue)

        // Agregar filtros opcionales si tienen valor
        if (garaje != null) query = query.whereEqualTo("garaje", garaje)
        if (jardin != null) query = query.whereEqualTo("jardin", jardin)

        val snapshot = query.get().await()

        val propertiesPreview = snapshot.documents.mapNotNull { document ->
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

        // Guardar en caché
        Cache.listaDePropiedades = propertiesPreview
        Log.d("Firestore", "Datos guardados en companion object: ${Cache.listaDePropiedades}")

        return propertiesPreview
    }

}