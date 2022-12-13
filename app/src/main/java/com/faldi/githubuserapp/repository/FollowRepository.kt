package com.faldi.githubuserapp.repository

import com.faldi.githubuserapp.data.api.UserApiService
import com.faldi.githubuserapp.data.api.request.FollowDataClass

interface FollowRepository {
    suspend fun getFollower(username: String,type: String): FollowDataClass
    suspend fun getFollowing(username: String,type: String): FollowDataClass
}
class FollowRepositoryImpl(
    private val userApiService: UserApiService
):FollowRepository{
    override suspend fun getFollower(username: String,type: String): FollowDataClass {
        return userApiService.getFollow(username, type)
    }

    override suspend fun getFollowing(username: String,type: String): FollowDataClass {
        return userApiService.getFollow(username,type)
    }
}