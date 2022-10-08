package com.affan.githubuserapp.data.remote

import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getSearchUsers (
        @Header("Authorization") token : String,
        @Query("q") userName : String,
        @Query("per_page") perPage : Int,
        @Query("page") page : Int
    ) : UserResponse

    @GET("users/{username}")
    suspend fun getDetails (
        @Path("username") userName: String,
        @Header("Authorization") token : String,
    )

}