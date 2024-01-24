package com.lloydsmobile.data.services

import com.lloydsmobile.data.models.SingleUserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApiService {
    @GET("/api/users/{id}")
    suspend fun getUserById(@Path("id") id: String) : Response<SingleUserDto>
}