package com.affan.githubuserapp.data.local

import com.affan.githubuserapp.data.DataSource
import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.favorite.Favorite
import com.affan.githubuserapp.data.model.repository.RepositoryResponse
import com.affan.githubuserapp.data.model.user.UserResponse

class LocalDataSource(
    private val favoriteDatabase: FavoriteDatabase
) : DataSource {
    override suspend fun getSearchUsers(userName: String, perPage: Int, page: Int): UserResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getDetails(userName: String): DetailsResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getRepository(userName: String, perPage: Int): RepositoryResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getAllFavorite(): List<Favorite> {
        return favoriteDatabase.favoriteDao().getAllFavorite()
    }

    override suspend fun insertFavorite(user: Favorite) {
        return favoriteDatabase.favoriteDao().insertFavorite(user)
    }

    override suspend fun deleteFavorite(user: Favorite){
        return favoriteDatabase.favoriteDao().deleteFavorite(user)
    }

}