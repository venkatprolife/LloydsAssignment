package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.util.Resource

interface UserRepository {
    suspend fun getUsers(): Resource<List<UserModel>>
}
