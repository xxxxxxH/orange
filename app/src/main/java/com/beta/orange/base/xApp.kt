package com.beta.orange.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class xApp : Application() {
    companion object {
        lateinit var instance: xApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@xApp)
            loadKoinModules(
                mutableListOf(
                    appViewModelModule, appServiceModule, appDataSourceModule,
                    appRepositoryModule, appExtraModule
                )
            )
        }
    }
}