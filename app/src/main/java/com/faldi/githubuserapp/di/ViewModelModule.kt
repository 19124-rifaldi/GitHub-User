package com.faldi.githubuserapp.di

import com.faldi.githubuserapp.view.detail_user.DetailUserViewModel
import com.faldi.githubuserapp.view.favorite.FavoriteViewModel
import com.faldi.githubuserapp.view.followers.FollowersViewModel
import com.faldi.githubuserapp.view.following.FollowingViewModel
import com.faldi.githubuserapp.view.main.MainViewModel
import com.faldi.githubuserapp.view.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel {MainViewModel(get()) }
    viewModel {SettingViewModel(get())}
    viewModel {DetailUserViewModel(get(),get())}
    viewModel { FollowersViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}

