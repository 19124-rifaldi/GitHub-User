package com.faldi.githubuserapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userFavorite: UserFavorite)

    @Delete
     fun delete(userFavorite: UserFavorite)

    @Query("SELECT * from UserFavorite ORDER BY username ASC")
    fun getAllFavorite(): Flow<List<UserFavorite>>

    @Query("SELECT * from UserFavorite WHERE username=:username")
    fun getFavoriteByUsername(username:String?): UserFavorite
}