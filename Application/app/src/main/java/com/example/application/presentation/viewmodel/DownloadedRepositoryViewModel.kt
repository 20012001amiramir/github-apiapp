package com.example.application.presentation.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.usecase.DownloadedRepositoryUseCase

class DownloadedRepositoryViewModel(private val downloadedRepositoryUseCase: DownloadedRepositoryUseCase): ViewModel() {
    //without mapping to new model
    fun getRepositories() = LiveDataReactiveStreams.fromPublisher(downloadedRepositoryUseCase.getDownloadedRepository())
    fun setData(data: DownloadRepositoryModel) = downloadedRepositoryUseCase.setData(data)
}