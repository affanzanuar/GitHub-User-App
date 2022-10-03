package com.affan.githubuserapp.data

import com.affan.githubuserapp.data.model.user.User

interface DataSource {

    fun getSearchUsers (
        userName : String,
        perPage : Int,
        page : Int
    ) : User

}