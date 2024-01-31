package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserModel
import kotlinx.coroutines.flow.StateFlow

interface DetailRepository {
    val userModel: StateFlow<UserModel>

    suspend fun getUserById(id: String): StateFlow<UserModel>
}
