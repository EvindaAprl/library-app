package com.example.libraryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.libraryapp.R
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

            // Load gambar profil pengguna (jika ada)
            Glide.with(binding.root.context)
                .load(socialPost.userProfileImage)
                .placeholder(R.drawable.ic_account) // Placeholder jika gambar tidak ada
                .error(R.drawable.ic_account) // Gambar error jika gagal memuat
                .circleCrop() // Membuat gambar menjadi lingkaran
                .into(binding.ivUserProfile)

            // Load gambar sampul buku (jika ada)
            Glide.with(binding.root.context)
                .load(socialPost.bookCoverUrl)
                .placeholder(R.drawable.ic_book) // Placeholder jika gambar tidak ada
                .error(R.drawable.ic_book) // Gambar error jika gagal memuat
                .into(binding.ivBookCover)

            // Set click listener untuk btnFollow
            binding.btnFollow.setOnClickListener {
                Toast.makeText(binding.root.context, binding.root.context.getString(R.string.follow_feature_not_available), Toast.LENGTH_SHORT).show()
            }
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