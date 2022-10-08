package com.affan.githubuserapp.main.search.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AbsListView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.databinding.ActivitySearchBinding
import com.affan.githubuserapp.di.ViewModelFactory
import com.affan.githubuserapp.main.details.view.DetailsActivity
import com.affan.githubuserapp.main.search.adapter.SearchAdapter
import com.affan.githubuserapp.main.search.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private lateinit var viewModel: SearchViewModel

    private lateinit var searchAdapter: SearchAdapter

    private lateinit var mLayoutManager : LinearLayoutManager

    private var isLoading = false

    private var isLastPage = false

    private var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance
        )[SearchViewModel::class.java]

        mLayoutManager = LinearLayoutManager(this)


        setupRecyclerView()
        getObserve()

        binding.skLoadingListUser.visibility = View.GONE

        binding.ivSearch.setOnClickListener {
            searchAdapter.clearData()
            viewModel.usersSearchPage = 1
            getUserName()
        }

        binding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchAdapter.clearData()
                viewModel.usersSearchPage = 1
                getUserName()
            }
            true
        }
    }

    private fun getUserName(){
        val userName = binding.edtSearch.text.toString()
        viewModel.getUsersSearch(userName)
    }

    private fun setupRecyclerView() {
        binding.rvListUsers.setHasFixedSize(true)
        searchAdapter = SearchAdapter {data: User -> intentToDetails(data) }
        binding.rvListUsers.adapter = searchAdapter
        binding.rvListUsers.layoutManager = mLayoutManager
        binding.rvListUsers.addOnScrollListener(this.scrollListener)
    }

    private fun intentToDetails (data : User) {
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra(EXTRAS_DATA_USERNAME,data.login)
        startActivity(intent)
    }


    private fun getObserve() {
        viewModel.users.observe(this){ data ->
            searchAdapter.setData(data)
        }

        viewModel.error.observe(this){error ->
            Log.d("SearchActivity",error)
        }
    }

    private var scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val mChildCount = layoutManager.childCount
            val mItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isLastItem = firstVisibleItemPosition + mChildCount >= mItemCount
            val isNotBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = mItemCount >= 100

            val shouldPaginate = isNotLoadingAndNotLastPage &&
                    isLastItem && isNotBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate){
                val userName = binding.edtSearch.text.toString()
                viewModel.getUsersSearch(userName)
                Log.e("what shouldPaginate", "SHOULD PAGINATE")
                Log.e("what mItemCount", mItemCount.toString())
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }

    companion object {
        const val EXTRAS_DATA_USERNAME = "extras_data"
    }
}