package com.affan.githubuserapp.data

import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse

interface DataSource {

    suspend fun getSearchUsers (
        userName : String,
        perPage : Int,
        page : Int
    ) : UserResponse

}