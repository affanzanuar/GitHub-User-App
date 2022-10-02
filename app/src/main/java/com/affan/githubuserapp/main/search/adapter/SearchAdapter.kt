package com.affan.githubuserapp.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.databinding.ContainerListUserBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewModel>() {

//    private val itemList = listOf<>()

    inner class SearchViewModel (private val binding: ContainerListUserBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewModel {
        return SearchViewModel(ContainerListUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewModel, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}