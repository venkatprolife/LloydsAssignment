package com.lloydsmobile.data.repository

import com.lloydsmobile.data.mapper.toUserModel
import com.lloydsmobile.data.models.SingleUserDto
import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import com.lloydsmobile.domain.util.Resource
import javax.inject.Inject

/**
 * This class fetches the Details of User through API
 */
class DetailRepositoryImpl
    @Inject
    constructor(private val detailApiService: DetailApiService) :
    DetailRepository, BaseRepo() {
        override suspend fun getUserById(id: String): Resource<UserModel> {
            val result: Resource<SingleUserDto> = safeApiCall { detailApiService.getUserById(id) }
            if (result is Resource.Success && result.data != null) {
                val userDto = result.data?.data
                if (userDto != null) {
                    return Resource.Success(userDto.toUserModel())
                }
            }
            return Resource.Error(result.message!!)
        }
    }
