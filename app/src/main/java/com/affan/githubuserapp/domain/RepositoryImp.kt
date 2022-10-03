package com.affan.githubuserapp.domain

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.user.User

class RepositoryImp (
    private val remoteDataSource: DataSource
        ) : Repository {

    override suspend fun getSearchUsers(userName: String, perPage: Int, page: Int): User {
        return remoteDataSource.getSearchUsers(userName,perPage,page)
    }

}