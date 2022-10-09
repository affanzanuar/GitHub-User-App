package com.affan.githubuserapp.main.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class SearchViewModel (
    private val repository: Repository
        ) : ViewModel() {

    private val _users = MutableLiveData<List<User?>>()
    val users : LiveData<List<User?>> = _users

    var usersSearchPage = 1

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getUsersSearch (userName : String) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO){
                    repository.getSearchUsers(userName,100,usersSearchPage)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _users.value = data.items!!
                    usersSearchPage++
                    Log.e("what VM usersSearchPage 44", usersSearchPage.toString())
                    Log.e("what VM usersSearchPage 51", usersSearchPage.toString())

                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _error.value = error.message
                }
            }
        }
    }

}