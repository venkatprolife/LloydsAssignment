package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserListModel
import com.lloydsmobile.domain.util.Resource

interface UserRepository {
    suspend fun getUsers(): Resource<UserListModel>
}
