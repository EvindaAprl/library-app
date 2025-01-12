package com.example.libraryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    val id: Int,
    val title: String,
    val bookCount: Int,
    val coverPath: String
) : Parcelable