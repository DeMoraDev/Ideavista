package com.example.ideavista.domain.usecase.properties

import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.presentation.utils.Property

class GetPropertyDetailsUseCase(private val repository: PropertyRepository) {
    suspend operator fun invoke(propertyId: String): Property? {
        return repository.getPropertyById(propertyId)
    }
}