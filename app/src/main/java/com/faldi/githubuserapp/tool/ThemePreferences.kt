package com.faldi.githubuserapp.tool

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ThemePreferences (private val context: Context) {

    fun getTheme(): Flow<Boolean> {
        return context.modeDark.data.map { preferences->
            preferences[KEY] ?: false

        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        context.modeDark.edit{ preferences ->
            preferences[KEY] = isDarkModeActive
        }
    }
    companion object{
        private const val setting = "theme_setting"
        private val KEY = booleanPreferencesKey("key")
        private val Context.modeDark by preferencesDataStore(setting)
    }
}