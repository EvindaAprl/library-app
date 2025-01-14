package com.example.libraryapp.model

data class UserProfile(
    val userName: String,
    val userBio: String,
    val userBacaCount: Int,
    val userFollowingCount: Int,
    val userFollowersCount: Int,
    val profileImage: String,
)