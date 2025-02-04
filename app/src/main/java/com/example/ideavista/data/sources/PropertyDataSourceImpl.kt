package com.example.ideavista.data.sources

import android.util.Log
import com.example.ideavista.presentation.utils.Property
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PropertyDataSourceImpl(private val db: FirebaseFirestore) : PropertyDataSource {

    override suspend fun fetchProperties(modoPropiedad: String): List<Property> {
        val snapshot = db.collection("property")
            .whereEqualTo("modo_propiedad", modoPropiedad)
            .get()
            .await()

        return snapshot.documents.mapNotNull { document ->
            try {
                val property = document.toObject(Property::class.java)
                property?.copy(id = document.id)
            } catch (e: Exception) {
                Log.e("PropertyDataSource", "Error mapeando documento: ${e.message}")
                null
            }
        }
    }

    override suspend fun getPropertyById(propertyId: String): Property? {
        val document = db.collection("property").document(propertyId).get().await()
        return if (document.exists()) {
            document.toObject(Property::class.java)?.copy(id = document.id)
        } else {
            null
        }
    }
}