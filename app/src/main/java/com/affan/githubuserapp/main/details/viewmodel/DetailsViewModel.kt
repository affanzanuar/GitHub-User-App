package com.affan.githubuserapp.main.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.githubuserapp.data.model.details.DetailsResponse
import com.affan.githubuserapp.data.model.repository.RepositoryResponse
import com.affan.githubuserapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel (private val repository : Repository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _usersDetails = MutableLiveData<DetailsResponse?>()
    val usersDetails : LiveData<DetailsResponse?> = _usersDetails

    private val _usersRepository = MutableLiveData<RepositoryResponse?>()
    val userRepository : LiveData<RepositoryResponse?> = _usersRepository

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getDetails (userName : String){
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getDetails(userName)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _usersDetails.value = data
                    _isLoading.value = false
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _error.value = error.message
                    _isLoading.value = false
                }
            }
        }
    }

    fun getRepository (userName : String) {
        viewModelScope.launch {
            runCatching {
                _isLoading.value = true
                withContext(Dispatchers.IO){
                    repository.getRepository(userName,100)
                }
            }.onSuccess { data ->
                withContext(Dispatchers.Main){
                    _usersRepository.value = data
                    _isLoading.value = false
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _error.value = error.message
                    _isLoading.value = false
                }
            }
        }
    }

}