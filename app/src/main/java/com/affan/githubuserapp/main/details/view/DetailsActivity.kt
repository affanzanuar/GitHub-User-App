package com.affan.githubuserapp.main.details.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.githubuserapp.databinding.ActivityDetailsBinding
import com.affan.githubuserapp.main.details.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}