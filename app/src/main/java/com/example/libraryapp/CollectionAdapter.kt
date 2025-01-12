package com.example.libraryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.databinding.ItemCollectionBinding
import com.example.libraryapp.model.Collection

class CollectionAdapter : ListAdapter<Collection, CollectionAdapter.CollectionViewHolder>(CollectionDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collection = getItem(position)
        holder.bind(collection)
    }

    inner class CollectionViewHolder(private val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(collection: Collection) {
            binding.tvCollectionTitle.text = collection.title
            binding.tvBookCount.text = "${collection.bookCount} buku"
            // Tambahkan logic untuk load image cover koleksi (jika ada)
            // binding.ivCollectionCover.setImageResource(collection.coverImageResId)
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