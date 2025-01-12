package com.example.libraryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserProfile(
    val userName: String,
    val userBio: String,
    val userBacaCount: Int,
    val userFollowingCount: Int,
    val userFollowersCount: Int,
    val profileImage: String,
):Parcelable