package com.example.ideavista.data.sources

import com.example.ideavista.presentation.utils.Property

interface PropertyDataSource {
    suspend fun fetchProperties(modoPropiedad: String): List<Property>
    suspend fun getPropertyById(propertyId: String): Property?
}