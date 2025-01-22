package com.example.ideavista.presentation.utils

import com.google.firebase.Timestamp

data class Property(
    val user_id: String?= "",
    val titulo: String?= "",
    val ciudad: String?= "",
    val codigo_postal: Int?= 0,
    val direccion: String?= "",
    val estado: String?= "",
    val numero_baños: Int?= 0,
    val numero_habitaciones: Int?= 0,
    val planta: String?= "",
    val distancia: Int?= 0,
    val precio: Long? = null,
    val tamaño: Int?= 0,
    val tipo_anuncio: String?= "",
    val tipo_propiedad: String?= "",
    val descripcion: String?= "",
    val additionalInfo: String?= "",
    val fecha_publicacion: Timestamp? = null
)
