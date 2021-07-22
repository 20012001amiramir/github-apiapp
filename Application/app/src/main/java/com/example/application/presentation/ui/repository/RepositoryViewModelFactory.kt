package com.example.application.presentation.ui.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.application.di.AppScope
import com.example.application.domain.usecase.RepositoryUseCase
import com.example.application.presentation.viewmodel.RepositoryViewModel
import javax.inject.Inject

@AppScope
class RepositoryViewModelFactory @Inject constructor(private val repositoryUseCase: RepositoryUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(RepositoryViewModel::class.java) -> {
                RepositoryViewModel(repositoryUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}