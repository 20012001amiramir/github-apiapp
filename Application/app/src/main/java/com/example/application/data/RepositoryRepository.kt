package com.example.application.data

import android.util.Log
import com.example.application.data.source.local.LocalDataSource
import com.example.application.data.source.remote.RemoteDataSource
import com.example.application.data.source.remote.network.ApiResponse
import com.example.application.data.source.remote.response.repositories.RepositoryResponse
import com.example.application.domain.model.Repository
import com.example.application.domain.repository.IRepositoryRepository
import com.example.application.domain.utils.AppExecutors
import com.example.application.domain.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRepositoryRepository {
    override fun getRepository(q: String): Flowable<Resource<List<Repository>>> =
        object : NetworkBoundResource<List<Repository>, List<RepositoryResponse>>(appExecutors) {
            override fun loadFromDB(): Flowable<List<Repository>> {
                val query = "%$q%"
                return localDataSource.getRepository(query).map {
                    DataMapper.mapRepositoryEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Repository>?): Boolean =
                true

            override fun createCall(): Flowable<ApiResponse<List<RepositoryResponse>>> =
                remoteDataSource.getRepositories(q)

            override fun saveCallResult(data: List<RepositoryResponse>) {
                val userList = DataMapper.mapRepositoryResponsesToEntities(data)
                localDataSource.insertRepository(userList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()
}