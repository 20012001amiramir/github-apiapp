package com.example.application.data.source.local.entity

import androidx.room.ColumnInfo

data class Owner(
    @ColumnInfo(name = "login")
    val login: String
)