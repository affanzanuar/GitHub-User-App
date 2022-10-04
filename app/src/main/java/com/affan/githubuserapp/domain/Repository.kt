package com.affan.githubuserapp.domain

import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse

interface Repository {

    suspend fun getSearchUsers (
        userName : String,
        perPage : Int,
        page : Int
    ) : UserResponse

}