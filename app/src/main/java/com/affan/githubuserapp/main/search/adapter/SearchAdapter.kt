package com.affan.githubuserapp.main.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.R
import com.affan.githubuserapp.data.model.user.User
import com.affan.githubuserapp.databinding.ContainerListUserBinding
import com.bumptech.glide.Glide

class SearchAdapter (
    private val onClickUser : (data : User) -> Unit
        ) : RecyclerView.Adapter<SearchAdapter.SearchViewModel>() {

    private val itemList = mutableListOf<User?>()

    inner class SearchViewModel (val binding: ContainerListUserBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind ( item : User) {
                Glide.with(binding.root)
                    .load(item.avatarUrl)
                    .placeholder(R.drawable.ic_default_profil_picture)
                    .circleCrop()
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
        itemList[position]?.let { holder.bind(it) }

        holder.binding.root.setOnClickListener {
            itemList[position]?.let { it1 -> onClickUser(it1) }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<User?>){
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData (){
        itemList.clear()
        notifyDataSetChanged()
    }
}