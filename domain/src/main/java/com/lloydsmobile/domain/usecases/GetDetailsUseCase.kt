package com.lloydsmobile.domain.usecases

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.util.Resource
import javax.inject.Inject

/**
 * Use case class to get the user details
 */
class GetDetailsUseCase
    @Inject
    constructor(private val detailRepository: DetailRepository) {
        suspend operator fun invoke(id: String): Resource<UserModel> {
            return detailRepository.getUserById(id)
        }
    }
