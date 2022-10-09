package com.affan.githubuserapp.data.remote

import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.repository.RepositoryResponse
import com.affan.githubuserapp.data.model.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getSearchUsers (
        @Query("q") userName : String,
        @Query("per_page") perPage : Int,
        @Query("page") page : Int
    ) : UserResponse

    @GET("users/{username}")
    suspend fun getDetails (
        @Path("username") userName: String,
    ) : DetailsResponse

    @GET("users/{username}/repos")
    suspend fun getRepository (
        @Path("username") userName: String,
    ) : RepositoryResponse

}