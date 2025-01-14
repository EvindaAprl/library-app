package com.example.libraryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_posts")
data class AccountPost(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val postText: String,
    )