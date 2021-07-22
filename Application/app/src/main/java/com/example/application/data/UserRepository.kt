package com.example.application.data

import android.util.Log
import com.example.application.data.source.local.LocalDataSource
import com.example.application.data.source.remote.RemoteDataSource
import com.example.application.data.source.remote.network.ApiResponse
import com.example.application.domain.model.User
import com.example.application.domain.repository.IUserRepository
import com.example.application.data.source.remote.response.user.UserResponse
import com.example.application.domain.utils.AppExecutors
import com.example.application.domain.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun getSearchUser(q: String): Flowable<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): Flowable<List<User>> {
                val query = "%$q%"
                return localDataSource.getSearchUser(query).map {
                    Log.e("User Repository", "$it")
                    DataMapper.mapUserEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                true

            override fun createCall(): Flowable<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getSearchUser(q)

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertSearchUser(userList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()
}