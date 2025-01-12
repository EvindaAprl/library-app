package com.example.libraryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.databinding.ItemSocialBinding
import com.example.libraryapp.model.SocialPost

class SocialAdapter : ListAdapter<SocialPost, SocialAdapter.SocialViewHolder>(SocialDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        val binding = ItemSocialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SocialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        val socialPost = getItem(position)
        holder.bind(socialPost)
    }

    inner class SocialViewHolder(private val binding: ItemSocialBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(socialPost: SocialPost) {
            binding.tvUserName.text = socialPost.userName
            binding.tvTime.text = socialPost.time
            binding.tvPostText.text = socialPost.postText
            binding.tvBookTitle.text = socialPost.bookTitle
            binding.tvLikeCount.text = socialPost.likeCount.toString()
            binding.tvCommentCount.text = socialPost.commentCount.toString()

            // load image profile
        }
    }

    class SocialDiffCallback: DiffUtil.ItemCallback<SocialPost>() {
        override fun areItemsTheSame(oldItem: SocialPost, newItem: SocialPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SocialPost, newItem: SocialPost): Boolean {
            return oldItem == newItem
        }

    }
}