package com.faldi.githubuserapp.view.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.faldi.githubuserapp.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SearchRepository) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getTheme().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            pref.saveTheme(isDarkModeActive)
        }
    }
}