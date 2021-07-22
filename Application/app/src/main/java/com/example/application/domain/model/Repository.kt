package com.example.application.domain.model

data class Repository(
    var name: String,

    var url: String,

    val owner: OwnerModel
)
