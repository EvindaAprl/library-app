package com.example.libraryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey val userName: String,
    val userBio: String,
    val userBacaCount: Int,
    val userFollowingCount: Int,
    val userFollowersCount: Int,
    val profileImage: String,
)