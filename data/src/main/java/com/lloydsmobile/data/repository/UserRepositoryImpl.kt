package com.lloydsmobile.data.repository

import android.util.Log
import com.lloydsmobile.data.models.UsersListDto
import com.lloydsmobile.data.services.UsersApiService
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(private val usersApiService: UsersApiService) :
    UserRepository {
        override suspend fun getUsers(): UsersListDto {
            try {
                val response = usersApiService.getUsers()
                if (response.isSuccessful && response.body()?.data != null) {
                    return response.body()!!
                }
            } catch (e: Exception) {
                Log.d("Venkat", e.toString())
            }
            return UsersListDto()
        }
    }
