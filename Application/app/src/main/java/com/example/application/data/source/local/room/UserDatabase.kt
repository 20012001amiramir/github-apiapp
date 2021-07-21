package com.example.application.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.application.data.source.local.entity.RepositoryEntity
import com.example.application.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class, RepositoryEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}