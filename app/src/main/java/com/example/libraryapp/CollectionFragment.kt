package com.example.libraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryapp.adapter.CollectionAdapter
import com.example.libraryapp.databinding.FragmentCollectionBinding
import com.example.libraryapp.model.Collection

class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var collectionAdapter: CollectionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadData()
        setupSearch()
        setupCreateCollection()

    }


    private fun setupRecyclerView() {
        collectionAdapter = CollectionAdapter()
        binding.collectionRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = collectionAdapter
        }
    }

    private fun loadData() {
        val dummyCollectionList = listOf(
            Collection(id = 1, title = "Favoritku", bookCount = 10, coverPath = ""),
            Collection(id = 2, title = "Belajar Desain", bookCount = 5, coverPath = ""),
            Collection(id = 3, title = "Koding", bookCount = 32, coverPath = ""),
        )
        collectionAdapter.submitList(dummyCollectionList)
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