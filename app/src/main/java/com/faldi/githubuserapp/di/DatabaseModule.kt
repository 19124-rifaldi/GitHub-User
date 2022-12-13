package com.faldi.githubuserapp.di

import androidx.room.Room
import com.faldi.githubuserapp.data.local.FavoriteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<FavoriteDatabase>().favDao() }
    single{
        Room.databaseBuilder(
            androidContext(),
            FavoriteDatabase::class.java,
            "note_database12"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}