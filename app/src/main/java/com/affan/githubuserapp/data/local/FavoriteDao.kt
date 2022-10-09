package com.affan.githubuserapp.data.local

import androidx.room.*
import com.affan.githubuserapp.data.model.favorite.Favorite

@Dao
interface FavoriteDao {

    @Query ("SELECT * FROM Favorite")
    suspend fun getAllFavorite () : List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite (user : Favorite)

    @Delete
    suspend fun deleteFavorite (user: Favorite)

}