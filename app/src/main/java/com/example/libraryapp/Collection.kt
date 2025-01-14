package com.example.libraryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class Collection(
    @PrimaryKey val id: Int,
    val title: String,
    val bookCount: Int,
    val coverPath: String?
)