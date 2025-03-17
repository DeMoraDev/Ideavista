package com.example.ideavista.domain.repository

import com.example.ideavista.presentation.utils.PropertyPreview

interface PropertyPreviewRepository {
    suspend fun fetchPropertiesPreview(
        modoPropiedad: String,
        dropdownDbValue: String,
        filters: Map<String, Boolean?>
    ): List<PropertyPreview>
}