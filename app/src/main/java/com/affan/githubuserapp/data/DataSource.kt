package com.affan.githubuserapp.data

import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse

interface DataSource {

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