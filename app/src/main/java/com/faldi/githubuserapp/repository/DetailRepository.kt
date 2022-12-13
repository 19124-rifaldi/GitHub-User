package com.faldi.githubuserapp.repository

import com.faldi.githubuserapp.data.api.UserApiService
import com.faldi.githubuserapp.data.api.request.UserDetailService

interface DetailRepository {
    suspend fun getDetailUser(username: String): UserDetailService

}
class DetailRepositoryImpl(private val userApiService: UserApiService):DetailRepository{
    override suspend fun getDetailUser(username: String): UserDetailService {
        return userApiService.getDetailUser(username)
    }



}