package com.faldi.githubuserapp.di

import com.faldi.githubuserapp.repository.*

import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> {SearchRepositoryImpl(get(),get())}
    single<DetailRepository> {DetailRepositoryImpl(get())}
    single<FollowRepository> {FollowRepositoryImpl(get())}
    single<DatabaseRepository> {DatabaseRepositoryImpl(get())  }
}