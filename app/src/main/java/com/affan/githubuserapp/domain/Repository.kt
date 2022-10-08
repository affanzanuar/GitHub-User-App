package com.affan.githubuserapp.domain

import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse

interface Repository {

    suspend fun getSearchUsers (
        token : String,
        userName : String,
        perPage : Int,
        page : Int
    ) : UserResponse

    suspend fun getDetails (
        userName: String,
        token: String
    ): DetailsResponse

}