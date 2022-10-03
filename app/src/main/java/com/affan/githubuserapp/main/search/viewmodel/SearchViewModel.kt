package com.affan.githubuserapp.main.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.domain.Repository

class SearchViewModel (
    private val repository: Repository
        ) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _users = MutableLiveData<List<User>>()
    val users : LiveData<List<User>> = _users


}