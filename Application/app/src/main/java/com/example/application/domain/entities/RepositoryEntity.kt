package com.example.practiseapp.domain.entities

import retrofit2.http.Url

data class RepositoryEntity(
    val id: Int = 0,
    val address: Url,
    val name: String
)