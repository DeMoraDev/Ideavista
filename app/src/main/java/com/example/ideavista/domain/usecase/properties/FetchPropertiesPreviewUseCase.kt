package com.example.ideavista.domain.usecase.properties

import com.example.ideavista.domain.repository.PropertyPreviewRepository
import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.presentation.utils.Property
import com.example.ideavista.presentation.utils.PropertyPreview

class FetchPropertiesPreviewUseCase(private val repository: PropertyPreviewRepository) {
    suspend operator fun invoke(tipoPropiedad: String,dropdownDbValue: String): List<PropertyPreview> {
        return repository.fetchPropertiesPreview(tipoPropiedad,dropdownDbValue)
    }
}