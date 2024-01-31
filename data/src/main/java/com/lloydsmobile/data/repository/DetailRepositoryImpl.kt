package com.lloydsmobile.data.repository

import com.lloydsmobile.data.services.DetailApiService
import com.lloydsmobile.domain.mapper.toUserModel
import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailRepositoryImpl
    @Inject
    constructor(private val detailApiService: DetailApiService) :
    DetailRepository {
    private val _userModel = MutableStateFlow(UserModel())
    override val userModel: StateFlow<UserModel> get() = _userModel

        override suspend fun getUserById(id: String): StateFlow<UserModel> {
            try {
                val response =
                    detailApiService.getUserById(id)
                if (response.isSuccessful && response.body()?.data != null) {
                    _userModel.emit(response.body()!!.data.toUserModel()!!)
                    return userModel
                }
                return userModel
            } catch (e: Exception) {
                return userModel
            }
        }
    }
