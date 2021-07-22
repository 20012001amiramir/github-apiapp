package com.example.application.domain.usecase

import com.example.application.domain.repository.IRepositoryRepository
import javax.inject.Inject

class RepositoryInteractor @Inject constructor(private val RepositoryRepository: IRepositoryRepository) :
    RepositoryUseCase {
    override fun getRepository(q: String) = RepositoryRepository.getRepository(q)

}