package com.lloydsmobile.domain.repository

import com.lloydsmobile.data.repository.UserRepository
import com.lloydsmobile.domain.mapper.toUserListModel
import com.lloydsmobile.domain.model.UserListModel
import javax.inject.Inject

class UserRepositoryDomainImpl
@Inject
constructor(private val userRepository: UserRepository) :
    UserRepositoryDomain {
    override suspend fun getUsers(): UserListModel {
        val userListDto = userRepository.getUsers()
        return userListDto.toUserListModel()
    }
}
