package com.example.ideavista.domain.repository

import com.example.ideavista.presentation.utils.Property

interface PropertyRepository {
    suspend fun fetchProperties(tipoPropiedad: String): List<Property>
    suspend fun getPropertyById(propertyId: String): Property?
}