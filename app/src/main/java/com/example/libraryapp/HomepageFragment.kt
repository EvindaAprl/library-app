package com.example.libraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryapp.adapter.BookAdapter
import com.example.libraryapp.databinding.FragmentHomepageBinding
import com.example.libraryapp.model.Book

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!
    private lateinit var continueReadingAdapter: BookAdapter
    private lateinit var newBooksAdapter: BookAdapter
    private lateinit var recommendationAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        loadData()
        setupSearch()
    }
    private fun setupRecyclerViews(){
        // Inisialisasi RecyclerView Lanjutkan Membaca
        continueReadingAdapter = BookAdapter(activity as BookAdapter.OnBookClickListener)
        binding.continueReadingRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = continueReadingAdapter
        }


        // Inisialisasi RecyclerView Terbaru
        newBooksAdapter = BookAdapter(activity as BookAdapter.OnBookClickListener)
        binding.newBooksRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newBooksAdapter
        }

        // Inisialisasi RecyclerView Rekomendasi
        recommendationAdapter = BookAdapter(activity as BookAdapter.OnBookClickListener)
        binding.recommendationRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendationAdapter
        }
    }


    private fun loadData() {
        // Data dummy untuk Lanjutkan Membaca
        val continueReadingList = listOf(
            Book(id = 1, title = "Cantik Itu Luka", author = "Eka Kurniawan", synopsis = "", coverPath = "", queueCount = 1, availableCount = 1),
            Book(id = 2, title = "Filosofi Teras", author = "Henry Manampiring", synopsis = "", coverPath = "", queueCount = 1, availableCount = 1),
        )
        continueReadingAdapter.submitList(continueReadingList)

        // Data dummy untuk Terbaru
        val newBooksList = listOf(
            Book(id = 3, title = "Detektif Conan 104", author = "Gosho Aoyama", synopsis = "Kematian misterius \"Koji Haneda\", seorang pemain shogi jenius. Kebenaran di balik kasus yang lama terpendam.", coverPath = "", queueCount = 1, availableCount = 1),
            Book(id = 4, title = "The Architecture of Love", author = "Ika Natassa", synopsis = "Mengisahkan tentang kehidupan percintaan penulis muda, Raia Risjad, yang selalu menghantui.", coverPath = "", queueCount = 1, availableCount = 1),

            )
        newBooksAdapter.submitList(newBooksList)


        // Data dummy untuk Rekomendasi
        val recommendationList = listOf(
            Book(id = 1, title = "Cantik Itu Luka", author = "Eka Kurniawan", synopsis = "", coverPath = "", queueCount = 1, availableCount = 1),
            Book(id = 2, title = "Filosofi Teras", author = "Henry Manampiring", synopsis = "", coverPath = "", queueCount = 1, availableCount = 1),
            Book(id = 5, title = "Negeri 5 Menara", author = "A. Fuadi", synopsis = "", coverPath = "", queueCount = 1, availableCount = 1)
        )
        recommendationAdapter.submitList(recommendationList)
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