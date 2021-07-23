package com.example.application.domain.model


data class DownloadRepositoryModel (
    var name: String?,

    var url: String,

    val owner: OwnerModel
)