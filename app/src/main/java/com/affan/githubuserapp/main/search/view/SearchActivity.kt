package com.affan.githubuserapp.main.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.databinding.ActivitySearchBinding
import com.affan.githubuserapp.di.ViewModelFactory
import com.affan.githubuserapp.main.search.adapter.SearchAdapter
import com.affan.githubuserapp.main.search.viewmodel.SearchViewModel
import kotlinx.coroutines.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchAdapter: SearchAdapter

    private lateinit var mLayoutManager : LinearLayoutManager

//    private var page = 1

//
//    private var prevTotal = 0
//
//    private var sHold = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance
        )[SearchViewModel::class.java]

        mLayoutManager = LinearLayoutManager(this)


        setupRecyclerView()
        getObserve()

        binding.skLoadingListUser.visibility = View.GONE
        binding.ivSearch.setOnClickListener {
            searchAdapter.clearData()
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    setUserName()
                }
            }
//            val userName = binding.edtSearch.text.toString()
//            searchViewModel.getUsersSearch(userName)
        }
//        var job : Job? = null
//        binding.edtSearch.addTextChangedListener { editable ->
//            job?.cancel()
//            job = MainScope().launch {
//                delay(5500)
//                editable?.let {
//                    if (editable.toString().isNotEmpty()) {
//                        searchViewModel.getUsersSearch(editable.toString())
//                    }
//                }
//            }
//        }
    }

    private fun setUserName(){

        val userName = binding.edtSearch.text.toString()
//        if (userName.isEmpty()) return
        searchViewModel.getUsersSearch(userName)

//        for (i in 1..10){
//        isLoading = false
//        Log.d("what setUserNamePage", page.toString())
//            Log.d("what if Loop itemCount",mLayoutManager.itemCount.toString())
//        }
//        Log.d("what if itemCount",mLayoutManager.itemCount.toString())
    }


    private var isLoading = false

    private var isLastPage = false

    private var isScrolling = false

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

            val userName = binding.edtSearch.text.toString()

            if (shouldPaginate){
                searchViewModel.getUsersSearch(userName)
                Log.e("what shouldPaginae", "SHOULD PAGINATE")
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

    private fun setupRecyclerView() {
        binding.rvListUsers.setHasFixedSize(true)
        searchAdapter = SearchAdapter()
        binding.rvListUsers.adapter = searchAdapter
//        val mLayoutManager = LinearLayoutManager(this)
        binding.rvListUsers.layoutManager = mLayoutManager
        binding.rvListUsers.addOnScrollListener(this.scrollListener)

//        binding.rvListUsers.addOnScrollListener(object  : RecyclerView.OnScrollListener(){
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val mChildCount = mLayoutManager.childCount
//                val mFirstComplete = mLayoutManager.findFirstVisibleItemPosition()
//                val mItemCount = mLayoutManager.itemCount
//
//                Log.d("what if DX DX DX",dx.toString())
//                Log.d("what if DY DY DY",dy.toString())
//                Log.d("what if mChildCount",mLayoutManager.childCount.toString())
//                Log.d("what if mFirstComplete",mLayoutManager.findFirstCompletelyVisibleItemPosition().toString())
//                Log.d("what if mItemCount",mLayoutManager.itemCount.toString())
//                val userName = binding.edtSearch.text.toString()
//
////                for (i in 2..10){
////
////                }
////                if (mChildCount + mFirstComplete >= mItemCount && dy>=0){
////                    for (i in 1..10) {
//                        if (dy>0){
////                            if (mChildCount + mFirstComplete >= mItemCount){
////                                val mPage = page + i
////                                Log.e("what if mPAGE",mPage.toString())
////                                break
////                            }
////                            if (isLoading){
////                                if (mItemCount>prevTotal){
////                                    isLoading = false
////                                    prevTotal = mItemCount
////                                }
////                            }
//                            Log.e("what if isLoading b120",isLoading.toString())
//                            if (!isLoading){
//                                if ((mItemCount-mChildCount) <= (mFirstComplete +sHold)){
//                                    lifecycleScope.launch {
//                                        withContext(Dispatchers.IO){
//                                            page++
//                                            setUserName(page,3500)
//                                        }
//                                    }
//                                    isLoading = true
//                                    Log.e("what if b130 mPAGE",page.toString())
//                                }
//                            }
////                            Log.e("what if mPAGE",page.toString())
//                        }
////                isLoading = false
////                    }
////                    page++
////                    Log.d("what if PAGE",page.toString())
////                    searchViewModel.getUsersSearch(userName,page)
////                    break
////                }
//
////                if (dy > 0) {
////                    if (mChildCount + mFirstComplete >= mItemCount){
////                        page++
////                        searchViewModel.getUsersSearch(userName,page)
////                    }
////                }
//
//
//            }
//        })

//        if (mLayoutManager.itemCount == 100){
//            lifecycleScope.launch {
//                withContext(Dispatchers.IO){
//                    getUserPageSearch(5000)
//                }
//            }
//        }
//        binding.rvListUsers.addOnScrollListener(object  : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                Log.d("what if DX DX DX",dx.toString())
//                Log.d("what if DY DY DY",dy.toString())
//                Log.d("what if scrollState",recyclerView.scrollState.toString())
//                Log.d("what if itemDecorationCount",recyclerView.itemDecorationCount.toString())
//                Log.d("what if childCount",mLayoutManager.childCount.toString())
//                Log.d("what if findFirstCompletelyVisibleItemPosition",mLayoutManager.findFirstCompletelyVisibleItemPosition().toString())
//                Log.d("what if itemCount",mLayoutManager.itemCount.toString())
//                Log.d("what if recycleChildrenOnDetach",mLayoutManager.recycleChildrenOnDetach.toString())
//                Log.d("what if initialPrefetchItemCount",mLayoutManager.initialPrefetchItemCount.toString())
////                Log.d("what if initialPrefetchItemCount",mLayoutManager.)
//                val userName = binding.edtSearch.text.toString()
//                val mItem = mLayoutManager.childCount
//                val lItem = mLayoutManager.findFirstCompletelyVisibleItemPosition()
//                val count = mLayoutManager.itemCount
////                if (mItem+lItem == count){
////                    Handler().postDelayed({
////                        searchViewModel.run { getUsersSearch(userName,2) }
////                    },5000)
////                        searchViewModel.getUsersSearch(userName,2)
////                    lifecycleScope.launch {
////                        withContext(Dispatchers.IO){
////                            getUserPageSearch(5000)
////                                                    }
////                    }
////                }
////                if (mLayoutManager.itemCount == 200){
////                    Handler().postDelayed({
////                        searchViewModel.run { getUsersSearch(userName,3) }
////                    },2000)
//////                    searchViewModel.getUsersSearch(userName,3)
////                }
////                if (mLayoutManager.findFirstVisibleItemPosition() == 250){
////                    val userName = binding.edtSearch.text.toString()
////                    searchViewModel.getUsersSearch(userName,4)
////                }
//            }
//        })
    }

//    private suspend fun getUserPageSearch(interval : Long){
//        delay(interval)
//        val userName = binding.edtSearch.text.toString()
////        for (i in 2..10){
//            searchViewModel.getUsersSearch(userName,2)
////        }
//    }

    private fun getObserve() {

//        searchViewModel.isLoading.observe(this){ isLoading ->
////            if (isLoading == true){
////                binding.rvListUsers.visibility = View.GONE
////                binding.skLoadingListUser.visibility = View.VISIBLE
////            } else {
////                binding.rvListUsers.visibility = View.VISIBLE
////                binding.skLoadingListUser.visibility = View.GONE
////            }
//        }

        searchViewModel.users.observe(this){ data ->
//            searchAdapter.clearData()
            searchAdapter.setData(data)
//            val totalPages = 12
//            isLastPage = searchViewModel.usersSearchPage == totalPages
//            if (isLastPage) {
//                binding.rvListUsers.setPadding(0,0,0,0)
//            }
        }

        searchViewModel.error.observe(this){error ->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
            Log.d("SearchActivity",error)
        }

    }

}