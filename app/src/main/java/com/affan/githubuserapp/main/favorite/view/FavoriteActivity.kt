package com.affan.githubuserapp.main.favorite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.githubuserapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}