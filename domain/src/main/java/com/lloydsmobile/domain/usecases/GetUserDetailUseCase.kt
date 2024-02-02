package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.util.Resource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetUserDetailUseCase
    @Inject
    constructor(private val detailRepository: DetailRepository) {
        suspend fun getUserById(id: String): Resource<UserModel> {
            // Perform any necessary business logic or data transformations here
            return detailRepository.getUserById(id)
        }
    }
