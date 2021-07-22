package com.example.application.data.source.remote

import android.annotation.SuppressLint
import com.example.application.data.source.remote.network.ApiResponse
import com.example.application.data.source.remote.network.ApiService
import com.example.application.data.source.remote.response.repositories.RepositoryResponse
import com.example.application.data.source.remote.response.user.UserResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    @SuppressLint("CheckResult")
    fun getSearchUser(q: String): Flowable<ApiResponse<List<UserResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<UserResponse>>>()

        val client = apiService.getSearchUser(q)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.list
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.tag("RemoteDataSource").e(error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
    @SuppressLint("CheckResult")
    fun getRepositories(q: String): Flowable<ApiResponse<List<RepositoryResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<RepositoryResponse>>>()

        val client = apiService.getRepo(q)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Timber.tag("RemoteDataSource").e(error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}