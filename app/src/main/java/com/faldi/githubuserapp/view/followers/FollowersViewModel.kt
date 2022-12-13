package com.faldi.githubuserapp.view.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faldi.githubuserapp.data.api.request.FollowDataClassItem
import com.faldi.githubuserapp.repository.FollowRepository
import kotlinx.coroutines.launch

class FollowersViewModel(
    private val followRepository: FollowRepository
):ViewModel() {

    private val _username  = MutableLiveData<ArrayList<FollowDataClassItem>>()
    val username : LiveData<ArrayList<FollowDataClassItem>> = _username

    fun getUsername(usernameData :String){
        viewModelScope.launch{
            try {
                val data = followRepository.getFollower(usernameData, type)
                _username.value = data
            }catch (e:Exception){
                Log.e("TAG", "onFailure: ${e.message.toString()}")
            }
        }
    }
    companion object{
        private const val type = "followers"
    }
}