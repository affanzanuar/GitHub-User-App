package com.affan.githubuserapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.affan.githubuserapp.data.model.favorite.Favorite

@Database(
    entities = [Favorite::class],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao() : Favorite

    companion object {
        private var INSTANCE : FavoriteDatabase? = null
        fun getInstance (context : Context) : FavoriteDatabase {
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_db"
                ).build().also {
                    INSTANCE = it
                }
            }

        }
    }

}