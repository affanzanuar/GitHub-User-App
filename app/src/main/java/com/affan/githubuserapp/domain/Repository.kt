package com.affan.githubuserapp.domain

import com.affan.githubuserapp.data.model.user.User

interface Repository {

    fun getSearchUsers (
        userName : String,
        perPage : Int,
        page : Int
    ) : User

}