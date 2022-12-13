package com.faldi.githubuserapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserFavorite (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uid")
    var uid:Int,
    @ColumnInfo(name = "username")
    var username: String ,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String?
):Parcelable