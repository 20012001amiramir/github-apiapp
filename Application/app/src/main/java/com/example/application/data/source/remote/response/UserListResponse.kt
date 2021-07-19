package com.example.application.data.source.remote.response

import com.example.application.data.source.remote.response.UserResponse
import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val list : List<UserResponse>
)
