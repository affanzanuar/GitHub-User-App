package com.affan.githubuserapp.data

import com.affan.githubuserapp.data.model.user.User

interface DataSource {

    suspend fun getSearchUsers (
        userName : String,
        perPage : Int,
        page : Int
    ) : List<User>

}