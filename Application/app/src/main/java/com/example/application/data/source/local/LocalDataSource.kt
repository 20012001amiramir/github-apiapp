package com.example.application.data.source.local

import com.example.application.data.Resource
import com.example.application.data.source.local.entity.RepositoryDownload
import com.example.application.data.source.local.entity.RepositoryEntity
import com.example.application.data.source.local.entity.UserEntity
import com.example.application.data.source.local.room.dao.RepositoryDao
import com.example.application.data.source.local.room.dao.RepositoryDownloadDao
import com.example.application.data.source.local.room.dao.UserDao
import com.example.application.domain.model.DownloadRepositoryModel
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao, private val repositoryDao: RepositoryDao, private val repositoryDownloadDao: RepositoryDownloadDao) {
    fun getSearchUser(q: String): Flowable<List<UserEntity>> = userDao.getSearchUser(q)
    fun insertSearchUser(userList: List<UserEntity>) = userDao.insertSearchUser(userList)
    fun getRepository(q: String): Flowable<List<RepositoryEntity>> = repositoryDao.getRepositories(q)
    fun insertRepository(repository: List<RepositoryEntity>) = repositoryDao.insertRepository(repository)
    fun getDownloadRepository(): Flowable<List<RepositoryDownload>> = repositoryDownloadDao.getRepositories()
    fun insertDownloadRepository(repository: RepositoryDownload) = repositoryDownloadDao.insertRepository(repository)
}