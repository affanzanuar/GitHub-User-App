package com.affan.githubuserapp.main.favorite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.affan.githubuserapp.databinding.ActivityFavoriteBinding
import com.affan.githubuserapp.di.ViewModelFactory
import com.affan.githubuserapp.main.favorite.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[FavoriteViewModel::class.java]

        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    fun getObserve () {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading){
                binding.rvWatchList.visibility = View.GONE
                binding.skLoadingFavorite.visibility = View.VISIBLE
            } else {
                binding.rvWatchList.visibility = View.VISIBLE
                binding.skLoadingFavorite.visibility = View.GONE
            }
        }

        viewModel.error.observe(this) { error ->
            Log.e("what Favorite Activity", error)
        }

    }

}