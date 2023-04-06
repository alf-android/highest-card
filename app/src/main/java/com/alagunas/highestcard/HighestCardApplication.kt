package com.alagunas.highestcard

import android.app.Application
import com.alagunas.data.di.dataModule
import com.alagunas.highestcard.di.uiModule
import com.alagunas.usecases.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HighestCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(uiModule, dataModule, useCasesModule)
            )
        }
    }
}