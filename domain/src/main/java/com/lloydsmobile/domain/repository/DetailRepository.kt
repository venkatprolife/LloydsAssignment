package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.util.Resource

/**
 * This is the interface to fetch the Details of user through API
 */
interface DetailRepository {
    suspend fun getUserById(id: String): Resource<UserModel>
}
