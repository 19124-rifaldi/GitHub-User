package com.faldi.githubuserapp.data.api

import com.faldi.githubuserapp.BuildConfig
import com.faldi.githubuserapp.data.api.request.FollowDataClass
import com.faldi.githubuserapp.data.api.request.GitApi
import com.faldi.githubuserapp.data.api.request.UserDetailService
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("search/users")
    @Headers("Authorization: token $myToken")
    suspend fun getUser(
        @Query("q") q : String,
    ): GitApi

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    suspend fun getDetailUser(
        @Path("username")username:String
    ):UserDetailService

    @GET("users/{username}/{type}")
    @Headers("Authorization: token ${BuildConfig.TOKEN}")
    suspend fun getFollow(
        @Path("username")username: String,
        @Path("type")type:String
    ):FollowDataClass



    companion object{
        private const val myToken = BuildConfig.TOKEN
    }
}



