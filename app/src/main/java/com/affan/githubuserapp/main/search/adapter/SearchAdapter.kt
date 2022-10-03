package com.affan.githubuserapp.main.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.databinding.ContainerListUserBinding
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewModel>() {

    private val itemList = mutableListOf<User>()

    inner class SearchViewModel (private val binding: ContainerListUserBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind ( item : User) {
                Glide.with(binding.root)
                    .load(item.avatarUrl)
                    .into(binding.ivProfilePicture)

                binding.tvUserNameList.text = item.login
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewModel {
        return SearchViewModel(ContainerListUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewModel, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<User>){
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }
}