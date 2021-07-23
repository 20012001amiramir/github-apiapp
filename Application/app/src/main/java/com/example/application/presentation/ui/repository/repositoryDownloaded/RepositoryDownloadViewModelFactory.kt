package com.example.application.presentation.ui.repository.repositoryDownloaded

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.application.di.AppScope
import com.example.application.domain.usecase.DownloadedRepositoryUseCase
import com.example.application.presentation.viewmodel.DownloadedRepositoryViewModel
import javax.inject.Inject

@AppScope
class RepositoryDownloadViewModelFactory @Inject constructor(private val repositoryDownloadUseCase: DownloadedRepositoryUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DownloadedRepositoryViewModel::class.java) -> {
                DownloadedRepositoryViewModel(repositoryDownloadUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}