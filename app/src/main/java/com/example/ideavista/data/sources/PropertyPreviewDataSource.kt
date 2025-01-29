package com.example.ideavista.data.sources

import com.example.ideavista.presentation.utils.PropertyPreview

interface PropertyPreviewDataSource {
    suspend fun fetchPropertiesPreview(tipoPropiedad: String): List<PropertyPreview>
}