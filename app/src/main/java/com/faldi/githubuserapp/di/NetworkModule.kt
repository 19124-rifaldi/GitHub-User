package com.faldi.githubuserapp.di

import com.faldi.githubuserapp.BuildConfig
import com.faldi.githubuserapp.data.api.UserApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single{
        val url = BuildConfig.GIT_API
        val loggingInterceptor = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else -> HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserApiService::class.java)
    }

}
