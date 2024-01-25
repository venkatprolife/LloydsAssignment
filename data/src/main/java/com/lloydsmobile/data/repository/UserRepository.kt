package com.lloydsmobile.data.repository

import com.lloydsmobile.data.models.UsersListDto

interface UserRepository {
    suspend fun getUsers(): UsersListDto
}
