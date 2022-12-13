package com.faldi.githubuserapp.view.main

import android.util.Log
import androidx.lifecycle.*
import com.faldi.githubuserapp.data.api.request.Item
import com.faldi.githubuserapp.repository.SearchRepository
import kotlinx.coroutines.launch


class MainViewModel(
    private val repo : SearchRepository
    ):ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return repo.getTheme().asLiveData()
    }

    private val _searchQuery = MutableLiveData<List<Item>>()
    val searchQuery : LiveData<List<Item>> = _searchQuery


    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    companion object{
        private const val TAG = "MainViewModel"
    }

    fun getName(username:String){
        search(username)
    }

    private fun search(username:String){
        _loading.value = false
        viewModelScope.launch {
            try {
                _loading.value = false
                val data = repo.searchUser(username)
                _searchQuery.value = data.items

            }catch (e:Exception){
                Log.e(TAG, "onFailure: ${e.message.toString()}")
                _loading.value = false
            }
        }


//        val api = GitApiConfig.getUserService().getUser(username)
//        api.enqueue(object : Callback<GitApi>{
//
//            override fun onResponse(call: Call<GitApi>, response: Response<GitApi>) {
//                if (response.isSuccessful) {
//                    _loading.value = false
//                    _searchQuery.value = response.body()?.items as List<Item>
//                }
//            }
//
//            override fun onFailure(call: Call<GitApi>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//                _loading.value = false
//            }
//
//        })

    }
}