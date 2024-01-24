package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.mapper.toUserModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.data.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailRepositoryDomainImpl @Inject constructor(private val detailRepository: DetailRepository) :
    DetailRepositoryDomain {

    private val _userModel = MutableStateFlow(UserModel())
    val userModel: StateFlow<UserModel> get() = _userModel

    override suspend fun getUserById(id: String): StateFlow<UserModel> {
         detailRepository.getUserById(id)
        _userModel.emit(detailRepository.userDto.value?.toUserModel()!!)
        return userModel
    }
}