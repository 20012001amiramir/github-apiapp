package com.example.application.data.di

import com.example.application.data.RepositoryRepository
import com.example.application.data.UserRepository
import com.example.application.domain.repository.IRepositoryRepository
import com.example.application.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryRepositoryModule {
    
    @Binds
    abstract fun provideRepositoryRepository(RepositoryRepository: RepositoryRepository): IRepositoryRepository
}