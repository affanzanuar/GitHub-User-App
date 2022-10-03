package com.affan.githubuserapp.data.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)