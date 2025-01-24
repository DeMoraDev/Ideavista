package com.example.ideavista.domain.usecase.properties

import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.presentation.utils.Property

class FetchPropertiesUseCase(private val repository: PropertyRepository) {
    suspend operator fun invoke(tipoPropiedad: String): List<Property> {
        return repository.fetchProperties(tipoPropiedad)
    }
}