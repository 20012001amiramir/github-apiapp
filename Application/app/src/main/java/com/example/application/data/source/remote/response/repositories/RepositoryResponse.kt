package com.example.application.data.source.remote.response.repositories

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("html_url")
    var url: String,

    @SerializedName("owner")
    val owner: OwnerResponse
)
