package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.util.Resource
import javax.inject.Inject

class GetDetailsUseCase
    @Inject
    constructor(private val detailRepository: DetailRepository) {
        suspend operator fun invoke(id: String): Resource<UserModel> {
            // Perform any necessary business logic or data transformations here
            return detailRepository.getUserById(id)
        }
    }
