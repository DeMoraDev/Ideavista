package com.example.ideavista.data.local

import com.example.ideavista.presentation.utils.PropertyPreview

class Cache {
    companion object {
        var listaDePropiedades: List<PropertyPreview> = emptyList()

        fun getPropertyById(id: String): PropertyPreview? {
            return listaDePropiedades.find { it.id == id }
        }
    }
}