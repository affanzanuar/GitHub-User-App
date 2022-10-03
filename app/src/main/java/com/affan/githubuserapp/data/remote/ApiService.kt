package com.affan.githubuserapp.data.remote

import com.affan.githubuserapp.data.model.user.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    suspend fun getSearchUsers (
        @Query("q") userName : String,
        @Query("per_page") perPage : Int,
        @Query("page") page : Int
    ) : List<User>

}