package com.example.ideavista.presentation.utils

import com.google.firebase.Timestamp

data class Property(
    val user_id: String? = "",
    var id: String? = "",
    val titulo: String? = "",
    val ciudad: String? = "",
    val images: List<String> = emptyList(),
    val planos: List<String> = emptyList(),
    val codigo_postal: Int? = 0,
    val comentario: String? = "",
    val direccion: String? = "",
    val estado: String? = "",
    val garaje: Boolean = false,
    val numero_baños: Int? = 0,
    val numero_habitaciones: Int? = 0,
    val planta: String? = "",
    val distancia: Int? = 0,
    val precio: Long? = null,
    val tamaño: Int? = 0,
    val tipo_anuncio: String? = "",
    val tipo_propiedad: String? = "",
    val descripcion: String? = "",
    val additionalInfo: String? = "",
    val fecha_publicacion: Timestamp? = null
)
