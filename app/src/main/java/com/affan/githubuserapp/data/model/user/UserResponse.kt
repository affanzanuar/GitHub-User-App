package com.affan.githubuserapp.data.model.user


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: MutableList<User?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)