package com.example.application.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.application.data.source.local.entity.DetailUserEntity
import com.example.application.data.source.local.entity.UserEntity
import com.example.application.data.source.local.room.UserDao

@Database(entities = [UserEntity::class, DetailUserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}