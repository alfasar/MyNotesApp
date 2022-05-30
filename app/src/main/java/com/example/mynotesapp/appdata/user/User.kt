package com.example.mynotesapp.appdata.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "E_mail")
    var email: String,

    @ColumnInfo(name = "Password")
    var password: String
)