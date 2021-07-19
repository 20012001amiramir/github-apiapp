package com.example.application.domain.usecase

import com.example.application.domain.repository.IUserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) :
    UserUseCase {
    override fun getSearchUser(q: String) = userRepository.getSearchUser(q)

}