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
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@Database(entities = [Book::class, Collection::class, SocialPost::class, UserProfile::class, AccountPost::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun collectionDao(): CollectionDao
    abstract fun socialPostDao(): SocialPostDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun accountPostDao(): AccountPostDao // Corrected function name

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
                    .fallbackToDestructiveMigration() // Add this line
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase){
                            super.onCreate(db)
                            // Trigger database seeding
                            INSTANCE?.let {db ->
                                (context as? DashboardActivity)?.lifecycleScope?.launch {
                                    DatabaseSeeder.seed(context, getInstance(context).bookDao(), getInstance(context).collectionDao(), getInstance(context).socialPostDao(), getInstance(context).userProfileDao())
                                }
                            }


                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}