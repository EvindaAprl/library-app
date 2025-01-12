package com.example.libraryapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val synopsis: String,
    val coverPath: String,
    val queueCount: Int,
    val availableCount: Int
) : Parcelable