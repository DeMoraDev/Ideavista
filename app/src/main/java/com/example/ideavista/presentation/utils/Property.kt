package com.example.ideavista.presentation.utils

import com.google.firebase.Timestamp

data class Property(

    //Datos para propiedades Tipo Vivienda

    val user_id: String? = "",
    var id: String? = "",
    //val titulo: String? = "",
    //val ciudad: String? = "",
    //val images: List<String> = emptyList(),
    //val planos: List<String> = emptyList(),
    val codigo_postal: Int? = 0,
    val comentario: String? = "",
    //val direccion: String? = "",
    //val estado: String? = "",
    val numero_baños: Int? = 0,
    //val numero_habitaciones: Int? = 0,
    //val planta: String? = "",
    //val distancia: Int? = 0,
    //val precio: Long? = null,
    //val tamaño: Int? = 0,
    val tipo_anuncio: String? = "",
    val tipo_propiedad: String? = "",
   // val descripcion: String? = "",
   // val additionalInfo: String? = "",
    val fecha_publicacion: Timestamp? = null,

    //Características básicas
    //val garaje: Boolean = false,
    val armarios_empotrados: Boolean? = false,
    val terraza: Boolean? = false,
    val calefaccion: Boolean? = false,
    val chimenea: Boolean? = false,
    val estado_propiedad: String? = "", //nuevo,reformar,bueno
    val orientacion: String? = "", //Norte, sur, este, oeste
    val ano: Int = 0,

    //Edificio
    val ascensor: Boolean? = false,

    //Equipamiento
    val aire_acondicionado: Boolean? = false,
    val jardin: Boolean? = false,
    val piscina: Boolean? = false,

    //Certificado energético
    val certificado_consumo: String? = "", //A,B,C,D,E,F Y G
    val certificado_emisiones: String? = "",


)
