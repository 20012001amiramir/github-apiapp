package com.example.application.data.source.remote.network

import com.example.application.data.source.remote.response.repositories.RepositoryResponse
import com.example.application.data.source.remote.response.user.UserListResponse
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") q: String
    ): Flowable<UserListResponse>

    @GET("/users/{user}/repos")
    fun getRepo(@Path("user") name: String?): Flowable<List<RepositoryResponse>>


}