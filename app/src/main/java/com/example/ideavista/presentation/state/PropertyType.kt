package com.example.ideavista.presentation.state
import androidx.annotation.StringRes
import com.example.ideavista.R

enum class PropertyType(@StringRes val displayNameRes: Int, val dbValue: String) {
    OBRA_NUEVA(R.string.home_dropdown_new_home, "obra_nueva"),
    VIVIENDAS(R.string.home_dropdown_homes , "viviendas"),
    OFICINAS(R.string.home_dropdown_offices, "oficinas"),
    LOCALES_NAVES(R.string.home_dropdown_commercial_property, "locales_naves"),
    TRASPASOS(R.string.home_dropdown_transfers, "traspasos"),
    GARAJES(R.string.home_dropdown_garages, "garajes"),
    TERRENOS(R.string.home_dropdown_land, "terrenos"),
    TRASTEROS(R.string.home_dropdown_storage_rooms, "trasteros"),
    EDIFICIOS(R.string.home_dropdown_buildings, "edificios");

    companion object {
        // Nombre de la base de datos
        fun fromDbValue(value: String): PropertyType? {
            return entries.find { it.dbValue == value }
        }
    }
}
