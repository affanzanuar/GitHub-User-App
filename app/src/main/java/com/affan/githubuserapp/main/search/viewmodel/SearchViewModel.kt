package com.affan.githubuserapp.main.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.data.model.user.UserResponse
import com.affan.githubuserapp.data.util.Utility
import com.affan.githubuserapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class SearchViewModel (
    private val repository: Repository
        ) : ViewModel() {

    private val _users = MutableLiveData<List<User?>>()
    val users : LiveData<List<User?>> = _users

    private var usersSearchPage = 1
    private var usersSearchResponse : UserResponse? = null

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getUsersSearch (userName : String) {
        viewModelScope.launch {
            runCatching {
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
                    _users.value = data.items!!
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _error.value = error.message
                }
            }
        }
    }

}