package com.example.ideavista.data.repository

import com.example.ideavista.data.sources.PropertyPreviewDataSource
import com.example.ideavista.domain.repository.PropertyPreviewRepository
import com.example.ideavista.presentation.utils.PropertyPreview

class PropertyPreviewRepositoryImpl(
    val dataSource: PropertyPreviewDataSource,
) : PropertyPreviewRepository {

    override suspend fun fetchPropertiesPreview(modoPropiedad: String, dropdownDbValue: String): List<PropertyPreview> {
        return dataSource.fetchPropertiesPreview(modoPropiedad, dropdownDbValue)
    }

}