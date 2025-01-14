package com.example.libraryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.model.AccountPost

@Dao
interface AccountPostDao {
    @Query("SELECT * FROM account_posts")
    suspend fun getAllAccountPosts(): List<AccountPost>

    @Insert
    suspend fun insertAccountPosts(accountPosts: List<AccountPost>)
}