package com.example.application.data.source.local

import com.example.application.data.source.local.entity.UserEntity
import com.example.application.data.source.local.room.UserDao
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao) {
    fun getSearchUser(q: String): Flowable<List<UserEntity>> = userDao.getSearchUser(q)
    fun insertSearchUser(userList: List<UserEntity>) = userDao.insertSearchUser(userList)
}