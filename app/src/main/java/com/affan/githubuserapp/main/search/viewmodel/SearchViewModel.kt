package com.affan.githubuserapp.main.search.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse
import com.affan.githubuserapp.data.util.Utility
import com.affan.githubuserapp.domain.Repository
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notifyAll
import retrofit2.Response

class SearchViewModel (
    private val repository: Repository
        ) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _users = MutableLiveData<List<User?>>()
    val users : LiveData<List<User?>> = _users
    var usersSearchPage = 1
    var usersSearchResponse : UserResponse? = null

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getUsersSearch (userName : String) {
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getSearchUsers(Utility.TOKEN,userName,100,usersSearchPage)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    usersSearchPage++
                    Log.e("what VM usersSearchPage 44", usersSearchPage.toString())
                    if (usersSearchResponse == null) {
                        usersSearchResponse = data
                    } else {
                        val oldPage = usersSearchResponse?.items
                        val newPage = data.items
                        oldPage?.addAll(newPage!!)
                        Log.e("what old Page", oldPage.toString())
                        Log.e("what new Page", newPage.toString())
                    }
                    Log.e("what VM usersSearchPage 51", usersSearchPage.toString())
                    _isLoading.value = false
                    _users.value = data.items!!
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _isLoading.value = false
                    _error.value = error.message
                }
            }
        }
    }

//    private fun handlerSearchResponse (response: Response<UserResponse>) : Resource<UserResponse>{
//        if (response.isSuccessful){
//            response.body()?.let { data ->
//                usersSearchPage++
//                return Resource.
//
//            }
//        }
//    }

}