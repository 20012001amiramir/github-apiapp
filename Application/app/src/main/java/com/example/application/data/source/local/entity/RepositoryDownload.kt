package com.example.application.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloadedRepository")
data class RepositoryDownload(
    @ColumnInfo(name = "name")
    var name: String?,

    @PrimaryKey
    @ColumnInfo(name = "html_url")
    var url: String,

    @Embedded
    val owner: Owner

)