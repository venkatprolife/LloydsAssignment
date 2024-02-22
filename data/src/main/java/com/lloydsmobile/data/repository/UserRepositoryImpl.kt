package com.lloydsmobile.data.repository

import com.lloydsmobile.data.mapper.toUserListModel
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.UsersApiService
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.UserRepository
import com.lloydsmobile.domain.util.Resource
import javax.inject.Inject

/**
 * This class fetches the User List through API
 */
class UserRepositoryImpl
@Inject
constructor(private val usersApiService: UsersApiService) :
    UserRepository, BaseRepo() {
    override suspend fun getUsers(): Resource<List<UserModel>> {
        val result: Resource<UsersListDto> = safeApiCall { usersApiService.getUsers() }
        if (result is Resource.Success) {
            val userDtoList = result.data
            if (userDtoList != null)
                return Resource.Success(userDtoList.toUserListModel())
        }
        return Resource.Error(result.message!!)
    }
}
