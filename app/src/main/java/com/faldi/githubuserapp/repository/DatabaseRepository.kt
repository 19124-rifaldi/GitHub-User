package com.faldi.githubuserapp.repository

import com.faldi.githubuserapp.data.local.UserFavorite
import com.faldi.githubuserapp.data.local.UserFavoriteDao
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun insertFavorite(userFavorite: UserFavorite)
    fun getFavorite(): Flow<List<UserFavorite>>
    fun deleteFavorite(userFavorite:UserFavorite)
    suspend fun getFavoriteByUsername(username:String?): UserFavorite
}

class DatabaseRepositoryImpl(
    private val favoriteDao: UserFavoriteDao
):DatabaseRepository{
    override suspend fun insertFavorite(userFavorite: UserFavorite) {
        return favoriteDao.insert(userFavorite)
    }

    override fun getFavorite(): Flow<List<UserFavorite>> {
        return favoriteDao.getAllFavorite()
    }

    override  fun deleteFavorite(userFavorite: UserFavorite) {
        return favoriteDao.delete(userFavorite)
    }
    override suspend fun getFavoriteByUsername(username: String?): UserFavorite {
        return favoriteDao.getFavoriteByUsername(username)
    }

}