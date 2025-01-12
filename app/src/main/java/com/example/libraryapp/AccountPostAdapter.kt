package com.example.libraryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.databinding.ItemAccountPostBinding
import com.example.libraryapp.model.AccountPost

class AccountPostAdapter : ListAdapter<AccountPost, AccountPostAdapter.AccountPostViewHolder>(AccountPostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountPostViewHolder {
        val binding = ItemAccountPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountPostViewHolder, position: Int) {
        val accountPost = getItem(position)
        holder.bind(accountPost)
    }

    inner class AccountPostViewHolder(private val binding: ItemAccountPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(accountPost: AccountPost) {
            binding.tvPostText.text = accountPost.postText
        }
    }
    class AccountPostDiffCallback: DiffUtil.ItemCallback<AccountPost>() {
        override fun areItemsTheSame(oldItem: AccountPost, newItem: AccountPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AccountPost, newItem: AccountPost): Boolean {
            return oldItem == newItem
        }

    }

}