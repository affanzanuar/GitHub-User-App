package com.affan.githubuserapp.main.search.viewmodel

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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _users = MutableLiveData<List<User?>>()
    val users : LiveData<List<User?>> = _users

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getUsersSearch (userName : String, page : Int) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.Main){
                    _isLoading.value = true
                    repository.getSearchUsers("token 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",userName,100,page).items
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _isLoading.value = false
                    _users.value = data
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _isLoading.value = false
                    _error.value = error.message
                }
            }
        }
    }

}