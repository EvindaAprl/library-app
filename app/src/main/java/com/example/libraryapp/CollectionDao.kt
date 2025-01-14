package com.example.libraryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.model.Collection

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collections")
    suspend fun getAllCollections(): List<Collection>

    @Query("SELECT * FROM collections WHERE id = :collectionId")
    suspend fun getCollectionById(collectionId: Int): Collection?

    @Insert
    suspend fun insertCollection(collection: Collection)

    @Insert
    suspend fun insertCollections(collections: List<Collection>)
}