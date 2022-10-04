package com.affan.githubuserapp.data.remote

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse

class RemoteDataSource(
    private val apiService: ApiService
) : DataSource {

    override suspend fun getSearchUsers(userName: String, perPage: Int, page: Int): UserResponse {
        return apiService.getSearchUsers(userName,perPage,page)
    }

}