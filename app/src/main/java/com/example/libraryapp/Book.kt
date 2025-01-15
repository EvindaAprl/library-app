package com.example.libraryapp.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "books")
data class Book(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val synopsis: String,
    val coverPath: String?,
    val queueCount: Int,
    val availableCount: Int,
    val pageContent: String? = null,
    var isBorrowed:Boolean = false // Add this
)