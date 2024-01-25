package com.lloydsmobile.data.services

import com.lloydsmobile.data.models.UsersListDto
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiService {
    @GET("/api/users")
    suspend fun getUsers(): Response<UsersListDto>
}
