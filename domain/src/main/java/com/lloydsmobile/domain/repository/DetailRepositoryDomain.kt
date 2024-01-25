package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserModel
import kotlinx.coroutines.flow.StateFlow

interface DetailRepositoryDomain {
    suspend fun getUserById(id: String): StateFlow<UserModel>
}
