package com.example.application.domain.usecase

import com.example.application.data.Resource
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.model.Repository
import com.example.application.domain.model.User
import io.reactivex.Flowable

interface DownloadedRepositoryUseCase {
    fun getDownloadedRepository(): Flowable<Resource<List<DownloadRepositoryModel>>>
    fun setData(data: DownloadRepositoryModel)
}