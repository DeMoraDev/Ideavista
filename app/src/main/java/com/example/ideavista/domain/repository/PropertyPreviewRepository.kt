package com.example.ideavista.domain.repository

import com.example.ideavista.presentation.utils.PropertyPreview

interface PropertyPreviewRepository {
    suspend fun fetchPropertiesPreview(tipoPropiedad: String): List<PropertyPreview>
}