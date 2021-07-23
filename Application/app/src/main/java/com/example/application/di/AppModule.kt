package com.example.application.di

import com.example.application.domain.usecase.*
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase

    @Binds
    abstract fun provideRepositoryUseCase(repositoryInteractor: RepositoryInteractor): RepositoryUseCase

    @Binds
    abstract fun provideDownloadRepositoryUseCase(downloadedRepositoryInteractor: DownloadedRepositoryInteractor): DownloadedRepositoryUseCase

}