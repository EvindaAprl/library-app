package com.example.libraryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.libraryapp.R
import com.example.libraryapp.databinding.ItemCollectionBinding
import com.example.libraryapp.model.Collection

class CollectionAdapter(private val onCollectionClick: (Int) -> Unit) : ListAdapter<Collection, CollectionAdapter.CollectionViewHolder>(CollectionDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collection = getItem(position)
        holder.bind(collection)
    }

    inner class CollectionViewHolder(private val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val collection = getItem(position)
                    onCollectionClick(collection.id)
                }
            }
        }

        fun bind(collection: Collection) {
            binding.tvCollectionTitle.text = collection.title
            binding.tvBookCount.text = "${collection.bookCount} buku"

            Glide.with(binding.root.context)
                .load(collection.coverPath)
                .placeholder(R.drawable.ic_book)
                .error(R.drawable.ic_book)
                .into(binding.ivCollectionCover)
        }
    }

    class CollectionDiffCallback: DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem == newItem
        }

    }
}