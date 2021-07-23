package com.example.application.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.example.application.data.source.local.LocalDataSource
import com.example.application.data.source.local.entity.RepositoryDownload
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.repository.IDownloadedRepositoryRepository
import com.example.application.domain.utils.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadedRepositoryRepository @Inject constructor(
    private val localDataSource: LocalDataSource
    ) : IDownloadedRepositoryRepository {

    override fun getDownloadedRepository(): Flowable<Resource<List<DownloadRepositoryModel>>> =
        object : DatabaseBoundResource<List<DownloadRepositoryModel>, List<RepositoryDownload>>() {
            override fun loadFromDB(): Flowable<List<DownloadRepositoryModel>> {
                return localDataSource.getDownloadRepository().map {
                    DataMapper.mapDownloadRepositoryResponsesToEntities(it)
                }
            }
        }.asFlowable()
    override fun setData(data: DownloadRepositoryModel){
        localDataSource.insertDownloadRepository(
            DataMapper.mapRepositoryDownloadModelEntitiesToDomain(data)
        )
    }

}