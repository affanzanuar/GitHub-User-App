package com.affan.githubuserapp.main.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.data.model.favorite.Favorite
import com.affan.githubuserapp.databinding.ContainerListFavoriteBinding
import com.bumptech.glide.Glide

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val itemFavorite = mutableListOf<Favorite?>()

    inner class FavoriteViewHolder (val binding: ContainerListFavoriteBinding)
        : RecyclerView.ViewHolder (binding.root) {
            fun bind (item : Favorite){
                Glide.with(binding.root)
                    .load(item.avatarUrl)
                    .into(binding.ivProfilePicture)

                binding.tvUserNameList.text = item.login
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ContainerListFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        itemFavorite[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return itemFavorite.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<Favorite?>) {
        itemFavorite.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData () {
        itemFavorite.clear()
        notifyDataSetChanged()
    }
}