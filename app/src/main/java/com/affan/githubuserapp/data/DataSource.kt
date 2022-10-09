package com.affan.githubuserapp.data

import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.repository.RepositoryResponse
import com.affan.githubuserapp.data.model.user.UserResponse

interface DataSource {

    suspend fun getSearchUsers (
        userName : String,
        perPage : Int,
        page : Int
    ) : UserResponse

    suspend fun getDetails (
        userName: String
    ): DetailsResponse

    suspend fun getRepository (
        userName: String,
        perPage : Int
    ): RepositoryResponse

}