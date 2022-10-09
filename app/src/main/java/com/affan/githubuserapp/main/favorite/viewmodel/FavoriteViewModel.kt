package com.affan.githubuserapp.main.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.githubuserapp.data.model.favorite.Favorite
import com.affan.githubuserapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel (private val repository : Repository)
    : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _watchList = MutableLiveData<List<Favorite?>>()
    val watchList : LiveData<List<Favorite?>> = _watchList

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getAllWatchList(){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getAllFavorite()
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _watchList.value = data
                    _isLoading.value = false
                }
            }.onFailure { error ->
                _error.value = error.message
                _isLoading.value = false
            }
        }
    }

}