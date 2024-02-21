package com.lloydsmobile.domain.repository

import com.lloydsmobile.domain.model.UserModel
import com.lloydsmobile.domain.util.Resource

/**
 * This is the interface to fetch the User List through API
 */
interface UserRepository {
    suspend fun getUsers(): Resource<List<UserModel>>
}
