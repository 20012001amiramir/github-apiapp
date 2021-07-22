package com.example.application.presentation.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.application.domain.usecase.RepositoryUseCase
import com.example.application.domain.usecase.UserUseCase


class RepositoryViewModel(private val repositoryUseCase: RepositoryUseCase): ViewModel() {
    //without mapping to new model
    fun getRepositories(q: String) = LiveDataReactiveStreams.fromPublisher(repositoryUseCase.getRepository(q))
}