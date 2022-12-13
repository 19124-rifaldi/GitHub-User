package com.faldi.githubuserapp.view.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faldi.githubuserapp.repository.DatabaseRepository

class FavoriteViewModel(
    favoriteRepository: DatabaseRepository
):ViewModel() {
    val getFavorite = favoriteRepository.getFavorite().asLiveData()
}