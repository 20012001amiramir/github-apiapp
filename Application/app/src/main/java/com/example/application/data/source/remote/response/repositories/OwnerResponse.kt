package com.example.application.data.source.remote.response.repositories

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("login")
    val login: String
)