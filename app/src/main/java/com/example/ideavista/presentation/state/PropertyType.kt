package com.example.ideavista.presentation.state

enum class PropertyType(val displayName: String, val dbValue: String) {
    OBRA_NUEVA("Obra nueva", "obra_nueva"),
    VIVIENDAS("Viviendas", "viviendas"),
    OFICINAS("Oficinas", "oficinas"),
    LOCALES_NAVES("Locales o naves", "locales_naves"),
    TRASPASOS("Traspasos", "traspasos"),
    GARAJES("Garajes", "garajes"),
    TERRENOS("Terrenos", "terrenos"),
    TRASTEROS("Trasteros", "trasteros"),
    EDIFICIOS("Edificios", "edificios");

    companion object {
        // Nombre de la base de datos
        fun fromDbValue(value: String): PropertyType? {
            return entries.find { it.dbValue == value }
        }

        // Nombre a mostrar
        fun fromDisplayName(name: String): PropertyType? {
            return entries.find { it.displayName == name }
        }
    }
}
