package com.faldi.githubuserapp.repository

import com.faldi.githubuserapp.data.api.UserApiService
import com.faldi.githubuserapp.data.api.request.GitApi
import com.faldi.githubuserapp.tool.ThemePreferences
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchUser(query:String): GitApi
    fun getTheme():Flow<Boolean>
    suspend fun saveTheme(theme:Boolean)
}
class SearchRepositoryImpl (
    private val dataSource : UserApiService,
    private val themePref : ThemePreferences
):SearchRepository{
    override suspend fun searchUser(query: String): GitApi = dataSource.getUser(query)
    override fun getTheme(): Flow<Boolean> {
        return themePref.getTheme()
    }

    override suspend fun saveTheme(theme: Boolean) {
        themePref.saveThemeSetting(theme)
    }
}