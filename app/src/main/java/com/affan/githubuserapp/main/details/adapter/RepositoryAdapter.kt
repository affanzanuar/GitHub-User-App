package com.affan.githubuserapp.main.details.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.githubuserapp.data.model.repository.RepositoryItem
import com.affan.githubuserapp.databinding.ContainerListRepositoryBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private val itemRepository = mutableListOf<RepositoryItem?>()

    inner class RepositoryViewHolder (private val binding: ContainerListRepositoryBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind (item : RepositoryItem){
                binding.tvRepoName.text = item.name

                val currentDateCreated = item.createdAt

                val yearCreated0 = currentDateCreated?.get(0)
                val yearCreated1 = currentDateCreated?.get(1)
                val yearCreated2 = currentDateCreated?.get(2)
                val yearCreated3 = currentDateCreated?.get(3)
                val yearCreated = "$yearCreated0$yearCreated1$yearCreated2$yearCreated3"

                val monthCreated0 = currentDateCreated?.get(5)
                val monthCreated1 = currentDateCreated?.get(6)
                val monthCreated = "$monthCreated0$monthCreated1"

                val dateCreated0 = currentDateCreated?.get(8)
                val dateCreated1 = currentDateCreated?.get(9)
                val dateCreated = "$dateCreated0$dateCreated1"

                binding.tvCreatedRepo.text = "$dateCreated-$monthCreated-$yearCreated"

                val currentDateUpdated = item.updatedAt

                val yearUpdated0 = currentDateUpdated?.get(0)
                val yearUpdated1 = currentDateUpdated?.get(1)
                val yearUpdated2 = currentDateUpdated?.get(2)
                val yearUpdated3 = currentDateUpdated?.get(3)
                val yearUpdated = "$yearUpdated0$yearUpdated1$yearUpdated2$yearUpdated3"

                val monthUpdated0 = currentDateUpdated?.get(5)
                val monthUpdated1 = currentDateUpdated?.get(6)
                val monthUpdated = "$monthUpdated0$monthUpdated1"

                val dateUpdated0 = currentDateUpdated?.get(8)
                val dateUpdated1 = currentDateUpdated?.get(9)
                val dateUpdated = "$dateUpdated0$dateUpdated1"
                binding.tvLastUpdateRepo.text = "$dateUpdated-$monthUpdated-$yearUpdated"
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(ContainerListRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        itemRepository[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return itemRepository.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<RepositoryItem?>){
        itemRepository.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData (){
        itemRepository.clear()
        notifyDataSetChanged()
    }
}