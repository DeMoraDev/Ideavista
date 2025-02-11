package com.example.ideavista.app.di

import android.content.Context
import com.example.ideavista.data.local.DataStore.UserPreferences
import com.example.ideavista.data.repository.AuthRepositoryImpl
import com.example.ideavista.data.repository.PreferencesRepositoryImpl
import com.example.ideavista.data.repository.PropertyPreviewRepositoryImpl
import com.example.ideavista.data.repository.PropertyRepositoryImpl
import com.example.ideavista.data.sources.PropertyDataSource
import com.example.ideavista.data.sources.PropertyDataSourceImpl
import com.example.ideavista.data.sources.PropertyPreviewDataSource
import com.example.ideavista.data.sources.PropertyPreviewDataSourceImpl
import com.example.ideavista.domain.repository.AuthRepository
import com.example.ideavista.domain.repository.PreferencesRepository
import com.example.ideavista.domain.repository.PropertyPreviewRepository
import com.example.ideavista.domain.repository.PropertyRepository
import com.example.ideavista.domain.usecase.CheckUserStatusUseCase
import com.example.ideavista.domain.usecase.SaveCountryUseCase
import com.example.ideavista.domain.usecase.SaveLanguageUseCase
import com.example.ideavista.domain.usecase.SetUserAsReturningUseCase
import com.example.ideavista.domain.usecase.auth.LoginUseCase
import com.example.ideavista.domain.usecase.auth.RegisterUseCase
import com.example.ideavista.domain.usecase.properties.FetchPropertiesPreviewUseCase
import com.example.ideavista.domain.usecase.properties.FetchPropertiesUseCase
import com.example.ideavista.domain.usecase.properties.GetPropertyDetailsUseCase
import com.example.ideavista.presentation.viewmodel.FilterViewModel
import com.example.ideavista.presentation.viewmodel.HomeScreenViewModel
import com.example.ideavista.presentation.viewmodel.LoginScreenViewModel
import com.example.ideavista.presentation.viewmodel.MapsViewModel
import com.example.ideavista.presentation.viewmodel.OnboardingViewModel
import com.example.ideavista.presentation.viewmodel.PropertyViewModel
import com.example.ideavista.presentation.viewmodel.SplashScreenViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

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
    viewModel { LoginScreenViewModel(get(), get()) } //ViewModel
    viewModel { HomeScreenViewModel(get(), get(), get()) }
    viewModel { PropertyViewModel(get()) }
    viewModel { FilterViewModel(get()) }
    viewModel { MapsViewModel(get()) }





    //Auth di
    single { FirebaseAuth.getInstance() } //Instancia de Firebase
    single<AuthRepository> { AuthRepositoryImpl(get()) } // Repositorio
    factory { RegisterUseCase(get()) } // UseCase
    factory { LoginUseCase(get()) }  //UseCase


    //Properties di
    single<PropertyDataSource>{
        PropertyDataSourceImpl(FirebaseFirestore.getInstance())
    }
    single<PropertyRepository> {
        PropertyRepositoryImpl(get())
    }
    single {
        FetchPropertiesUseCase(get())
    }
    single {
        GetPropertyDetailsUseCase(get())
    }

    //PropertyPreview di
    single<PropertyPreviewDataSource>{
        PropertyPreviewDataSourceImpl(FirebaseFirestore.getInstance())
    }
    single<PropertyPreviewRepository> {
        PropertyPreviewRepositoryImpl(get())
    }
    single {
        FetchPropertiesPreviewUseCase(get())
    }

    /*single {
        GetPropertyPreviewDetailsUseCase(get())
    } */

}
