package com.example.practiseapp.domain.usecases.AuthUseCases

import com.example.application.domain.common.Result
import com.example.practiseapp.domain.entities.RepositoryEntity

interface IGitUseCase {
    suspend operator fun invoke(repositoryEntity: RepositoryEntity): Result<RepositoryEntity>
}
