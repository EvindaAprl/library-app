package com.example.libraryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.model.SocialPost

@Dao
interface SocialPostDao {
    @Query("SELECT * FROM social_posts")
    suspend fun getAllSocialPosts(): List<SocialPost>

    @Insert
    suspend fun insertSocialPosts(socialPosts: List<SocialPost>)
}