package com.example.mynotesapp.appdata.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,

    @ColumnInfo(name = "Name")
    var name: String,

    @ColumnInfo(name = "Birthday")
    var birthday: String
)