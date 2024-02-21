package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.UserRepository
import com.lloydsmobile.domain.util.Resource
import javax.inject.Inject

/**
 * Use case class to get the user details
 */
class GetUserUseCase
    @Inject
    constructor(private val userRepository: UserRepository) {
        suspend operator fun invoke(): Resource<List<UserModel>> {
            return userRepository.getUsers()
        }
    }
