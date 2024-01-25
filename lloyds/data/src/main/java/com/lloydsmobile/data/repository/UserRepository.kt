package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UsersListDto
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val userListDto: StateFlow<UsersListDto>

    suspend fun getUsers()
}
