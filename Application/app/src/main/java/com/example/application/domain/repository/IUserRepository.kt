package com.example.application.domain.repository

import com.example.application.data.Resource
import com.example.application.domain.model.User
import io.reactivex.Flowable

interface IUserRepository {
    fun getSearchUser(q: String): Flowable<Resource<List<User>>>
}