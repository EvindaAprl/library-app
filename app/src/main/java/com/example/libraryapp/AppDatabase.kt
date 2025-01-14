package com.example.libraryapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.libraryapp.model.AccountPost
import com.example.libraryapp.model.Book
import com.example.libraryapp.model.Collection
import com.example.libraryapp.model.SocialPost
import com.example.libraryapp.model.UserProfile

@Database(entities = [Book::class, Collection::class, SocialPost::class, UserProfile::class, AccountPost::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun collectionDao(): CollectionDao
    abstract fun socialPostDao(): SocialPostDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun AccountPostDao(): AccountPostDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "library_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}