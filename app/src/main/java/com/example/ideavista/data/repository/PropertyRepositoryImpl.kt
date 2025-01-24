package com.example.ideavista.data.repository

import com.example.ideavista.data.sources.PropertyDataSource
import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.presentation.utils.Property

class PropertyRepositoryImpl(private val dataSource: PropertyDataSource) : PropertyRepository {

    override suspend fun fetchProperties(tipoPropiedad: String): List<Property> {
        return dataSource.fetchProperties(tipoPropiedad)
    }

    override suspend fun getPropertyById(propertyId: String): Property? {
        return dataSource.getPropertyById(propertyId)
    }
}