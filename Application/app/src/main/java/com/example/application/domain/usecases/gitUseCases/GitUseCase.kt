package com.example.application.domain.usecases.gitUseCases

import com.example.application.domain.common.Result
import com.example.application.domain.repositories.IGitRepository
import com.example.practiseapp.domain.entities.RepositoryEntity
import com.example.practiseapp.domain.usecases.AuthUseCases.IGitUseCase
import javax.inject.Inject

class GitUseCase @Inject constructor(
    @AuthRepositoryMain private val gitRepository: IGitRepository
): IGitUseCase {
    override suspend fun invoke(repositoryEntity: RepositoryEntity): Result<RepositoryEntity> =
        gitRepository.getRepository(repositoryEntity)
}
