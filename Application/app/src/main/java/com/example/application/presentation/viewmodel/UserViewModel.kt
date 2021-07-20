package com.example.application.presentation.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.application.domain.usecase.UserUseCase


class UserViewModel(private val userUseCase: UserUseCase): ViewModel() {
    fun getSearchUser(q: String) = LiveDataReactiveStreams.fromPublisher(userUseCase.getSearchUser(q))
}