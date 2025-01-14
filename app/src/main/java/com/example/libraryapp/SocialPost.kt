package com.example.libraryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "social_posts")
data class SocialPost(
    @PrimaryKey val id: Int,
    val userName: String,
    val userProfileImage: String,
    val time: String,
    val postText: String,
    val bookTitle: String,
    val bookCoverUrl: String?,
    val likeCount: Int,
    val commentCount: Int
)