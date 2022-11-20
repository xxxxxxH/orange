package com.beta.orange.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id:Long=0,
    @ColumnInfo(name = "a") val a:String?,
    @ColumnInfo(name = "b") val b:String?
)
