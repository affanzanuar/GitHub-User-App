package com.affan.githubuserapp.domain

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.favorite.Favorite
import com.affan.githubuserapp.data.model.repository.RepositoryResponse
import com.affan.githubuserapp.data.model.user.UserResponse

class RepositoryImp (
    private val remoteDataSource: DataSource,
    private val localDataSource : DataSource
        ) : Repository {

    override suspend fun getSearchUsers(
        userName: String,
        perPage: Int,
        page: Int
    ): UserResponse {
        return remoteDataSource.getSearchUsers(userName,perPage,page)
    }

    override suspend fun getDetails(
        userName: String,
    ): DetailsResponse {
        return remoteDataSource.getDetails(userName)
    }

    override suspend fun getRepository(
        userName: String,
        perPage : Int
    ): RepositoryResponse {
        return remoteDataSource.getRepository(userName,perPage)
    }

    override suspend fun getAllFavorite(): List<Favorite> {
        return localDataSource.getAllFavorite()
    }

    override suspend fun insertFavorite(user: Favorite) {
        return localDataSource.insertFavorite(user)
    }

    override suspend fun deleteFavorite(user: Favorite) {
        return localDataSource.deleteFavorite(user)
    }

}