package com.affan.githubuserapp.main.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.databinding.ActivitySearchBinding
import com.affan.githubuserapp.di.ViewModelFactory
import com.affan.githubuserapp.main.search.adapter.SearchAdapter
import com.affan.githubuserapp.main.search.viewmodel.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var searchAdapter: SearchAdapter

    private lateinit var mLayoutManager : LinearLayoutManager

//    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance
        )[SearchViewModel::class.java]

        mLayoutManager = LinearLayoutManager(this)


        setAdapter()
        getObserve()

        binding.skLoadingListUser.visibility = View.GONE
        binding.ivSearch.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    setUserName(5000)
                }
            }
        }

    }

    private suspend fun setUserName(interval : Long){
//        isLoading = true
        val userName = binding.edtSearch.text.toString()
        if (userName.isEmpty()) return

        for (i in 1..10){
            searchViewModel.getUsersSearch(userName,i)
            delay(interval)
        }
        Log.d("what if itemCount",mLayoutManager.itemCount.toString())
    }

    private fun setAdapter() {
        binding.rvListUsers.setHasFixedSize(true)
        searchAdapter = SearchAdapter()
        binding.rvListUsers.adapter = searchAdapter
//        val mLayoutManager = LinearLayoutManager(this)
        binding.rvListUsers.layoutManager = mLayoutManager
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
//                Log.d("what if childCount",recyclerView.childCount.toString())
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
////                    },2000)
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

        searchViewModel.isLoading.observe(this){ isLoading ->
//            if (isLoading == true){
//                binding.rvListUsers.visibility = View.GONE
//                binding.skLoadingListUser.visibility = View.VISIBLE
//            } else {
//                binding.rvListUsers.visibility = View.VISIBLE
//                binding.skLoadingListUser.visibility = View.GONE
//            }
        }

        searchViewModel.users.observe(this){ data ->
            searchAdapter.setData(data)
        }

        searchViewModel.error.observe(this){error ->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
            Log.d("SearchActivity",error)
        }

    }

}