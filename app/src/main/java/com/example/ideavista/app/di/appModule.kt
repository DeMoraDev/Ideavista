package com.example.ideavista.app.di

import android.content.Context
import com.example.ideavista.presentation.viewmodel.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //Proveedor de SharedPreferences
    single { provideSharedPreferences(androidContext()) }

    //Proveedor de viewModels
    viewModel() { SplashScreenViewModel(get()) }
}

fun provideSharedPreferences(context: Context) =
    context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)