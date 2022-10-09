package com.affan.githubuserapp.data.remote

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.favorite.Favorite
import com.affan.githubuserapp.data.model.repository.RepositoryResponse
import com.affan.githubuserapp.data.model.user.UserResponse

class RemoteDataSource(
    private val apiService: ApiService
) : DataSource {

    override suspend fun getSearchUsers(
        userName: String,
        perPage: Int,
        page: Int
    ): UserResponse {
        return apiService.getSearchUsers(userName,perPage,page)
    }

    override suspend fun getDetails(
        userName: String
    ): DetailsResponse {
        return apiService.getDetails(userName)
    }

    override suspend fun getRepository(
        userName: String,
        perPage : Int
    ): RepositoryResponse {
        return apiService.getRepository(userName, perPage)
    }

    override suspend fun getAllFavorite(): List<Favorite> {
        throw UnsupportedOperationException("User Local Data Source")
    }

    override suspend fun insertFavorite(user: Favorite) {
        throw UnsupportedOperationException("User Local Data Source")
    }

    override suspend fun deleteFavorite(user: Favorite) {
        throw UnsupportedOperationException("User Local Data Source")
    }

}