package com.example.libraryapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryapp.adapter.CollectionAdapter
import com.example.libraryapp.databinding.FragmentCollectionBinding
import kotlinx.coroutines.launch

class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var collectionDao: CollectionDao // Tambahkan ini

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectionDao = (requireActivity() as DashboardActivity).database.collectionDao() // Inisialisasi collectionDao

        setupRecyclerView()
        loadData()
        setupSearch()
        setupCreateCollection()
    }


    private fun setupRecyclerView() {
        collectionAdapter = CollectionAdapter { collectionId ->
            // Handle click di sini, misalnya navigasi ke detail collection
            Toast.makeText(requireContext(), "Collection with ID $collectionId clicked", Toast.LENGTH_SHORT).show()
        }
        binding.collectionRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = collectionAdapter
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                val collections = collectionDao.getAllCollections()
                collectionAdapter.submitList(collections)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("CollectionFragment", "Error loading collections", e)
            }
        }
    }

    private fun setupSearch(){
        binding.searchBar.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.search_feature_not_available), Toast.LENGTH_SHORT).show()
        }
    }
    private fun setupCreateCollection() {
        binding.createCollectionButton.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.create_collection_feature_not_available), Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}