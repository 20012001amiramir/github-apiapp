package com.example.application.domain.repository

import com.example.application.data.Resource
import com.example.application.domain.model.Repository
import com.example.application.domain.model.User
import io.reactivex.Flowable

interface IRepositoryRepository {
    fun getRepository(q: String): Flowable<Resource<List<Repository>>>
}