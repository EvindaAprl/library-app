package com.example.libraryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocialPost(
    val id: Int,
    val userName: String,
    val userProfileImage: String,
    val time: String,
    val postText: String,
    val bookTitle: String,
    val likeCount: Int,
    val commentCount: Int
) : Parcelable