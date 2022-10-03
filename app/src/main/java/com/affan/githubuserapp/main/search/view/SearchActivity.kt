package com.affan.githubuserapp.main.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.affan.githubuserapp.R
import com.affan.githubuserapp.databinding.ActivitySearchBinding
import com.affan.githubuserapp.di.ViewModelFactory
import com.affan.githubuserapp.main.search.adapter.SearchAdapter
import com.affan.githubuserapp.main.search.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance
        )[SearchViewModel::class.java]
        setAdapter()
    }

    private fun setAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvListUsers.adapter = searchAdapter
        binding.rvListUsers.layoutManager = LinearLayoutManager(this)
    }

    private fun getObserve() {

        searchViewModel.isLoading.observe(this){ isLoading ->

            if (isLoading == true){
                binding.rvListUsers.visibility = View.GONE
            } else {
                binding.rvListUsers.visibility = View.VISIBLE
            }

        }

    }

}