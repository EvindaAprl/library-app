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
import com.example.libraryapp.adapter.SocialAdapter
import com.example.libraryapp.databinding.FragmentSocialBinding
import kotlinx.coroutines.launch

class SocialFragment : Fragment() {

    private var _binding: FragmentSocialBinding? = null
    private val binding get() = _binding!!
    private lateinit var socialAdapter: SocialAdapter
    private lateinit var socialPostDao: SocialPostDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        socialPostDao = (requireActivity() as DashboardActivity).database.socialPostDao()

        setupRecyclerView()
        loadData()
        setupSearch()
    }

    private fun setupRecyclerView() {
        socialAdapter = SocialAdapter()
        binding.socialRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = socialAdapter
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                val socialPosts = socialPostDao.getAllSocialPosts()
                socialAdapter.submitList(socialPosts)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("SocialFragment", "Error loading social posts", e)
            }
        }
    }

    private fun setupSearch() {
        binding.searchBar.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.search_feature_not_available), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}