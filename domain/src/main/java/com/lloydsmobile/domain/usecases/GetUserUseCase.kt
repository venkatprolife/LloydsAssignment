package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.repository.UserRepositoryDomain
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetUserUseCase
    @Inject
    constructor(private val userRepositoryDomain: UserRepositoryDomain) {
        suspend fun getUsers(): UserListModel {
            // Perform any necessary business logic or data transformations here
            return userRepositoryDomain.getUsers()
        }
    }
