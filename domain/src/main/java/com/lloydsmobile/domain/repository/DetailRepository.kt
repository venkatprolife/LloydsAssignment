package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.util.Resource

interface DetailRepository {
    suspend fun getUserById(id: String): Resource<UserModel>
}
