package com.example.ideavista.app.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleUtils {
    fun changeAppLanguage(context: Context, language: String?) {
        val locale = when (language) {
            "Español" -> Locale("es")
            "English" -> Locale("en")
            "Français" -> Locale("fr")
            "Deutsch" -> Locale("de")
            "Nederlands" -> Locale("nl")
            "Italiano" -> Locale("it")
            else -> Locale(".getDefault()")
        }

        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}