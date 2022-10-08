package com.affan.githubuserapp.main.details.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.affan.githubuserapp.R
import com.affan.githubuserapp.databinding.ActivityDetailsBinding
import com.affan.githubuserapp.di.ViewModelFactory
import com.affan.githubuserapp.main.details.viewmodel.DetailsViewModel
import com.affan.githubuserapp.main.search.view.SearchActivity
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance
        )[DetailsViewModel::class.java]

        getObserve()

        binding.ivBack.setOnClickListener {
            finish()
        }

        val userName = intent.getStringExtra(SearchActivity.EXTRAS_DATA_USERNAME)

        viewModel.getDetails(userName!!)

    }

    private fun getObserve () {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.ivProfilePicture.visibility = View.GONE
                binding.tvName.visibility = View.GONE
                binding.tvUserName.visibility = View.GONE
                binding.ivLink.visibility = View.GONE
                binding.tvBlog.visibility = View.GONE
                binding.ivFollow.visibility = View.GONE
                binding.tvFollowers.visibility = View.GONE
                binding.tvFollowing.visibility = View.GONE
                binding.tvTotalFollowers.visibility = View.GONE
                binding.tvTotalFollowing.visibility = View.GONE
                binding.cvBlog.visibility = View.GONE
                binding.cvProfile.visibility = View.GONE
                binding.cvRepo.visibility = View.GONE
            } else {
                binding.ivProfilePicture.visibility = View.VISIBLE
                binding.tvName.visibility = View.VISIBLE
                binding.tvUserName.visibility = View.VISIBLE
                binding.ivLink.visibility = View.VISIBLE
                binding.tvBlog.visibility = View.VISIBLE
                binding.ivFollow.visibility = View.VISIBLE
                binding.tvFollowers.visibility = View.VISIBLE
                binding.tvFollowing.visibility = View.VISIBLE
                binding.tvTotalFollowers.visibility = View.VISIBLE
                binding.tvTotalFollowing.visibility = View.VISIBLE
                binding.cvBlog.visibility = View.VISIBLE
                binding.cvProfile.visibility = View.VISIBLE
                binding.cvRepo.visibility = View.VISIBLE
            }
        }

        viewModel.usersDetails.observe(this){ data ->

            Glide.with(this)
                .load(data?.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_default_profile_details)
                .into(binding.ivProfilePicture)

            binding.tvName.text = data?.name
            binding.tvUserName.text = data?.login

            if (data?.blog!!.isNotEmpty()){
                binding.tvBlog.text = data.blog
            } else {
                binding.tvBlog.text = getString(R.string.empty_blog)
            }

            binding.tvTotalFollowing.text = data.following.toString()
            binding.tvTotalFollowers.text = data.followers.toString()
            binding.tvTotalRepo.text = data.publicRepos.toString()
        }
    }


}