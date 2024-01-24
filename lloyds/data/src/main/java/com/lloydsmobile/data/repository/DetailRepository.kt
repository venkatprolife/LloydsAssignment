package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UserDto
import kotlinx.coroutines.flow.StateFlow

interface DetailRepository {
    val userDto: StateFlow<UserDto?>
    suspend fun getUserById(id: String)
}