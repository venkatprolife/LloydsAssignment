package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.repository.UserRepository
import com.lloydsmobile.domain.util.Resource
import javax.inject.Inject

class GetUserUseCase
    @Inject
    constructor(private val userRepository: UserRepository) {
        suspend operator fun invoke(): Resource<UserListModel> {
            // Perform any necessary business logic or data transformations here
            return userRepository.getUsers()
        }
    }
