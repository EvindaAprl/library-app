package com.example.libraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libraryapp.adapter.AccountPostAdapter
import com.example.libraryapp.databinding.FragmentAccountBinding
import com.example.libraryapp.model.AccountPost
import com.example.libraryapp.model.UserProfile


class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var accountPostAdapter: AccountPostAdapter
    private lateinit var userProfile: UserProfile


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadData()
        loadUserProfile()
        setupButtons()

    }

    private fun setupRecyclerView() {
        accountPostAdapter = AccountPostAdapter()
        binding.postRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountPostAdapter
        }
    }

    private fun loadData() {
        val dummyPostList = listOf(
            AccountPost(id = 1, postText = "Lagi baca \"Detektif Conan 104\", baru sampai hal...",),
            AccountPost(id = 2, postText = "Belajar bahasa baru nih, lewat buku \"Machine Le...",),
            AccountPost(id = 3, postText = "Ada yang udah baca buku ini belum ya?? Aku m...",),

            )
        accountPostAdapter.submitList(dummyPostList)

    }

    private fun loadUserProfile() {
        // load profile user dari database atau api
        userProfile = UserProfile(
            userName = "Evinda Apriliani",
            userBio =  "Membaca adalah jendela dunia.",
            userBacaCount = 21,
            userFollowingCount = 120,
            userFollowersCount = 300,
            profileImage = ""
        )
        binding.profileName.text = userProfile.userName
        binding.profileBio.text = userProfile.userBio
        binding.statsBaca.text = "${userProfile.userBacaCount}\nBaca"
        binding.statsFollowing.text = "${userProfile.userFollowingCount}\nFollowing"
        binding.statsFollowers.text = "${userProfile.userFollowersCount}\nFollowers"

    }

    private fun setupButtons() {
        binding.editProfileButton.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.edit_profile_not_available), Toast.LENGTH_SHORT).show()
        }

        binding.shareProfileButton.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.share_profile_not_available), Toast.LENGTH_SHORT).show()
        }
        binding.postButton.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.post_feature_not_available), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}