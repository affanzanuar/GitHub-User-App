package com.affan.githubuserapp.data.remote

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.user.User

class RemoteDataSource(
    private val apiService: ApiService
) : DataSource {

    override suspend fun getSearchUsers(userName: String, perPage: Int, page: Int): List<User> {
        return apiService.getSearchUsers(userName,perPage,page)
    }

}