package com.affan.githubuserapp.main.favorite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.githubuserapp.databinding.ActivityFavoriteBinding
import com.affan.githubuserapp.main.favorite.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    fun getObserve () {
    }

}