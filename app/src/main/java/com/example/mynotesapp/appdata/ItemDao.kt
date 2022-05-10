package com.example.mynotesapp.appdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItemToDatabase(item: Item)

    @Query("SELECT * FROM item_table ORDER BY itemId ASC")
    fun readAllData(): LiveData<List<Item>>
}