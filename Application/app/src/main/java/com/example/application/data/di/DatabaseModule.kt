package com.example.application.data.di

import android.content.Context
import androidx.room.Room
import com.example.application.data.source.local.room.dao.RepositoryDao
import com.example.application.data.source.local.room.dao.UserDao
import com.example.application.data.source.local.room.UserDatabase
import com.example.application.data.source.local.room.dao.RepositoryDownloadDao
import com.example.application.domain.usecase.DownloadedRepositoryUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabases(context: Context): UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java, "User.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(database: UserDatabase) : UserDao = database.userDao()

    @Provides
    fun provideRepositoryDao(database: UserDatabase) : RepositoryDao = database.repositoryDao()

    @Provides
    fun provideDownloadRepositoryDao(database: UserDatabase) : RepositoryDownloadDao = database.repositoryDownloadDao()
}