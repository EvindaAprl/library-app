package com.example.libraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.libraryapp.adapter.BookAdapter
import com.example.libraryapp.databinding.FragmentHomepageBinding

class HomepageFragment : Fragment() {
    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!
    private lateinit var continueReadingAdapter: BookAdapter
    private lateinit var newBooksAdapter: BookAdapter
    private lateinit var recommendationAdapter: BookAdapter
    private lateinit var bookDao: BookDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get instance AppDatabase dan BookDao
        bookDao = (requireActivity() as DashboardActivity).database.bookDao()

        setupRecyclerViews()
        loadData()
        setupSearch()
    }

    // HomepageFragment.kt
    private fun setupRecyclerViews() {
        // Inisialisasi RecyclerView Lanjutkan Membaca
        continueReadingAdapter = BookAdapter(object : BookAdapter.OnBookClickListener {
            override fun onBookClick(bookId: Int) {
                navigateToDetail(bookId)
            }
        })
        binding.continueReadingRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = continueReadingAdapter
        }

        // Inisialisasi RecyclerView Terbaru
        newBooksAdapter = BookAdapter(object : BookAdapter.OnBookClickListener {
            override fun onBookClick(bookId: Int) {
                navigateToDetail(bookId)
            }
        })
        binding.newBooksRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newBooksAdapter
        }

        // Inisialisasi RecyclerView Rekomendasi
        recommendationAdapter = BookAdapter(object : BookAdapter.OnBookClickListener {
            override fun onBookClick(bookId: Int) {
                navigateToDetail(bookId)
            }
        })
        binding.recommendationRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendationAdapter
        }
    }

    private fun navigateToDetail(bookId: Int) {
        val bundle = Bundle().apply {
            putInt("BOOK_ID", bookId)
        }
        // Navigate ke DetailBookFragment dengan passing bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailBookFragment().apply {
                arguments = bundle
            })
            .addToBackStack(null)
            .commit()
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                // Ambil semua buku dari database
                val allBooks = bookDao.getAllBooks()

                // Filter buku berdasarkan ID yang telah ditentukan
                val continueReadingList = allBooks.filter { it.id % 3 == 0 }
                val newBooksList = allBooks.filter { it.id % 2 == 0 }
                val recommendationList = allBooks.filter { it.id % 5 == 0 }

                // Submit data ke adapter
                continueReadingAdapter.submitList(continueReadingList)
                newBooksAdapter.submitList(newBooksList)
                recommendationAdapter.submitList(recommendationList)


            } catch (e: Exception) {
                // Tangani error jika terjadi masalah dengan database
                Toast.makeText(requireContext(), "Error loading book data: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("HomepageFragment", "Error loading book data", e)
            }
        }
    }


    private fun setupSearch(){
        binding.searchBar.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.search_feature_not_available), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}