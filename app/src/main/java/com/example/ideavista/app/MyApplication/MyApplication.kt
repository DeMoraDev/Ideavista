package com.example.ideavista.app.MyApplication

import android.app.Application
import com.example.ideavista.app.di.appModule
import com.example.ideavista.app.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}