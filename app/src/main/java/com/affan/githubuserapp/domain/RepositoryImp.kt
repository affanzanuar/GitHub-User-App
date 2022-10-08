package com.affan.githubuserapp.domain

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse

class RepositoryImp (
    private val remoteDataSource: DataSource
        ) : Repository {

    override suspend fun getSearchUsers(
        token : String,
        userName: String,
        perPage: Int,
        page: Int
    ): UserResponse {
        return remoteDataSource.getSearchUsers(token, userName,perPage,page)
    }

    override suspend fun getDetails(
        userName: String,
        token: String
    ): DetailsResponse {
        return remoteDataSource.getDetails(userName,token)
    }

}