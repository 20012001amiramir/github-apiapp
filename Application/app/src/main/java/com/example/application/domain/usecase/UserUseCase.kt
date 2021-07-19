package com.example.application.domain.usecase

import com.example.application.data.Resource
import com.example.application.domain.model.User
import io.reactivex.Flowable

interface UserUseCase {
    fun getSearchUser(q: String): Flowable<Resource<List<User>>>
}