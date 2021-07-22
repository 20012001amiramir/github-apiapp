package com.example.application.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "html_url")
    var url: String,

    @ColumnInfo(name = "Downloaded")
    val downloaded: Boolean = false,

    @Embedded
    val owner: Owner

)

