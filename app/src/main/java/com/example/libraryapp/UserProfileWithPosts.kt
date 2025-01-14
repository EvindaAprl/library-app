package com.example.libraryapp

import androidx.room.Embedded
import androidx.room.Relation
import com.example.libraryapp.model.AccountPost
import com.example.libraryapp.model.UserProfile

data class UserProfileWithPosts(
    @Embedded val userProfile: UserProfile,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val posts: List<AccountPost>
)