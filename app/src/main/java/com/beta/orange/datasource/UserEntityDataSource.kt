package com.beta.orange.datasource

import com.beta.orange.database.AppDatabase
import com.beta.orange.database.UserEntity

//建表
class UserEntityDataSource(
    private val xDataBase: AppDatabase
) {

    suspend fun insert(entity: UserEntity) {
        xDataBase.getDao().insert(entity)
    }

}