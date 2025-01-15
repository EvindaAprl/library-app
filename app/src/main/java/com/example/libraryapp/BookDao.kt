package com.example.libraryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: Int): Book?

    @Insert
    suspend fun insertBook(book: Book)

    @Insert
    suspend fun insertBooks(books : List<Book>)
}