package com.example.application.domain.usecase

import com.example.application.data.Resource
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.repository.IDownloadedRepositoryRepository
import com.example.application.domain.repository.IRepositoryRepository
import io.reactivex.Flowable
import javax.inject.Inject

class DownloadedRepositoryInteractor @Inject constructor(private val DownloadedRepositoryRepository: IDownloadedRepositoryRepository) :
    DownloadedRepositoryUseCase {
    override fun getDownloadedRepository() = DownloadedRepositoryRepository.getDownloadedRepository()

    override fun setData(data: DownloadRepositoryModel) = DownloadedRepositoryRepository.setData(data)
}