package com.example.application.di

import com.example.application.domain.usecase.UserInteractor
import com.example.application.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase
}