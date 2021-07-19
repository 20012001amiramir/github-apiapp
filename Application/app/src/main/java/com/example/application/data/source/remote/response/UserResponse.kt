package com.example.application.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("login")
    val username: String,

    @SerializedName("avatar_url")
    val avatarUrl: String
)
