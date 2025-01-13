package com.example.libraryapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.libraryapp.databinding.FragmentDetailBookBinding
import com.example.libraryapp.model.Book
import java.io.File

class DetailBookFragment : Fragment() {

    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!

    private var bookId: Int? = null
    private lateinit var book: Book
    private var isBorrowed: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookId = arguments?.getInt("BOOK_ID")

        setupListeners()
        loadBookDetails()

    }


    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.btnBookmark.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.bookmarked), Toast.LENGTH_SHORT).show()
        }

        binding.btnFavorite.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.added_to_favorites), Toast.LENGTH_SHORT).show()
        }

        binding.btnAction.setOnClickListener {
            isBorrowed = !isBorrowed
            updateButtonState()

            val message = if(isBorrowed) getString(R.string.added_to_queue) else getString(R.string.remove_from_queue)
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

        }
    }

    private fun loadBookDetails() {
        bookId?.let {
            val bookFromDb = getBookFromDatabase(it)
            this.book = bookFromDb

            binding.tvBookTitle.text = book.title
            binding.tvBookAuthor.text = getString(R.string.author_prefix, book.author)
            binding.tvQueueCount.text = getString(R.string.queue_count, book.queueCount)
            binding.tvAvailableCount.text = getString(R.string.available_count, book.availableCount)
            binding.tvSynopsis.text = book.synopsis


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
            updateButtonState()

        }  ?: run {
            Toast.makeText(requireContext(), getString(R.string.book_not_found_null), Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateButtonState() {
        if (isBorrowed) {
            binding.btnAction.text = getString(R.string.button_pinjam)
            binding.btnAction.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.orange)
        } else {
            binding.btnAction.text = getString(R.string.button_antri)
            binding.btnAction.backgroundTintList = ContextCompat.getColorStateList(requireContext(),R.color.green)
        }
    }
    private fun getBookFromDatabase(bookId: Int): Book {
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