package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserListModel
import kotlinx.coroutines.flow.StateFlow

interface UserRepositoryDomain {
    suspend fun getUsers(): UserListModel
}
