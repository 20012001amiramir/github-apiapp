package com.example.application.data.source.remote.network

import com.example.application.data.source.remote.response.UserListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") q: String
    ): Flowable<UserListResponse>
}