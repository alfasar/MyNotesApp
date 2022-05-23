package com.example.mynotesapp.appdata

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItemToDatabase(item: Item)

    @Query("SELECT * FROM item_table ORDER BY itemId ASC")
    fun readAllData(): LiveData<MutableList<Item>>

    @Query("DELETE FROM item_table")
    suspend fun deleteAll()
}