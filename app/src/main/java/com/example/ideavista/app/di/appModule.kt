package com.example.ideavista.app.di

import android.content.Context
import com.example.ideavista.data.local.DataStore.UserPreferences
import com.example.ideavista.data.repository.PreferencesRepositoryImpl
import com.example.ideavista.domain.repository.PreferencesRepository
import com.example.ideavista.domain.usecase.CheckUserStatusUseCase
import com.example.ideavista.domain.usecase.SaveCountryUseCase
import com.example.ideavista.domain.usecase.SaveLanguageUseCase
import com.example.ideavista.domain.usecase.SetUserAsReturningUseCase
import com.example.ideavista.presentation.viewmodel.OnboardingViewModel
import com.example.ideavista.presentation.viewmodel.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Data Layer
    single { UserPreferences(androidContext()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }

    // Use Cases
    single { CheckUserStatusUseCase(get()) }
    single { SetUserAsReturningUseCase(get()) }
    single { SaveLanguageUseCase(get()) }
    single { SaveCountryUseCase(get()) }

    // ViewModels
    viewModel { SplashScreenViewModel(get()) }
    viewModel { OnboardingViewModel(get(), get(), get()) }
}
