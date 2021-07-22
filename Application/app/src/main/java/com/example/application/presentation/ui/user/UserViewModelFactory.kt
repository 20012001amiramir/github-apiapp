package com.example.application.presentation.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.application.di.AppScope
import com.example.application.domain.usecase.UserUseCase
import com.example.application.presentation.viewmodel.UserViewModel
import javax.inject.Inject

@AppScope
class UserViewModelFactory @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}