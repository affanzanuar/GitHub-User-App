package com.affan.githubuserapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.affan.githubuserapp.data.remote.ApiService
import com.affan.githubuserapp.data.remote.RemoteDataSource
import com.affan.githubuserapp.domain.Repository
import com.affan.githubuserapp.domain.RepositoryImp
import com.affan.githubuserapp.main.search.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: Repository
        ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            SearchViewModel::class.java -> SearchViewModel(repository) as T
            else ->throw UnsupportedOperationException()
        }
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        private val logging : HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        private val remote : ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            retrofit.create(ApiService::class.java)
        }

        private val remoteDataSource = RemoteDataSource(remote)

        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        val getInstance = synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(
                RepositoryImp(
                    remoteDataSource
                )
            ).also { INSTANCE = it }
        }

    }
}