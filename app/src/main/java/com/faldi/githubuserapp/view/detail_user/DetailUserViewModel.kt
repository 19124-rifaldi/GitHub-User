package com.faldi.githubuserapp.view.detail_user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faldi.githubuserapp.data.api.request.UserDetailService
import com.faldi.githubuserapp.data.local.UserFavorite
import com.faldi.githubuserapp.repository.DatabaseRepository
import com.faldi.githubuserapp.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val detailRepository: DetailRepository,
    private val favoriteRepository: DatabaseRepository
):ViewModel() {
    private val _detail = MutableLiveData<UserDetailService>()
    val detail : LiveData<UserDetailService> = _detail

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _view = MutableLiveData<Boolean>()
    val view : LiveData<Boolean> = _view

    private val _checkFavorite = MutableLiveData<UserFavorite>()
    val checkFavorite : LiveData<UserFavorite> = _checkFavorite



    fun getUserDetail(username:String){
        _loading.value= true
        _view.value = false

        viewModelScope.launch {
            try {
                _loading.value= false
                _view.value = true
                val data =detailRepository.getDetailUser(username)
                _detail.value = data
            }catch (e:Exception){
                Log.e("TAG", "onFailure: ${e.message.toString()}")
            }
        }
    }

    fun addFavorite(userFavorite: UserFavorite){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(userFavorite)

        }
    }
    fun getFavoriteByUsername(username:String?){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = favoriteRepository.getFavoriteByUsername(username)
                _checkFavorite.postValue(data)
            }catch (e:Exception){
                Log.e("TAG test check", "onFailure: ${e.message.toString()}")
            }

        }
    }

    fun deleteFavorite(userFavorite: UserFavorite){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(userFavorite)
        }
    }



}