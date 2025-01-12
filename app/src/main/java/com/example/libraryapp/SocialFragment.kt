package com.example.libraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryapp.adapter.SocialAdapter
import com.example.libraryapp.databinding.FragmentSocialBinding
import com.example.libraryapp.model.SocialPost

class SocialFragment : Fragment() {

    private var _binding: FragmentSocialBinding? = null
    private val binding get() = _binding!!
    private lateinit var socialAdapter: SocialAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val dummySocialPost = listOf(
            SocialPost(id = 1, userName = "Budi Doremi", userProfileImage = "", time = "2 jam", postText = "Aku baru selesai baca part awal dan sudah dibuat penasaran! Plot twist-nya nggak ketebak!", bookTitle = "Detektif Conan 104", likeCount = 39, commentCount = 3),
            SocialPost(id = 2, userName = "Dian Sastro", userProfileImage = "", time = "4 jam", postText = "Buku ini mengingatkan saya pada realitas sosial di Indonesia. Sebuah karya sastra yang berani dan penuh makna.", bookTitle = "Cantik Itu Luka", likeCount = 53, commentCount = 12),
        )
        socialAdapter.submitList(dummySocialPost)
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