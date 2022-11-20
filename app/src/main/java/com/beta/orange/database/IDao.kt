package com.beta.orange.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface IDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: List<T>)

    @Delete
    suspend fun delete(data: List<T>)

    @Delete
    suspend fun delete(data: T)
}