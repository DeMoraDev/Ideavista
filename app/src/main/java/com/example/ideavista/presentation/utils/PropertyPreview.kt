package com.example.ideavista.presentation.utils

data class PropertyPreview(
    val user_id: String? = "",
    var id: String? = "",
    val titulo: String? = "",
    val ciudad: String? = "",
    val images: List<String> = emptyList(),
    val planos: List<String> = emptyList(),
    val direccion: String? = "",
    val estado: String? = "",
    val garaje: Boolean = false,
    val numero_habitaciones: Int? = 0,
    val planta: String? = "",
    val distancia: Int? = 0,
    val precio: Long? = null,
    val tamaño: Int? = 0,
    val descripcion: String? = "",
    val additionalInfo: String? = "",
)
