package com.example.application.domain.utils

import com.example.application.data.source.local.entity.Owner
import com.example.application.data.source.local.entity.RepositoryEntity
import com.example.application.data.source.local.entity.UserEntity
import com.example.application.data.source.remote.response.repositories.RepositoryResponse
import com.example.application.data.source.remote.response.user.UserResponse
import com.example.application.domain.model.OwnerModel
import com.example.application.domain.model.Repository
import com.example.application.domain.model.User


object DataMapper {
    fun mapUserResponsesToEntities(input: List<UserResponse>) : List<UserEntity>{
        val userList = ArrayList<UserEntity>()
        input.map{
            val user = UserEntity(
                id = it.id.toString(),
                username = it.username,
                avatarUrl = it.avatarUrl
            )
            userList.add(user)
        }
        return userList
    }

    fun mapUserEntitiesToDomain(input: List<UserEntity>) : List<User>{
        val userList = ArrayList<User>()
        input.map {
            val user = User(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
            userList.add(user)
        }
        return userList
    }
    fun mapRepositoryResponsesToEntities(input: List<RepositoryResponse>) : List<RepositoryEntity>{
        val userList = ArrayList<RepositoryEntity>()
        input.map{
            val user = RepositoryEntity(
                name = it.name,
                url = it.url,
                owner = Owner(it.owner.login)
            )
            userList.add(user)
        }
        return userList
    }

    fun mapRepositoryEntitiesToDomain(input: List<RepositoryEntity>) : List<Repository>{
        val userList = ArrayList<Repository>()
        input.map {
            val user = Repository(
                name = it.name,
                url = it.url,
                owner = OwnerModel(it.owner.login)
            )
            userList.add(user)
        }
        return userList
    }
}