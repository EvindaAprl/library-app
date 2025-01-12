package com.example.libraryapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.libraryapp.databinding.FragmentDetailBookBinding
import com.example.libraryapp.model.Book
import java.io.File
import java.io.FileNotFoundException

class DetailBookFragment : Fragment() {

    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!

    private var bookId: Int? = null
    private lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve bookId from arguments
        bookId = arguments?.getInt("BOOK_ID")

        // Initialize UI elements
        setupListeners()
        loadBookDetails()

    }


    private fun setupListeners() {
        // Back button
        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        // Bookmark button
        binding.btnBookmark.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.bookmarked), Toast.LENGTH_SHORT).show()
        }

        // Favorite button
        binding.btnFavorite.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.added_to_favorites), Toast.LENGTH_SHORT).show()
        }

        // Action button (e.g., queue the book)
        binding.btnAction.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.added_to_queue), Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadBookDetails() {
        bookId?.let {
            // Retrieve book details from database using bookId
            val bookFromDb = getBookFromDatabase(it)
            this.book = bookFromDb

            binding.tvBookTitle.text = book.title
            binding.tvBookAuthor.text = getString(R.string.author_prefix, book.author)
            binding.tvQueueCount.text = getString(R.string.queue_count, book.queueCount)
            binding.tvAvailableCount.text = getString(R.string.available_count, book.availableCount)
            binding.tvSynopsis.text = book.synopsis


            // Load book cover
            try {
                val coverFile = File(book.coverPath)
                if (coverFile.exists()) {
                    val bitmap = BitmapFactory.decodeFile(coverFile.absolutePath) // decode bitmap
                    if (bitmap != null) {
                        binding.ivBookCover.setImageBitmap(bitmap)
                    } else {
                        binding.ivBookCover.setImageResource(R.drawable.ic_book) // set placeholder gambar
                        Toast.makeText(requireContext(), getString(R.string.error_decode_file), Toast.LENGTH_SHORT).show() // feedback error
                    }
                } else {
                    binding.ivBookCover.setImageResource(R.drawable.ic_book) // set placeholder gambar
                    Toast.makeText(requireContext(), getString(R.string.file_not_found), Toast.LENGTH_SHORT).show() // feedback error
                }
            } catch (e: Exception) {
                binding.ivBookCover.setImageResource(R.drawable.ic_book) // set placeholder gambar
                Toast.makeText(requireContext(), getString(R.string.error_load_image), Toast.LENGTH_SHORT).show() // feedback error
            }

        }  ?: run {
            // Handle case where book id is null
            Toast.makeText(requireContext(), getString(R.string.book_not_found_null), Toast.LENGTH_SHORT).show()
            // You can also clear view and handle the logic when bookId is null
        }
    }

    private fun getBookFromDatabase(bookId: Int): Book {
        // Simulate a database query
        // Replace this with actual Room database query or other local database logic
        return Book(
            id = bookId,
            title = "Contoh Judul Buku",
            author = "Penulis Contoh",
            synopsis = "Ini adalah sinopsis dari buku yang sangat menarik dan informatif.",
            coverPath = "/path/to/book_cover.jpg",
            queueCount = 251,
            availableCount = 5
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}