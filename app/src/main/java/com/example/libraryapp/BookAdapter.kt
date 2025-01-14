package com.example.libraryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.util.TypedValue
import com.bumptech.glide.Glide
import com.example.libraryapp.R
import com.example.libraryapp.databinding.ItemBookBinding
import com.example.libraryapp.model.Book

class BookAdapter(private val listener: OnBookClickListener) : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {
    interface OnBookClickListener {
        fun onBookClick(bookId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    inner class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val book = getItem(position)
                    listener.onBookClick(book.id)
                }
            }
        }
        fun bind(book: Book) {
            binding.tvBookTitle.text = book.title
            binding.tvBookAuthor.text = book.author

            Glide.with(binding.root.context)
                .load(book.coverPath)
                .override(200.dpToPx(binding.root.context), 250.dpToPx(binding.root.context))
                .placeholder(R.drawable.ic_book)
                .error(R.drawable.ic_book)
                .into(binding.ivBookCover)
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    fun Int.dpToPx(context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}