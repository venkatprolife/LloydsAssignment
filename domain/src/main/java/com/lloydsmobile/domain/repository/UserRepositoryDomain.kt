package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserListModel

interface UserRepositoryDomain {
    suspend fun getUsers(): UserListModel
}
