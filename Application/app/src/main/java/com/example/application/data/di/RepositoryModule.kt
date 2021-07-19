package com.example.application.data.di

import com.example.application.data.UserRepository
import com.example.application.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(userRepository: UserRepository): IUserRepository
}