package com.affan.githubuserapp.main.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.affan.githubuserapp.data.model.user.User
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
        getObserve()

        binding.skLoadingListUser.visibility = View.GONE
        binding.ivSearch.setOnClickListener {
            setUserName()
        }

    }

    private fun setUserName(){
        val userName = binding.edtSearch.text.toString()
        if (userName.isEmpty()) return
        searchViewModel.getUsersSearch(userName)
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
                binding.skLoadingListUser.visibility = View.VISIBLE
            } else {
                binding.rvListUsers.visibility = View.VISIBLE
                binding.skLoadingListUser.visibility = View.GONE
            }
        }

        searchViewModel.users.observe(this){ data ->
            searchAdapter.setData(data as List<User>)
        }

        searchViewModel.error.observe(this){error ->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
            Log.d("SearchActivity",error)
        }

    }

}