package com.example.application.domain.repository

import com.example.application.data.Resource
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.model.Repository
import com.example.application.domain.model.User
import io.reactivex.Flowable

interface IDownloadedRepositoryRepository {
    fun getDownloadedRepository(): Flowable<Resource<List<DownloadRepositoryModel>>>
    fun setData(data: DownloadRepositoryModel)
}