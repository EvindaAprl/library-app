package com.example.libraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide
import com.example.libraryapp.databinding.FragmentDetailBookBinding

class DetailBookFragment : Fragment() {

    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookDao: BookDao

    private var bookId: Int? = null
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

        bookDao = (requireActivity() as DashboardActivity).database.bookDao()

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
        bookId?.let { bookId ->
            lifecycleScope.launch {
                try {
                    // Ambil data buku dari database berdasarkan bookId
                    val book = bookDao.getBookById(bookId)

                    if (book != null) {
                        // Tampilkan data buku di UI
                        binding.tvBookTitle.text = book.title
                        binding.tvBookAuthor.text = getString(R.string.author_prefix, book.author)
                        binding.tvQueueCount.text = getString(R.string.queue_count, book.queueCount)
                        binding.tvAvailableCount.text = getString(R.string.available_count, book.availableCount)
                        binding.tvSynopsis.text = book.synopsis

                        // Handling Cover Image
                        if (!book.coverPath.isNullOrEmpty()) {
                            Glide.with(requireContext())
                                .load(book.coverPath)
                                .into(binding.ivBookCover)
                        } else {
                            binding.ivBookCover.setImageResource(R.drawable.ic_book)
                        }
                    } else {
                        // Buku tidak ditemukan
                        Toast.makeText(requireContext(), getString(R.string.book_not_found), Toast.LENGTH_SHORT).show()
                        activity?.onBackPressedDispatcher?.onBackPressed()
                    }
                } catch (e: Exception) {
                    // Tangani error jika terjadi masalah dengan database
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("DetailBookFragment", "Error loading book details", e)
                }
            }
        } ?: run {
            Toast.makeText(requireContext(), getString(R.string.book_not_found_null), Toast.LENGTH_SHORT).show()
            activity?.onBackPressedDispatcher?.onBackPressed()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}