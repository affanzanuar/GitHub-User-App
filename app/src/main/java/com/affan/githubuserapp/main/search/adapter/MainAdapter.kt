package com.affan.githubuserapp.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.databinding.ContainerListUserBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewModel>() {

//    private val itemList = listOf<>()

    inner class MainViewModel (private val binding: ContainerListUserBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewModel {
        return MainViewModel(ContainerListUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: MainViewModel, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}