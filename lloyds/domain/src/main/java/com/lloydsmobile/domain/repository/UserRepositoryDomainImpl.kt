package com.lloydsmobile.domain.repository

import com.lloydsmobile.data.repository.UserRepository
import com.lloydsmobile.domain.mapper.toUserListModel
import com.lloydsmobile.domain.model.UserListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepositoryDomainImpl
    @Inject
    constructor(private val userRepository: UserRepository) :
    UserRepositoryDomain {
        private val _userListModel = MutableStateFlow(UserListModel())
        val userListModel: StateFlow<UserListModel> get() = _userListModel

        override suspend fun getUsers(): StateFlow<UserListModel> {
            userRepository.getUsers()
            _userListModel.emit(userRepository.userListDto.value.toUserListModel())
            return userListModel
        }
    }
