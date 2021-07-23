package com.example.application.data.di

import android.content.Context
import com.example.application.domain.repository.IDownloadedRepositoryRepository
import com.example.application.domain.repository.IRepositoryRepository
import com.example.application.domain.repository.IUserRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : CoreComponent
    }

    fun provideUserRepository() : IUserRepository

    fun provideRepositoryRepository() : IRepositoryRepository

    fun provideDownloadedRepositoryRepository() : IDownloadedRepositoryRepository
}