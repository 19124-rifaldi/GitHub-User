package com.faldi.githubuserapp

import android.app.Application
import com.faldi.githubuserapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    dataStoreModule,
                    databaseModule
                )
            )
        }
    }
}