package com.example.ideavista.presentation.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideavista.app.utils.LocaleUtils
import com.example.ideavista.data.local.DataStore.UserPreferences
import com.example.ideavista.domain.usecase.GetLanguageUseCase
import com.example.ideavista.domain.usecase.SaveCountryUseCase
import com.example.ideavista.domain.usecase.SaveLanguageUseCase
import com.example.ideavista.domain.usecase.SetUserAsReturningUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class OnboardingViewModel(
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,
    private val setUserAsReturningUseCase: SetUserAsReturningUseCase,
    private val getLanguageUseCase: GetLanguageUseCase
) : ViewModel() {


    // Estado para el idioma seleccionado
    private val _selectedLanguage = MutableStateFlow<String?>(null)
    val selectedLanguage: StateFlow<String?> = _selectedLanguage

    init {
        viewModelScope.launch {
            getLanguageUseCase().collect { language ->
                _selectedLanguage.value = language ?: Locale.getDefault().language
            }
        }
    }

    fun selectLanguage(context: Context, language: String) {
        viewModelScope.launch {
            _selectedLanguage.value = language
            saveLanguageUseCase.execute(language)
            LocaleUtils.changeAppLanguage(context, language)
            _translatedTexts.value = (translations[language] ?: translations["Español"])!!
        }
    }



    // Estado para el país seleccionado
    private val _selectedCountry = MutableStateFlow<String?>("España y Andorra")
    val selectedCountry: StateFlow<String?> = _selectedCountry


    fun selectCountry(country: String) {
        viewModelScope.launch {
            _selectedCountry.value = country // Actualiza el estado local
            saveCountryUseCase.execute(country) // Guarda el estado
        }
    }

    //Marca las preferencias como completadas- Usuario no nuevo
    fun setUserAsReturning() {
        viewModelScope.launch {
            setUserAsReturningUseCase.execute()
        }
    }

    //Estados para los idiomas

    private val _translatedTexts = MutableStateFlow<Map<String, String>>(emptyMap())
    val translatedTexts: StateFlow<Map<String, String>> = _translatedTexts


    val translations = mapOf(
        "Español" to mapOf(
            "choose_language" to "Elige el idioma de la aplicación",
            "continue" to "Continuar"
        ),
        "English" to mapOf(
            "choose_language" to "Choose the application language",
            "continue" to "Continue"
        ),
        "Français" to mapOf(
            "choose_language" to "Choisissez la langue de l'application",
            "continue" to "Continuer"
        ),
        "Italiano" to mapOf(
            "choose_language" to "Scegli la lingua dell'applicazione",
            "continue" to "Continua"
        ),
        "Português" to mapOf(
            "choose_language" to "Escolha o idioma do aplicativo",
            "continue" to "Continuar"
        ),
        "Eλληνικά" to mapOf(
            "choose_language" to "Επιλέξτε τη γλώσσα της εφαρμογής",
            "continue" to "Συνέχεια"
        ),
        "Русский" to mapOf(
            "choose_language" to "Выберите язык приложения",
            "continue" to "Продолжить"
        ),
        "Українська" to mapOf(
            "choose_language" to "Оберіть мову застосунку",
            "continue" to "Продовжити"
        ),
        "日本語" to mapOf(
            "choose_language" to "アプリの言語を選択してください",
            "continue" to "続ける"
        ),
        "Deutsch" to mapOf(
            "choose_language" to "Wählen Sie die Anwendungssprache",
            "continue" to "Weiter"
        ),
        "Dansk" to mapOf(
            "choose_language" to "Kies de taal van de applicatie",
            "continue" to "Doorgaan"
        ),
        "Norsk" to mapOf(
            "choose_language" to "Velg applikasjonsspråk",
            "continue" to "Fortsette"
        ),
        "Polski" to mapOf(
            "choose_language" to "Wybierz język aplikacji",
            "continue" to "Kontynuować"
        ),
        "Românâ" to mapOf(
            "choose_language" to "Alegeți limba aplicației",
            "continue" to "Continuați"
        ),
        "Suomi" to mapOf(
            "choose_language" to "Valitse sovelluksen kieli",
            "continue" to "Jatka"
        ),
        "Svenska" to mapOf(
            "choose_language" to "Välj applikationens språk",
            "continue" to "Fortsätt"
        ),
        "Català" to mapOf(
            "choose_language" to "Tria l'idioma de l'aplicació",
            "continue" to "Continuar"
        )
    )



}