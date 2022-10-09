package com.affan.githubuserapp.data.model.favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = false)
    val id : Int?,
    val login : String?,
    val avatarUrl : String?
) : Parcelable
