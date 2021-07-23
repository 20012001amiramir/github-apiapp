package com.example.application.di

import android.app.Activity
import com.example.application.data.di.CoreComponent
import com.example.application.data.di.RepositoryModule
import com.example.application.presentation.ui.repository.RepositoriesActivity
import com.example.application.presentation.ui.repository.repositoryDownloaded.DownloadedRepositoriesActivity
import com.example.application.presentation.ui.repository.repositoryEntity.RepositoryDownloadActivity
import com.example.application.presentation.ui.user.UserActivity
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }
    fun injectR(activity: RepositoriesActivity)
    fun injectU(activity: UserActivity)
    fun injectD (activity: RepositoryDownloadActivity)
    fun injectDown (activity: DownloadedRepositoriesActivity)
}