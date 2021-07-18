package com.example.application.domain.repositories

import com.example.application.domain.common.Result
import com.example.practiseapp.domain.entities.RepositoryEntity

interface IGitRepository {

    suspend fun getRepository(repository: RepositoryEntity): Result<RepositoryEntity>

}
