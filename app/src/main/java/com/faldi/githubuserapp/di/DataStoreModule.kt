package com.faldi.githubuserapp.di


import com.faldi.githubuserapp.tool.ThemePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single{
        ThemePreferences(androidContext())
    }
}