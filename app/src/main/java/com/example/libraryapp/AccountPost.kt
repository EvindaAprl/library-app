package com.example.libraryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountPost(
    val id: Int,
    val postText: String,

    ) : Parcelable