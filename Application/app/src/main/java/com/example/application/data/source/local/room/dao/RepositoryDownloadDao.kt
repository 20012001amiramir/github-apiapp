package com.example.application.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.application.data.Resource
import com.example.application.data.source.local.entity.Owner
import com.example.application.data.source.local.entity.RepositoryDownload
import com.example.application.data.source.local.entity.RepositoryEntity
import com.example.application.data.source.local.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface RepositoryDownloadDao {

    @Query("SELECT * FROM repository")
    fun getRepositories():  Flowable<List<RepositoryDownload>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(repository: RepositoryDownload): Completable

}