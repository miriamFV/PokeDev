package com.example.pokedev.common

import android.app.Application
import com.example.data.di.dataModule
import com.example.pokedev.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeDevApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokeDevApplication)
            modules (
                uiModule,
                dataModule
            )
        }
    }
}