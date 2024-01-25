package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UserDto
import com.lloydsmobile.data.services.DetailApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DetailRepositoryImpl
    @Inject
    constructor(private val detailApiService: DetailApiService) :
    DetailRepository {
        private val _userDto = MutableStateFlow(UserDto())
        override val userDto: StateFlow<UserDto?> get() = _userDto

        override suspend fun getUserById(id: String) {
            try {
                val response =
                    detailApiService.getUserById(id)
                if (response.isSuccessful && response.body()?.data != null) {
                    _userDto.emit(response.body()!!.data)
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
