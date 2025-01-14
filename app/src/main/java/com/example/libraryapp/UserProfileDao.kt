package com.example.libraryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.model.UserProfile

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profiles LIMIT 1")
    suspend fun getUserProfile(): UserProfile?

    @Insert
    suspend fun insertProfile(profile: UserProfile)
}